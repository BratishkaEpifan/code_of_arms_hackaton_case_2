package com.example.code_of_arms_hackaton_case_2.repository;

import com.example.code_of_arms_hackaton_case_2.entity.CreditCardEntity;
import com.example.code_of_arms_hackaton_case_2.entity.InvoiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface InvoiceRepository extends JpaRepository<InvoiceEntity, Long> {

    List<InvoiceEntity> getInvoiceEntitiesByCreditCardEntity(CreditCardEntity creditCardEntity);
}
