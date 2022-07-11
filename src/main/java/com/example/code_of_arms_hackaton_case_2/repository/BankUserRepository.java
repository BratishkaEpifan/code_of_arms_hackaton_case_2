package com.example.code_of_arms_hackaton_case_2.repository;

import com.example.code_of_arms_hackaton_case_2.entity.BankUser;
import com.example.code_of_arms_hackaton_case_2.entity.BonusCountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BankUserRepository extends JpaRepository<BankUser, Long>  {


    BankUser findBankUserByLogin(String login);

    @Modifying
    @Query("update BankUser u set u.bonusLevel = ?1, u.basePercent = ?2 where u.login = ?3")
    void changeBonusLevel(String bonusLevel, double basePercent, String login);



}
