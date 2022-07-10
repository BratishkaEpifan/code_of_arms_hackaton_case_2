package com.example.code_of_arms_hackaton_case_2.service;

import com.example.code_of_arms_hackaton_case_2.entity.BankUser;
import com.example.code_of_arms_hackaton_case_2.entity.BonusCategory;
import com.example.code_of_arms_hackaton_case_2.entity.BonusCountEntity;
import org.springframework.stereotype.Service;

@Service
public class BonusCountEntityService {

    public void addBonus (BankUser bankUser, BonusCountEntity bonusCountEntity, double amount, String category) {

        double bonus = 0.0;
        double basePercent = bankUser.getBasePercent();
        BonusCategory bonusCategory1 = bankUser.getBonusCategory1();
        BonusCategory bonusCategory2 = bankUser.getBonusCategory2();
        BonusCategory bonusCategory3 = bankUser.getBonusCategory3();
        BonusCategory bonusCategory4 = bankUser.getBonusCategory4();


        String s = category.toUpperCase();
        if (s.equals(BonusCategory.BEAUTY_AND_COSMETICS.toString())) {
            if (s.equals(bonusCategory1.toString()) || s.equals(bonusCategory2.toString())
                    || s.equals(bonusCategory3.toString()) || s.equals(bonusCategory4.toString())) {
                bonus = 0.07*amount;
                bonusCountEntity.setBeautyAndCosmeticsWithBonus(bonusCountEntity.getBeautyAndCosmeticsWithBonus() + bonus);
            } else {
                bonusCountEntity.setBeautyAndCosmeticsWithoutBonus(bonusCountEntity.getBeautyAndCosmeticsWithoutBonus() + basePercent*amount);
            }
        } else if (s.equals(BonusCategory.CINEMA_AND_MUSIC.toString())) {
            if (s.equals(bonusCategory1.toString()) || s.equals(bonusCategory2.toString())
                    || s.equals(bonusCategory3.toString()) || s.equals(bonusCategory4.toString())) {
                bonus = 0.15*amount;
                bonusCountEntity.setCinemaAndMusicWithBonus(bonusCountEntity.getCinemaAndMusicWithBonus() + bonus);
            } else {
                bonusCountEntity.setCinemaAndMusicWithoutBonus(bonusCountEntity.getCinemaAndMusicWithoutBonus() + basePercent*amount);
            }
        } else if (s.equals(BonusCategory.CLOTHES.toString())) {
            if (s.equals(bonusCategory1.toString()) || s.equals(bonusCategory2.toString())
                    || s.equals(bonusCategory3.toString()) || s.equals(bonusCategory4.toString())) {
                bonus = 0.05*amount;
                bonusCountEntity.setClothesWithBonus(bonusCountEntity.getClothesWithBonus() + bonus);
            } else {
                bonusCountEntity.setClothesWithoutBonus(bonusCountEntity.getClothesWithoutBonus() + basePercent*amount);
            }
        } else if (s.equals(BonusCategory.FITNESS.toString())) {
            if (s.equals(bonusCategory1.toString()) || s.equals(bonusCategory2.toString())
                    || s.equals(bonusCategory3.toString()) || s.equals(bonusCategory4.toString())) {
                bonus = 0.05*amount;
                bonusCountEntity.setFitnessWithBonus(bonusCountEntity.getFitnessWithBonus() + bonus);
            } else {
                bonusCountEntity.setFitnessWithoutBonus(bonusCountEntity.getFitnessWithoutBonus() + basePercent*amount);
            }
        } else if (s.equals(BonusCategory.FURNITURE.toString())) {
            if (s.equals(bonusCategory1.toString()) || s.equals(bonusCategory2.toString())
                    || s.equals(bonusCategory3.toString()) || s.equals(bonusCategory4.toString())) {
                bonus = 0.05*amount;
                bonusCountEntity.setFurnitureWithBonus(bonusCountEntity.getFurnitureWithBonus() + bonus);
            } else {
                bonusCountEntity.setFurnitureWithoutBonus(bonusCountEntity.getFurnitureWithoutBonus() + basePercent*amount);
            }
        } else if (s.equals(BonusCategory.GAMES.toString())) {
            if (s.equals(bonusCategory1.toString()) || s.equals(bonusCategory2.toString())
                    || s.equals(bonusCategory3.toString()) || s.equals(bonusCategory4.toString())) {
                bonus = 0.1*amount;
                bonusCountEntity.setGamesWithBonus(bonusCountEntity.getGamesWithBonus() + bonus);
            } else {
                bonusCountEntity.setGamesWithoutBonus(bonusCountEntity.getGamesWithoutBonus() + basePercent*amount);
            }
        } else if (s.equals(BonusCategory.MEDICINE.toString())) {
            if (s.equals(bonusCategory1.toString()) || s.equals(bonusCategory2.toString())
                    || s.equals(bonusCategory3.toString()) || s.equals(bonusCategory4.toString())) {
                bonus = 0.05*amount;
                bonusCountEntity.setMedicineWithBonus(bonusCountEntity.getMedicineWithBonus() + bonus);
            } else {
                bonusCountEntity.setMedicineWithoutBonus(bonusCountEntity.getMedicineWithoutBonus() + basePercent*amount);
            }
        } else if (s.equals(BonusCategory.RESTAURANTS.toString())) {
            if (s.equals(bonusCategory1.toString()) || s.equals(bonusCategory2.toString())
                    || s.equals(bonusCategory3.toString()) || s.equals(bonusCategory4.toString())) {
                bonus = 0.05*amount;
                bonusCountEntity.setRestaurantsWithBonus(bonusCountEntity.getRestaurantsWithBonus() + bonus);
            } else {
                bonusCountEntity.setRestaurantsWithoutBonus(bonusCountEntity.getRestaurantsWithoutBonus() + basePercent*amount);
            }
        } else if (s.equals(BonusCategory.TAXI.toString())) {
            if (s.equals(bonusCategory1.toString()) || s.equals(bonusCategory2.toString())
                    || s.equals(bonusCategory3.toString()) || s.equals(bonusCategory4.toString())) {
                bonus = 0.05*amount;
                bonusCountEntity.setTaxiWithBonus(bonusCountEntity.getTaxiWithBonus() + bonus);
            } else {
                bonusCountEntity.setTaxiWithoutBonus(bonusCountEntity.getTaxiWithoutBonus() + basePercent*amount);
            }
        } else if (s.equals(BonusCategory.TRAVEL.toString())) {
            if (s.equals(bonusCategory1.toString()) || s.equals(bonusCategory2.toString())
                    || s.equals(bonusCategory3.toString()) || s.equals(bonusCategory4.toString())) {
                bonus = 0.05*amount;
                bonusCountEntity.setTravelWithBonus(bonusCountEntity.getTravelWithBonus() + bonus);
            } else {
                bonusCountEntity.setTravelWithoutBonus(bonusCountEntity.getTravelWithoutBonus() + basePercent*amount);
            }
        }
    }


}
