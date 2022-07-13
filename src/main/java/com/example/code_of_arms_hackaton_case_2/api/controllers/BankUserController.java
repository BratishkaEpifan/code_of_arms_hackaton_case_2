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
import java.util.LinkedList;
import java.util.List;


@Transactional
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class BankUserController {
    private final CreditCardRepository creditCardRepository;
    private final BankUserRepository bankUserRepository;
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
        invoiceRepository.save(invoiceEntity);


        creditCardEntity.setMoneyOnCard(creditCardEntity.getMoneyOnCard() - price);
        BankUser bankUser = getClientFromServletRequest(httpServletRequest);
        BonusCountEntity bonusCountEntity = bankUser.getBonusCountEntity();
        bonusCountEntityService.countBonus(bankUser, bonusCountEntity, price, bonusCategory);


        return ResponseEntity.status(HttpStatus.OK).body("The purchase was successful!");
    }


    @GetMapping("/best-combination")
    public ResponseEntity<?> getBestCombination(HttpServletRequest httpServletRequest) {
        BankUser bankUser = getClientFromServletRequest(httpServletRequest);
        BonusCountEntity bonusCountEntity = bankUser.getBonusCountEntity();
        List<String> result = bonusCountEntityService.getBestCombination(bankUser, bonusCountEntity);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping("/current-combination")
    public ResponseEntity<?> getCurrentCombination(HttpServletRequest httpServletRequest) {
        BankUser bankUser = getClientFromServletRequest(httpServletRequest);
        List<String> result = new LinkedList<String>();
        result.add(bankUser.getBonusCategory1().toString());
        result.add(bankUser.getBonusCategory2().toString());
        result.add(bankUser.getBonusCategory3().toString());
        result.add(bankUser.getBonusCategory4().toString());
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping("/get-bonus")
    public ResponseEntity<?> getBonusEntity(HttpServletRequest httpServletRequest) {
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








    @GetMapping("/cinema-and-music")
    public ResponseEntity<?> getCinema(HttpServletRequest httpServletRequest) {
        BankUser bankUser = getClientFromServletRequest(httpServletRequest);
        BonusCountEntity bonusCountEntity = bankUser.getBonusCountEntity();
        BonusCategory bonusCategory1 = bankUser.getBonusCategory1();
        BonusCategory bonusCategory2 = bankUser.getBonusCategory2();
        BonusCategory bonusCategory3 = bankUser.getBonusCategory3();
        BonusCategory bonusCategory4 = bankUser.getBonusCategory4();
        String s = "CINEMA_AND_MUSIC";
        BonusResponseEntity result = new BonusResponseEntity();
        result.setExpenditure(bonusCountEntity.getCinemaAndMusicExpenditure());
        if (s.equals(bonusCategory1.toString()) || s.equals(bonusCategory2.toString())
                || s.equals(bonusCategory3.toString()) || s.equals(bonusCategory4.toString())) {
            result.setBonus(bonusCountEntity.getCinemaAndMusicWithBonus());
            result.setPotentialBonus(bonusCountEntity.getCinemaAndMusicWithoutBonus());
            return ResponseEntity.status(HttpStatus.OK).body(result);
        } else {
            result.setBonus(bonusCountEntity.getCinemaAndMusicWithoutBonus());
            result.setPotentialBonus(bonusCountEntity.getCinemaAndMusicWithBonus());
            return ResponseEntity.status(HttpStatus.OK).body(result);
        }
    }

    @GetMapping("/games")
    public ResponseEntity<?> getGames(HttpServletRequest httpServletRequest) {
        BankUser bankUser = getClientFromServletRequest(httpServletRequest);
        BonusCountEntity bonusCountEntity = bankUser.getBonusCountEntity();
        BonusCategory bonusCategory1 = bankUser.getBonusCategory1();
        BonusCategory bonusCategory2 = bankUser.getBonusCategory2();
        BonusCategory bonusCategory3 = bankUser.getBonusCategory3();
        BonusCategory bonusCategory4 = bankUser.getBonusCategory4();
        String s = "GAMES";
        BonusResponseEntity result = new BonusResponseEntity();
        result.setExpenditure(bonusCountEntity.getGamesExpenditure());
        if (s.equals(bonusCategory1.toString()) || s.equals(bonusCategory2.toString())
                || s.equals(bonusCategory3.toString()) || s.equals(bonusCategory4.toString())) {
            result.setBonus(bonusCountEntity.getGamesWithBonus());
            result.setPotentialBonus(bonusCountEntity.getGamesWithoutBonus());
            return ResponseEntity.status(HttpStatus.OK).body(result);
        } else {
            result.setBonus(bonusCountEntity.getGamesWithoutBonus());
            result.setPotentialBonus(bonusCountEntity.getGamesWithBonus());
            return ResponseEntity.status(HttpStatus.OK).body(result);
        }
    }

    @GetMapping("/beauty-and-cosmetics")
    public ResponseEntity<?> getBeautyAndCosmetics(HttpServletRequest httpServletRequest) {
        BankUser bankUser = getClientFromServletRequest(httpServletRequest);
        BonusCountEntity bonusCountEntity = bankUser.getBonusCountEntity();
        BonusCategory bonusCategory1 = bankUser.getBonusCategory1();
        BonusCategory bonusCategory2 = bankUser.getBonusCategory2();
        BonusCategory bonusCategory3 = bankUser.getBonusCategory3();
        BonusCategory bonusCategory4 = bankUser.getBonusCategory4();
        String s = "BEAUTY_AND_COSMETICS";
        BonusResponseEntity result = new BonusResponseEntity();
        result.setExpenditure(bonusCountEntity.getBeautyExpenditure());

        if (s.equals(bonusCategory1.toString()) || s.equals(bonusCategory2.toString())
                || s.equals(bonusCategory3.toString()) || s.equals(bonusCategory4.toString())) {

            result.setBonus(bonusCountEntity.getBeautyAndCosmeticsWithBonus());
            result.setPotentialBonus(bonusCountEntity.getBeautyAndCosmeticsWithoutBonus());
            return ResponseEntity.status(HttpStatus.OK).body(result);
        } else {
            result.setBonus(bonusCountEntity.getBeautyAndCosmeticsWithoutBonus());
            result.setPotentialBonus(bonusCountEntity.getBeautyAndCosmeticsWithBonus());
            return ResponseEntity.status(HttpStatus.OK).body(result);
        }
    }

    @GetMapping("/clothes")
    public ResponseEntity<?> getClothes(HttpServletRequest httpServletRequest) {
        BankUser bankUser = getClientFromServletRequest(httpServletRequest);
        BonusCountEntity bonusCountEntity = bankUser.getBonusCountEntity();
        BonusCategory bonusCategory1 = bankUser.getBonusCategory1();
        BonusCategory bonusCategory2 = bankUser.getBonusCategory2();
        BonusCategory bonusCategory3 = bankUser.getBonusCategory3();
        BonusCategory bonusCategory4 = bankUser.getBonusCategory4();
        String s = "CLOTHES";
        BonusResponseEntity result = new BonusResponseEntity();
        result.setExpenditure(bonusCountEntity.getClothesExpenditure());

        if (s.equals(bonusCategory1.toString()) || s.equals(bonusCategory2.toString())
                || s.equals(bonusCategory3.toString()) || s.equals(bonusCategory4.toString())) {
            result.setBonus(bonusCountEntity.getClothesWithBonus());
            result.setPotentialBonus(bonusCountEntity.getClothesWithoutBonus());
            return ResponseEntity.status(HttpStatus.OK).body(result);
        } else {
            result.setBonus(bonusCountEntity.getClothesWithoutBonus());
            result.setPotentialBonus(bonusCountEntity.getClothesWithBonus());
            return ResponseEntity.status(HttpStatus.OK).body(result);
        }
    }

    @GetMapping("/medicine")
    public ResponseEntity<?> getMedicine(HttpServletRequest httpServletRequest) {
        BankUser bankUser = getClientFromServletRequest(httpServletRequest);
        BonusCountEntity bonusCountEntity = bankUser.getBonusCountEntity();
        BonusCategory bonusCategory1 = bankUser.getBonusCategory1();
        BonusCategory bonusCategory2 = bankUser.getBonusCategory2();
        BonusCategory bonusCategory3 = bankUser.getBonusCategory3();
        BonusCategory bonusCategory4 = bankUser.getBonusCategory4();
        String s = "MEDICINE";
        BonusResponseEntity result = new BonusResponseEntity();
        result.setExpenditure(bonusCountEntity.getMedicineExpenditure());

        if (s.equals(bonusCategory1.toString()) || s.equals(bonusCategory2.toString())
                || s.equals(bonusCategory3.toString()) || s.equals(bonusCategory4.toString())) {
            result.setBonus(bonusCountEntity.getMedicineWithBonus());
            result.setPotentialBonus(bonusCountEntity.getMedicineWithoutBonus());
            return ResponseEntity.status(HttpStatus.OK).body(result);
        } else {
            result.setBonus(bonusCountEntity.getMedicineWithoutBonus());
            result.setPotentialBonus(bonusCountEntity.getMedicineWithBonus());
            return ResponseEntity.status(HttpStatus.OK).body(result);
        }
    }

    @GetMapping("/furniture")
    public ResponseEntity<?> getFurniture(HttpServletRequest httpServletRequest) {
        BankUser bankUser = getClientFromServletRequest(httpServletRequest);
        BonusCountEntity bonusCountEntity = bankUser.getBonusCountEntity();
        BonusCategory bonusCategory1 = bankUser.getBonusCategory1();
        BonusCategory bonusCategory2 = bankUser.getBonusCategory2();
        BonusCategory bonusCategory3 = bankUser.getBonusCategory3();
        BonusCategory bonusCategory4 = bankUser.getBonusCategory4();
        String s = "FURNITURE";
        BonusResponseEntity result = new BonusResponseEntity();
        result.setExpenditure(bonusCountEntity.getFurnitureExpenditure());
        if (s.equals(bonusCategory1.toString()) || s.equals(bonusCategory2.toString())
                || s.equals(bonusCategory3.toString()) || s.equals(bonusCategory4.toString())) {
            result.setBonus(bonusCountEntity.getFurnitureWithBonus());
            result.setPotentialBonus(bonusCountEntity.getFurnitureWithoutBonus());
            return ResponseEntity.status(HttpStatus.OK).body(result);
        } else {
            result.setBonus(bonusCountEntity.getFurnitureWithoutBonus());
            result.setPotentialBonus(bonusCountEntity.getFurnitureWithBonus());
            return ResponseEntity.status(HttpStatus.OK).body(result);
        }
    }

    @GetMapping("/restaurants")
    public ResponseEntity<?> getRestaurants(HttpServletRequest httpServletRequest) {
        BankUser bankUser = getClientFromServletRequest(httpServletRequest);
        BonusCountEntity bonusCountEntity = bankUser.getBonusCountEntity();
        BonusCategory bonusCategory1 = bankUser.getBonusCategory1();
        BonusCategory bonusCategory2 = bankUser.getBonusCategory2();
        BonusCategory bonusCategory3 = bankUser.getBonusCategory3();
        BonusCategory bonusCategory4 = bankUser.getBonusCategory4();
        String s = "RESTAURANTS";
        BonusResponseEntity result = new BonusResponseEntity();
        result.setExpenditure(bonusCountEntity.getRestaurantsExpenditure());

        if (s.equals(bonusCategory1.toString()) || s.equals(bonusCategory2.toString())
                || s.equals(bonusCategory3.toString()) || s.equals(bonusCategory4.toString())) {
            result.setBonus(bonusCountEntity.getRestaurantsWithBonus());
            result.setPotentialBonus(bonusCountEntity.getRestaurantsWithoutBonus());
            return ResponseEntity.status(HttpStatus.OK).body(result);
        } else {
            result.setBonus(bonusCountEntity.getRestaurantsWithoutBonus());
            result.setPotentialBonus(bonusCountEntity.getRestaurantsWithBonus());
            return ResponseEntity.status(HttpStatus.OK).body(result);
        }
    }

    @GetMapping("/taxi")
    public ResponseEntity<?> getTaxi(HttpServletRequest httpServletRequest) {
        BankUser bankUser = getClientFromServletRequest(httpServletRequest);
        BonusCountEntity bonusCountEntity = bankUser.getBonusCountEntity();
        BonusCategory bonusCategory1 = bankUser.getBonusCategory1();
        BonusCategory bonusCategory2 = bankUser.getBonusCategory2();
        BonusCategory bonusCategory3 = bankUser.getBonusCategory3();
        BonusCategory bonusCategory4 = bankUser.getBonusCategory4();
        String s = "TAXI";
        BonusResponseEntity result = new BonusResponseEntity();
        result.setExpenditure(bonusCountEntity.getTaxiExpenditure());

        if (s.equals(bonusCategory1.toString()) || s.equals(bonusCategory2.toString())
                || s.equals(bonusCategory3.toString()) || s.equals(bonusCategory4.toString())) {
            result.setBonus(bonusCountEntity.getTaxiWithBonus());
            result.setPotentialBonus(bonusCountEntity.getTaxiWithoutBonus());
            return ResponseEntity.status(HttpStatus.OK).body(result);
        } else {
            result.setBonus(bonusCountEntity.getTaxiWithoutBonus());
            result.setPotentialBonus(bonusCountEntity.getTaxiWithBonus());
            return ResponseEntity.status(HttpStatus.OK).body(result);
        }
    }

    @GetMapping("/travel")
    public ResponseEntity<?> getTravel(HttpServletRequest httpServletRequest) {
        BankUser bankUser = getClientFromServletRequest(httpServletRequest);
        BonusCountEntity bonusCountEntity = bankUser.getBonusCountEntity();
        BonusCategory bonusCategory1 = bankUser.getBonusCategory1();
        BonusCategory bonusCategory2 = bankUser.getBonusCategory2();
        BonusCategory bonusCategory3 = bankUser.getBonusCategory3();
        BonusCategory bonusCategory4 = bankUser.getBonusCategory4();
        String s = "TRAVEL";
        BonusResponseEntity result = new BonusResponseEntity();
        result.setExpenditure(bonusCountEntity.getTravelExpenditure());

        if (s.equals(bonusCategory1.toString()) || s.equals(bonusCategory2.toString())
                || s.equals(bonusCategory3.toString()) || s.equals(bonusCategory4.toString())) {
            result.setBonus(bonusCountEntity.getTravelWithBonus());
            result.setPotentialBonus(bonusCountEntity.getTravelWithoutBonus());
            return ResponseEntity.status(HttpStatus.OK).body(result);
        } else {
            result.setBonus(bonusCountEntity.getTravelWithoutBonus());
            result.setPotentialBonus(bonusCountEntity.getTravelWithBonus());
            return ResponseEntity.status(HttpStatus.OK).body(result);
        }
    }



    @GetMapping("/fitness")
    public ResponseEntity<?> getFitness(HttpServletRequest httpServletRequest) {
        BankUser bankUser = getClientFromServletRequest(httpServletRequest);
        BonusCountEntity bonusCountEntity = bankUser.getBonusCountEntity();
        BonusCategory bonusCategory1 = bankUser.getBonusCategory1();
        BonusCategory bonusCategory2 = bankUser.getBonusCategory2();
        BonusCategory bonusCategory3 = bankUser.getBonusCategory3();
        BonusCategory bonusCategory4 = bankUser.getBonusCategory4();
        String s = "FITNESS";
        BonusResponseEntity result = new BonusResponseEntity();
        result.setExpenditure(bonusCountEntity.getFitnessExpenditure());

        if (s.equals(bonusCategory1.toString()) || s.equals(bonusCategory2.toString())
                || s.equals(bonusCategory3.toString()) || s.equals(bonusCategory4.toString())) {
            result.setBonus(bonusCountEntity.getFitnessWithBonus());
            result.setPotentialBonus(bonusCountEntity.getFitnessWithoutBonus());
            return ResponseEntity.status(HttpStatus.OK).body(result);
        } else {
            result.setBonus(bonusCountEntity.getFitnessWithoutBonus());
            result.setPotentialBonus(bonusCountEntity.getFitnessWithBonus());
            return ResponseEntity.status(HttpStatus.OK).body(result);
        }
    }


    @GetMapping("/no-category")
    public ResponseEntity<?> getNoCategory(HttpServletRequest httpServletRequest) {
        BankUser bankUser = getClientFromServletRequest(httpServletRequest);
        BonusCountEntity bonusCountEntity = bankUser.getBonusCountEntity();
        BonusResponseEntity result = new BonusResponseEntity();
        result.setExpenditure(bonusCountEntity.getNoCategoryExpenditure());
        result.setBonus(bonusCountEntity.getNoCategoryBonus());
        result.setPotentialBonus(bonusCountEntity.getNoCategoryBonus());
        return ResponseEntity.status(HttpStatus.OK).body(result);
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
