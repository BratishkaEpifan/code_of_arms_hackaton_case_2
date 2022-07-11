package com.example.code_of_arms_hackaton_case_2.service;

import com.example.code_of_arms_hackaton_case_2.entity.BankUser;
import com.example.code_of_arms_hackaton_case_2.entity.BonusCategory;
import com.example.code_of_arms_hackaton_case_2.entity.BonusCountEntity;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

@Service
public class BonusCountEntityService {

    public void countBonus(BankUser bankUser, BonusCountEntity bonusCountEntity, double amount, String category) {

        double bonus = 0.0;
        double basePercent = bankUser.getBasePercent();
        BonusCategory bonusCategory1 = bankUser.getBonusCategory1();
        BonusCategory bonusCategory2 = bankUser.getBonusCategory2();
        BonusCategory bonusCategory3 = bankUser.getBonusCategory3();
        BonusCategory bonusCategory4 = bankUser.getBonusCategory4();


        String s = category.toUpperCase();
        if (s.equals(BonusCategory.BEAUTY_AND_COSMETICS.toString())) {
            bonus = 0.07*amount;
            bonusCountEntity.setBeautyAndCosmeticsWithBonus(bonusCountEntity.getBeautyAndCosmeticsWithBonus() + bonus);
            bonusCountEntity.setBeautyAndCosmeticsWithoutBonus(bonusCountEntity.getBeautyAndCosmeticsWithoutBonus() + basePercent*amount);
            if (s.equals(bonusCategory1.toString()) || s.equals(bonusCategory2.toString())
                    || s.equals(bonusCategory3.toString()) || s.equals(bonusCategory4.toString())) {
                bonusCountEntity.setCurrentBonus(bonusCountEntity.getCurrentBonus() + bonus);
            } else {
                bonusCountEntity.setCurrentBonus(bonusCountEntity.getCurrentBonus() + basePercent*amount);
            }
        } else if (s.equals(BonusCategory.CINEMA_AND_MUSIC.toString())) {
            bonus = 0.15*amount;
            bonusCountEntity.setCinemaAndMusicWithBonus(bonusCountEntity.getCinemaAndMusicWithBonus() + bonus);
            bonusCountEntity.setCinemaAndMusicWithoutBonus(bonusCountEntity.getCinemaAndMusicWithoutBonus() + basePercent*amount);
            if (s.equals(bonusCategory1.toString()) || s.equals(bonusCategory2.toString())
                    || s.equals(bonusCategory3.toString()) || s.equals(bonusCategory4.toString())) {
                bonusCountEntity.setCurrentBonus(bonusCountEntity.getCurrentBonus() + bonus);
            } else {
                bonusCountEntity.setCurrentBonus(bonusCountEntity.getCurrentBonus() + basePercent*amount);
            }
        } else if (s.equals(BonusCategory.CLOTHES.toString())) {
            bonus = 0.05*amount;
            bonusCountEntity.setClothesWithBonus(bonusCountEntity.getClothesWithBonus() + bonus);
            bonusCountEntity.setClothesWithoutBonus(bonusCountEntity.getClothesWithoutBonus() + basePercent*amount);
            if (s.equals(bonusCategory1.toString()) || s.equals(bonusCategory2.toString())
                    || s.equals(bonusCategory3.toString()) || s.equals(bonusCategory4.toString())) {
                bonusCountEntity.setCurrentBonus(bonusCountEntity.getCurrentBonus() + bonus);
            } else {
                bonusCountEntity.setCurrentBonus(bonusCountEntity.getCurrentBonus() + basePercent*amount);
            }
        } else if (s.equals(BonusCategory.FITNESS.toString())) {
            bonus = 0.05*amount;
            bonusCountEntity.setFitnessWithBonus(bonusCountEntity.getFitnessWithBonus() + bonus);
            bonusCountEntity.setFitnessWithoutBonus(bonusCountEntity.getFitnessWithoutBonus() + basePercent*amount);
            if (s.equals(bonusCategory1.toString()) || s.equals(bonusCategory2.toString())
                    || s.equals(bonusCategory3.toString()) || s.equals(bonusCategory4.toString())) {
                bonusCountEntity.setCurrentBonus(bonusCountEntity.getCurrentBonus() + bonus);
            } else {
                bonusCountEntity.setCurrentBonus(bonusCountEntity.getCurrentBonus() + basePercent*amount);
            }
        } else if (s.equals(BonusCategory.FURNITURE.toString())) {
            bonus = 0.05*amount;
            bonusCountEntity.setFurnitureWithBonus(bonusCountEntity.getFurnitureWithBonus() + bonus);
            bonusCountEntity.setFurnitureWithoutBonus(bonusCountEntity.getFurnitureWithoutBonus() + basePercent*amount);
            if (s.equals(bonusCategory1.toString()) || s.equals(bonusCategory2.toString())
                    || s.equals(bonusCategory3.toString()) || s.equals(bonusCategory4.toString())) {
                bonusCountEntity.setCurrentBonus(bonusCountEntity.getCurrentBonus() + bonus);
            } else {
                bonusCountEntity.setCurrentBonus(bonusCountEntity.getCurrentBonus() + basePercent*amount);
            }
        } else if (s.equals(BonusCategory.GAMES.toString())) {
            bonus = 0.1*amount;
            bonusCountEntity.setGamesWithBonus(bonusCountEntity.getGamesWithBonus() + bonus);
            bonusCountEntity.setGamesWithoutBonus(bonusCountEntity.getGamesWithoutBonus() + basePercent*amount);
            if (s.equals(bonusCategory1.toString()) || s.equals(bonusCategory2.toString())
                    || s.equals(bonusCategory3.toString()) || s.equals(bonusCategory4.toString())) {
                bonusCountEntity.setCurrentBonus(bonusCountEntity.getCurrentBonus() + bonus);
            } else {
                bonusCountEntity.setCurrentBonus(bonusCountEntity.getCurrentBonus() + basePercent*amount);
            }
        } else if (s.equals(BonusCategory.MEDICINE.toString())) {
            bonus = 0.05*amount;
            bonusCountEntity.setMedicineWithBonus(bonusCountEntity.getMedicineWithBonus() + bonus);
            bonusCountEntity.setMedicineWithoutBonus(bonusCountEntity.getMedicineWithoutBonus() + basePercent*amount);
            if (s.equals(bonusCategory1.toString()) || s.equals(bonusCategory2.toString())
                    || s.equals(bonusCategory3.toString()) || s.equals(bonusCategory4.toString())) {
                bonusCountEntity.setCurrentBonus(bonusCountEntity.getCurrentBonus() + bonus);
            } else {
                bonusCountEntity.setCurrentBonus(bonusCountEntity.getCurrentBonus() + basePercent*amount);
            }
        } else if (s.equals(BonusCategory.RESTAURANTS.toString())) {
            bonus = 0.05*amount;
            bonusCountEntity.setRestaurantsWithBonus(bonusCountEntity.getRestaurantsWithBonus() + bonus);
            bonusCountEntity.setRestaurantsWithoutBonus(bonusCountEntity.getRestaurantsWithoutBonus() + basePercent*amount);
            if (s.equals(bonusCategory1.toString()) || s.equals(bonusCategory2.toString())
                    || s.equals(bonusCategory3.toString()) || s.equals(bonusCategory4.toString())) {
                bonusCountEntity.setCurrentBonus(bonusCountEntity.getCurrentBonus() + bonus);
            } else {
                bonusCountEntity.setCurrentBonus(bonusCountEntity.getCurrentBonus() + basePercent*amount);
            }
        } else if (s.equals(BonusCategory.TAXI.toString())) {
            bonus = 0.05*amount;
            bonusCountEntity.setTaxiWithBonus(bonusCountEntity.getTaxiWithBonus() + bonus);
            bonusCountEntity.setTaxiWithoutBonus(bonusCountEntity.getTaxiWithoutBonus() + basePercent*amount);
            if (s.equals(bonusCategory1.toString()) || s.equals(bonusCategory2.toString())
                    || s.equals(bonusCategory3.toString()) || s.equals(bonusCategory4.toString())) {
                bonusCountEntity.setCurrentBonus(bonusCountEntity.getCurrentBonus() + bonus);
            } else {
                bonusCountEntity.setCurrentBonus(bonusCountEntity.getCurrentBonus() + basePercent*amount);
            }
        } else if (s.equals(BonusCategory.TRAVEL.toString())) {
            bonus = 0.05*amount;
            bonusCountEntity.setTravelWithBonus(bonusCountEntity.getTravelWithBonus() + bonus);
            bonusCountEntity.setTravelWithoutBonus(bonusCountEntity.getTravelWithoutBonus() + basePercent*amount);
            if (s.equals(bonusCategory1.toString()) || s.equals(bonusCategory2.toString())
                    || s.equals(bonusCategory3.toString()) || s.equals(bonusCategory4.toString())) {
                bonusCountEntity.setCurrentBonus(bonusCountEntity.getCurrentBonus() + bonus);
            } else {
                bonusCountEntity.setCurrentBonus(bonusCountEntity.getCurrentBonus() + basePercent*amount);
            }
        }
    }

