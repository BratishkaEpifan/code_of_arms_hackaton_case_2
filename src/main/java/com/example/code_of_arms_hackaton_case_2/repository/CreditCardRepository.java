package com.example.code_of_arms_hackaton_case_2.repository;

import com.example.code_of_arms_hackaton_case_2.entity.BankUser;
import com.example.code_of_arms_hackaton_case_2.entity.CreditCardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CreditCardRepository extends JpaRepository<CreditCardEntity, Long> {

    List<CreditCardEntity> findCreditCardEntitiesByBankUser(BankUser bankUser);

    CreditCardEntity getCreditCardEntityById(Long id);

}
