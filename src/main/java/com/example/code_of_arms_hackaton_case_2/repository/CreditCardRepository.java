package com.example.code_of_arms_hackaton_case_2.repository;

import com.example.code_of_arms_hackaton_case_2.entity.BankUser;
import com.example.code_of_arms_hackaton_case_2.entity.CreditCardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CreditCardRepository extends JpaRepository<CreditCardEntity, Long> {

    List<CreditCardEntity> findCreditCardEntitiesByBankUser(BankUser bankUser);

    CreditCardEntity getCreditCardEntityById(Long id);

    @Modifying
    @Query("update CreditCardEntity c set c.moneyOnCard = ?1 where c.id = ?2")
    void updateCreditCard(double amount, Long cardId);

}
