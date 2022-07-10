package com.example.code_of_arms_hackaton_case_2.repository;

import com.example.code_of_arms_hackaton_case_2.entity.BankUser;
import com.example.code_of_arms_hackaton_case_2.entity.BonusCountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;


@Repository
public interface BonusCountEntityRepository extends JpaRepository<BonusCountEntity, Long > {

    BonusCountEntity findBonusCountEntityByBankUser(BankUser bankUser);
}
