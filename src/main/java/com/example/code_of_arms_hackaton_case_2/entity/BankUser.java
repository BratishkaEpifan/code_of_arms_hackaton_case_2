package com.example.code_of_arms_hackaton_case_2.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class BankUser {

    @Id
    @GeneratedValue
    private long userId;

    private String login;
    private String password;

    private String role;

    @Column(name = "bonus_level")
    private String bonusLevel;
    @Column(name = "base_percent")
    private double basePercent;
    @Column(name = "bonus_category_1")
    private BonusCategory bonusCategory1;
    @Column(name = "bonus_category_2")
    private BonusCategory bonusCategory2;
    @Column(name = "bonus_category_3")
    private BonusCategory bonusCategory3;
    @Column(name = "bonus_category_4")
    private BonusCategory bonusCategory4;

    @OneToOne(targetEntity = BonusCountEntity.class, cascade = CascadeType.ALL)
    @JoinColumn (
            name = "bonus_id",
            referencedColumnName = "bonusId"
    )
    private BonusCountEntity bonusCountEntity;


    @OneToMany(mappedBy = "bankUser")
    private List<CreditCardEntity> cards;



//    public BankUser (long id, String login, String password, String role, String bonusLevel, double basePercent, BonusCategory bonusCategory1, BonusCategory bonusCategory2,
//                     BonusCategory bonusCategory3, BonusCategory bonusCategory4) {
//        this.id = id;
//        this.login = login;
//        this.password = password;
//        this.role = role;
//        this.bonusLevel = bonusLevel;
//        this.basePercent = basePercent;
//        this.bonusCategory1 = bonusCategory1;
//        this.bonusCategory2 = bonusCategory2;
//        this.bonusCategory3 = bonusCategory3;
//        this.bonusCategory4 = bonusCategory4;
//    }

}
