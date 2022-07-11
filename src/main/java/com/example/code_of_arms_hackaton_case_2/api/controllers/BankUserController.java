package com.example.code_of_arms_hackaton_case_2.api.controllers;

import com.example.code_of_arms_hackaton_case_2.entity.*;
import com.example.code_of_arms_hackaton_case_2.repository.BankUserRepository;
import com.example.code_of_arms_hackaton_case_2.repository.BonusCountEntityRepository;
import com.example.code_of_arms_hackaton_case_2.repository.CreditCardRepository;
import com.example.code_of_arms_hackaton_case_2.repository.InvoiceRepository;
import com.example.code_of_arms_hackaton_case_2.security.JwtGenerator;
import com.example.code_of_arms_hackaton_case_2.service.BonusCountEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;


@Transactional
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class BankUserController {
    private final CreditCardRepository creditCardRepository;
    private final BankUserRepository bankUserRepository;
    private final BonusCountEntityRepository bonusCountEntityRepository;
    private final InvoiceRepository invoiceRepository;
    private final JwtGenerator jwtGenerator;
    private final BonusCountEntityService bonusCountEntityService;

    @GetMapping("/hello")
    public ResponseEntity<String> hello() {
        return ResponseEntity.status(HttpStatus.OK).body("Sirgay");
    }

    @GetMapping("/info")
    public ResponseEntity<?> getInformation(HttpServletRequest httpServletRequest) {
        BankUser bankUser = getClientFromServletRequest(httpServletRequest);
        return ResponseEntity.status(HttpStatus.OK).body(bankUser);
    }

    @PostMapping("/create-card")
    public ResponseEntity<?> createCard(HttpServletRequest httpServletRequest) {
        BankUser bankUser = getClientFromServletRequest(httpServletRequest);
        CreditCardEntity card = new CreditCardEntity();

        List<CreditCardEntity> list = creditCardRepository.findCreditCardEntitiesByBankUser(bankUser);
        list.add(card);
        card.setBankUser(bankUser);
        creditCardRepository.save(card);
        return ResponseEntity.status(HttpStatus.OK).body("The card was successfully created!");
    }

    @PostMapping("/deposit")
    public ResponseEntity<?> depositMoney(@RequestParam double amount, @RequestParam long cardId) {
        try {
            CreditCardEntity creditCardEntity = creditCardRepository.getCreditCardEntityById(cardId);
            creditCardEntity.setMoneyOnCard(creditCardEntity.getMoneyOnCard() + amount);
            return ResponseEntity.status(HttpStatus.OK).body("The money was successfully transferred!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("There is no credit card with such id!");
        }
    }


    @GetMapping("/cards")
    public ResponseEntity<?> getCards(HttpServletRequest httpServletRequest) {
        BankUser bankUser = getClientFromServletRequest(httpServletRequest);
        List<CreditCardEntity> result  = creditCardRepository.findCreditCardEntitiesByBankUser(bankUser);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PostMapping("/purchase")
    public ResponseEntity<?> purchase (@RequestParam double price, @RequestParam String bonusCategory, @RequestParam long cardID, HttpServletRequest httpServletRequest) {

        if (price < 0.0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Negative price!");
        }

        CreditCardEntity creditCardEntity = creditCardRepository.getCreditCardEntityById(cardID);

        if (creditCardEntity == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("There is no credit card with such id!");
        }
        if (creditCardEntity.getMoneyOnCard() < price) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Not enough money on the card!");
        }

        InvoiceEntity invoiceEntity = new InvoiceEntity();
        invoiceEntity.setAmount(price);
        invoiceEntity.setDate(new Date(System.currentTimeMillis()));
        invoiceEntity.setCreditCardEntity(creditCardEntity);
        List<InvoiceEntity> list = invoiceRepository.getInvoiceEntitiesByCreditCardEntity(creditCardEntity);
        list.add(invoiceEntity);


        creditCardEntity.setMoneyOnCard(creditCardEntity.getMoneyOnCard() - price);
        BankUser bankUser = getClientFromServletRequest(httpServletRequest);
        BonusCountEntity bonusCountEntity = bankUser.getBonusCountEntity();
        bonusCountEntityService.countBonus(bankUser, bonusCountEntity, price, bonusCategory);


        return ResponseEntity.status(HttpStatus.OK).body("The purchase was successful!");
    }

    @GetMapping("/bonuses")
    public ResponseEntity<?> getBonuses(HttpServletRequest httpServletRequest) {
        BankUser bankUser = getClientFromServletRequest(httpServletRequest);
        BonusCountEntity result = bankUser.getBonusCountEntity();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping("/best-combination")
    public ResponseEntity<?> getBestCombination(HttpServletRequest httpServletRequest) {
        BankUser bankUser = getClientFromServletRequest(httpServletRequest);
        BonusCountEntity bonusCountEntity = bankUser.getBonusCountEntity();
        List<String> result = bonusCountEntityService.getBestCombination(bankUser, bonusCountEntity);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping("/get-bonus")
    public ResponseEntity<?> getBonus(HttpServletRequest httpServletRequest) {
        BankUser bankUser = getClientFromServletRequest(httpServletRequest);
        BonusCountEntity bonusCountEntity = bankUser.getBonusCountEntity();
        double result = bonusCountEntity.getCurrentBonus();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping("/maximum-bonus")
    public ResponseEntity<?> getMaximumBonus(HttpServletRequest httpServletRequest) {
        BankUser bankUser = getClientFromServletRequest(httpServletRequest);
        BonusCountEntity bonusCountEntity = bankUser.getBonusCountEntity();
        double result = bonusCountEntityService.getMaxBonus(bankUser, bonusCountEntity);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PostMapping("/categories")
    public ResponseEntity<?> setCategories(String category1, String category2, String category3, String category4, HttpServletRequest httpServletRequest) {
        BonusCategory bonusCategory1 = stringToBonusCategory(category1);
        BonusCategory bonusCategory2 = stringToBonusCategory(category2);
        BonusCategory bonusCategory3 = stringToBonusCategory(category3);
        BonusCategory bonusCategory4 = stringToBonusCategory(category4);

        BankUser bankUser = getClientFromServletRequest(httpServletRequest);

        bankUser.setBonusCategory1(bonusCategory1);
        bankUser.setBonusCategory2(bonusCategory2);
        bankUser.setBonusCategory3(bonusCategory3);
        bankUser.setBonusCategory4(bonusCategory4);

        return ResponseEntity.status(HttpStatus.OK).body("The categories have been set!");
    }

    @PostMapping("/set-bonus-level")
    public ResponseEntity<?> setBonusLevel(String level, HttpServletRequest httpServletRequest) {
        if (level == "" || level == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid input");
        }
        String s = level.toUpperCase();
        BankUser bankUser = getClientFromServletRequest(httpServletRequest);
        if (s.equals("SILVER")) {
            bankUser.setBonusLevel("SILVER");
            bankUser.setBasePercent(0.005);
        } else if (s.equals("GOLD")) {
            bankUser.setBonusLevel("GOLD");
            bankUser.setBasePercent(0.01);
        } else if (s.equals("PLATINUM")) {
            bankUser.setBonusLevel("PLATINUM");
            bankUser.setBasePercent(0.02);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("There is no such bonus level!");
        }
        return ResponseEntity.status(HttpStatus.OK).body("The level has been set!");
    }


    private BankUser getClientFromServletRequest(HttpServletRequest httpServletRequest) {
        Cookie[] cookies = httpServletRequest.getCookies();
        String token = "";
        String login = "";
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("token")) {
                token = cookie.getValue();
                login = jwtGenerator.getLoginFromToken(token);
                break;
            }
        }
        BankUser bankUser = bankUserRepository.findBankUserByLogin(login);
        return bankUser;
    }

    private BonusCategory stringToBonusCategory (String category) {
        String s = category.toUpperCase();
        if (s.equals(BonusCategory.BEAUTY_AND_COSMETICS.toString())) {
            return BonusCategory.BEAUTY_AND_COSMETICS;
        } else if (s.equals(BonusCategory.CINEMA_AND_MUSIC.toString())) {
            return BonusCategory.CINEMA_AND_MUSIC;
        } else if (s.equals(BonusCategory.CLOTHES.toString())) {
            return BonusCategory.CLOTHES;
        } else if (s.equals(BonusCategory.FITNESS.toString())) {
            return BonusCategory.FITNESS;
        } else if (s.equals(BonusCategory.FURNITURE.toString())) {
            return BonusCategory.FURNITURE;
        } else if (s.equals(BonusCategory.GAMES.toString())) {
            return BonusCategory.GAMES;
        } else if (s.equals(BonusCategory.MEDICINE.toString())) {
            return BonusCategory.MEDICINE;
        } else if (s.equals(BonusCategory.RESTAURANTS.toString())) {
            return BonusCategory.RESTAURANTS;
        } else if (s.equals(BonusCategory.TAXI.toString())) {
            return BonusCategory.TAXI;
        } else if (s.equals(BonusCategory.TRAVEL.toString())) {
            return BonusCategory.TRAVEL;
        } else {
            return BonusCategory.NONE;
        }
    }

    private double bonusCategoryToPercent (BonusCategory bonusCategory) {
        double result = 0.0;
        if (bonusCategory.equals(BonusCategory.CLOTHES)) {
            result = 0.05;
        } else if (bonusCategory.equals(BonusCategory.FITNESS)) {
            result = 0.05;
        } else if (bonusCategory.equals(BonusCategory.FURNITURE)) {
            result = 0.05;
        } else if (bonusCategory.equals(BonusCategory.MEDICINE)) {
            result = 0.05;
        } else if (bonusCategory.equals(BonusCategory.GAMES)) {
            result = 0.1;
        } else if (bonusCategory.equals(BonusCategory.BEAUTY_AND_COSMETICS)) {
            result = 0.07;
        } else if (bonusCategory.equals(BonusCategory.CINEMA_AND_MUSIC)) {
            result = 0.15;
        } else if (bonusCategory.equals(BonusCategory.RESTAURANTS)) {
            result = 0.05;
        } else if (bonusCategory.equals(BonusCategory.TRAVEL)) {
            result = 0.05;
        } else if (bonusCategory.equals(BonusCategory.TAXI)) {
            result = 0.05;
        }

        return result;
    }

}
