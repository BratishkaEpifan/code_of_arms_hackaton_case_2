package com.example.code_of_arms_hackaton_case_2.repository;

import com.example.code_of_arms_hackaton_case_2.entity.BankUser;
import com.example.code_of_arms_hackaton_case_2.entity.BonusCountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;


@Repository
public interface BonusCountEntityRepository extends JpaRepository<BonusCountEntity, Long > {

    BonusCountEntity findBonusCountEntityByBankUser(BankUser bankUser);

    @Modifying
    @Query("update BonusCountEntity b set b.clothesWithBonus = ?1, b.clothesWithoutBonus = ?2, b.currentBonus = ?3 where b.bonusId = ?4")
    void addBonusClothes(double clothesWithBonus, double clothesWithoutBonus, double currentBonus, Long bonusId);

}
