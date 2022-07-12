package com.example.code_of_arms_hackaton_case_2.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BonusCountEntity {
    @Id
    @GeneratedValue
    private long bonusId;


    @Column(name = "current_bonus")
    private double currentBonus;


    @Column(name = "total_expenditure")
    private double totalExpenditure;


    @Column(name = "cinema_and_music_without_bonus")
    private double cinemaAndMusicWithoutBonus;
    @Column(name = "games_without_bonus")
    private double gamesWithoutBonus;
    @Column(name = "beauty_and_cosmetics_without_bonus")
    private double beautyAndCosmeticsWithoutBonus;
    @Column(name = "clothes_without_bonus")
    private double clothesWithoutBonus;
    @Column(name = "medicine_without_bonus")
    private double medicineWithoutBonus;
    @Column(name = "furniture_without_bonus")
    private double furnitureWithoutBonus;
    @Column(name = "restaurants_without_bonus")
    private double restaurantsWithoutBonus;
    @Column(name = "taxi_without_bonus")
    private double taxiWithoutBonus;
    @Column(name = "travel_without_bonus")
    private double travelWithoutBonus;
    @Column(name = "fitness_without_bonus")
    private double fitnessWithoutBonus;

    @Column(name = "no_category_bonus")
    private double noCategoryBonus;


    @Column(name = "cinema_and_music_with_bonus")
    private double cinemaAndMusicWithBonus;
    @Column(name = "games_with_bonus")
    private double gamesWithBonus;
    @Column(name = "beauty_and_cosmetics_with_bonus")
    private double beautyAndCosmeticsWithBonus;
    @Column(name = "clothes_with_bonus")
    private double clothesWithBonus;
    @Column(name = "medicine_with_bonus")
    private double medicineWithBonus;
    @Column(name = "furniture_with_bonus")
    private double furnitureWithBonus;
    @Column(name = "restaurant_with_bonus")
    private double restaurantsWithBonus;
    @Column(name = "taxi_with_bonus")
    private double taxiWithBonus;
    @Column(name = "travel_with_bonus")
    private double travelWithBonus;
    @Column(name = "fitness_with_bonus")
    private double fitnessWithBonus;


    @Column(name = "cinema_and_music_expenditure")
    private double cinemaAndMusicExpenditure;
    @Column(name = "games_expenditure")
    private double gamesExpenditure;
    @Column(name = "beauty_and_cosmetics_expenditure")
    private double beautyExpenditure;
    @Column(name = "clothes_expenditure")
    private double clothesExpenditure;
    @Column(name = "medicine_expenditure")
    private double medicineExpenditure;
    @Column(name = "furniture_expenditure")
    private double furnitureExpenditure;
    @Column(name = "restaurant_expenditure")
    private double restaurantsExpenditure;
    @Column(name = "taxi_expenditure")
    private double taxiExpenditure;
    @Column(name = "travel_expenditure")
    private double travelExpenditure;
    @Column(name = "fitness_expenditure")
    private double fitnessExpenditure;
    @Column(name = "no_category_expenditure")
    private double noCategoryExpenditure;



//    CINEMA_AND_MUSIC, //15%
//    GAMES, //10%
//    BEAUTY_AND_COSMETICS, //7%
//    CLOTHES, //5%
//    MEDICINE, //5%
//    FURNITURE, //5%
//    RESTAURANTS, //5%
//    TAXI, //5%
//    TRAVEL, //5%
//    FITNESS; //5%
}
