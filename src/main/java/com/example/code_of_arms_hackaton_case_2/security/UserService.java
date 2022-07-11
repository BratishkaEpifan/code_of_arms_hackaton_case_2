package com.example.code_of_arms_hackaton_case_2.security;

import com.example.code_of_arms_hackaton_case_2.entity.BankUser;
import com.example.code_of_arms_hackaton_case_2.entity.BonusCategory;
import com.example.code_of_arms_hackaton_case_2.entity.BonusCountEntity;
import com.example.code_of_arms_hackaton_case_2.repository.BankUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {
    private final BankUserRepository bankUserRepository;
    private final PasswordEncoder passwordEncoder;

    public void saveUser(BankUser bankUser) {
        bankUser.setRole("ROLE_USER");
        bankUser.setPassword(passwordEncoder.encode(bankUser.getPassword()));
        bankUser.setBonusLevel("SILVER");
        bankUser.setBasePercent(0.005);
        bankUser.setBonusCategory1(BonusCategory.NONE);
        bankUser.setBonusCategory2(BonusCategory.NONE);
        bankUser.setBonusCategory3(BonusCategory.NONE);
        bankUser.setBonusCategory4(BonusCategory.NONE);

        BonusCountEntity bonusCountEntity = new BonusCountEntity();
        bonusCountEntity.setCurrentBonus(0.0);
        bonusCountEntity.setBeautyAndCosmeticsWithBonus(0);
        bonusCountEntity.setBeautyAndCosmeticsWithoutBonus(0);
        bonusCountEntity.setCinemaAndMusicWithBonus(0);
        bonusCountEntity.setCinemaAndMusicWithoutBonus(0);
        bonusCountEntity.setFitnessWithoutBonus(0);
        bonusCountEntity.setFitnessWithBonus(0);
        bonusCountEntity.setClothesWithBonus(0);
        bonusCountEntity.setClothesWithoutBonus(0);
        bonusCountEntity.setTaxiWithBonus(0);
        bonusCountEntity.setTaxiWithoutBonus(0);
        bonusCountEntity.setFurnitureWithBonus(0);
        bonusCountEntity.setFurnitureWithoutBonus(0);
        bonusCountEntity.setMedicineWithBonus(0);
        bonusCountEntity.setMedicineWithoutBonus(0);
        bonusCountEntity.setGamesWithoutBonus(0);
        bonusCountEntity.setGamesWithBonus(0);
        bonusCountEntity.setRestaurantsWithoutBonus(0);
        bonusCountEntity.setRestaurantsWithBonus(0);
        bonusCountEntity.setTravelWithBonus(0);
        bonusCountEntity.setTravelWithoutBonus(0);

        bankUser.setBonusCountEntity(bonusCountEntity);
        bankUserRepository.save(bankUser);
    }

    public BankUser findByLogin(String login) {
        return bankUserRepository.findBankUserByLogin(login);
    }

    public BankUser findByLoginAndPassword(String login, String password) {
        BankUser bankUser = bankUserRepository.findBankUserByLogin(login);
        if (bankUser != null) {
            if (passwordEncoder.matches(password, bankUser.getPassword())) {
                return bankUser;
            }
        }
        return null;
    }

}
