package com.example.code_of_arms_hackaton_case_2.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class InvoiceEntity {
    @Id
    @GeneratedValue
    private long invoiceId;
    private double amount;
    private Date date;
    @ManyToOne
    private CreditCardEntity creditCardEntity;
}