    public List<String> getBestCombination(BankUser bankUser, BonusCountEntity bonusCountEntity) {

        double[] d = new double[10];

        d[0] = bonusCountEntity.getBeautyAndCosmeticsWithBonus() - bonusCountEntity.getBeautyAndCosmeticsWithoutBonus();
        d[1] = bonusCountEntity.getTaxiWithBonus() - bonusCountEntity.getTaxiWithoutBonus();
        d[2] = bonusCountEntity.getCinemaAndMusicWithBonus() - bonusCountEntity.getCinemaAndMusicWithoutBonus();
        d[3] = bonusCountEntity.getClothesWithBonus() - bonusCountEntity.getClothesWithoutBonus();
        d[4] = bonusCountEntity.getFitnessWithBonus() - bonusCountEntity.getFitnessWithoutBonus();
        d[5] = bonusCountEntity.getFurnitureWithBonus() - bonusCountEntity.getFurnitureWithoutBonus();
        d[6] = bonusCountEntity.getGamesWithBonus() - bonusCountEntity.getGamesWithoutBonus();
        d[7] = bonusCountEntity.getMedicineWithBonus() - bonusCountEntity.getMedicineWithoutBonus();
        d[8] = bonusCountEntity.getRestaurantsWithBonus() - bonusCountEntity.getRestaurantsWithoutBonus();
        d[9] = bonusCountEntity.getTravelWithBonus() - bonusCountEntity.getTravelWithoutBonus();

        HashMap<Double, String> hashMap = new HashMap<>();
        hashMap.put(d[0], "BEAUTY_AND_COSMETICS");
        hashMap.put(d[1], "TAXI");
        hashMap.put(d[2], "CINEMA_AND_MUSIC");
        hashMap.put(d[3], "CLOTHES");
        hashMap.put(d[4], "FITNESS");
        hashMap.put(d[5], "FURNITURE");
        hashMap.put(d[6], "GAMES");
        hashMap.put(d[7], "MEDICINE");
        hashMap.put(d[8], "RESTAURANTS");
        hashMap.put(d[9], "TRAVEL");

        Arrays.sort(d);
        int n = 0;
        if (bankUser.getBonusLevel().equals("SILVER")) {
            n = 1;
        } else if (bankUser.getBonusLevel().equals("GOLD")) {
            n = 2;
        } else if (bankUser.getBonusLevel().equals("PLATINUM")) {
            n = 4;
        }
        List<String> result = new LinkedList<>();

        for (int i = 9; i > 9-n; i--) {
            result.add(hashMap.get(d[i]));
        }
        return result;
    }

