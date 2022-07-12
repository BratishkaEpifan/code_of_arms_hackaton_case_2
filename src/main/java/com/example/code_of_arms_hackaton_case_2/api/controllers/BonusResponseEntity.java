package com.example.code_of_arms_hackaton_case_2.api.controllers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@AllArgsConstructor
@NoArgsConstructor
@Data
public class BonusResponseEntity {
    private double expenditure;
    private double bonus;
    private double potentialBonus;
}
