package com.example.code_of_arms_hackaton_case_2.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class CreditCardEntity {
    @Id
    @GeneratedValue
    private long id;
    @Column(name = "money_on_card")
    private double moneyOnCard;

    @OneToMany(mappedBy = "creditCardEntity", cascade = CascadeType.ALL)
    private List<InvoiceEntity> invoices;

    @ManyToOne(targetEntity = BankUser.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "userId")
    private BankUser bankUser;
}