    public double getMaxBonus(BankUser bankUser, BonusCountEntity bonusCountEntity) {

        List<String> categories = getBestCombination(bankUser, bonusCountEntity);
        double result = 0.0;

        if (categories.contains("BEAUTY_AND_COSMETICS")) {
            result = result + bonusCountEntity.getBeautyAndCosmeticsWithBonus();
        } else {
            result = result + bonusCountEntity.getBeautyAndCosmeticsWithoutBonus();
        }
        if (categories.contains("TAXI")) {
            result = result + bonusCountEntity.getTaxiWithBonus();
        } else {
            result = result + bonusCountEntity.getTaxiWithoutBonus();
        }
        if (categories.contains("CINEMA_AND_MUSIC")) {
            result = result + bonusCountEntity.getCinemaAndMusicWithBonus();
        } else {
            result = result + bonusCountEntity.getCinemaAndMusicWithoutBonus();
        }
        if (categories.contains("CLOTHES")) {
            result = result + bonusCountEntity.getClothesWithBonus();
        } else {
            result = result + bonusCountEntity.getClothesWithoutBonus();
        }
        if (categories.contains("FITNESS")) {
            result = result + bonusCountEntity.getFitnessWithBonus();
        } else {
            result = result + bonusCountEntity.getFitnessWithoutBonus();
        }
        if (categories.contains("FURNITURE")) {
            result = result + bonusCountEntity.getFurnitureWithBonus();
        } else {
            result = result + bonusCountEntity.getFurnitureWithoutBonus();
        }
        if (categories.contains("GAMES")) {
            result = result + bonusCountEntity.getGamesWithBonus();
        } else {
            result = result + bonusCountEntity.getGamesWithoutBonus();
        }
        if (categories.contains("MEDICINE")) {
            result = result + bonusCountEntity.getMedicineWithBonus();
        } else {
            result = result + bonusCountEntity.getMedicineWithoutBonus();
        }
        if (categories.contains("RESTAURANTS")) {
            result = result + bonusCountEntity.getRestaurantsWithBonus();
        } else {
            result = result + bonusCountEntity.getRestaurantsWithoutBonus();
        }
        if (categories.contains("TRAVEL")) {
            result = result + bonusCountEntity.getTravelWithBonus();
        } else {
            result = result + bonusCountEntity.getTravelWithoutBonus();
        }

        return result;
    }


}
