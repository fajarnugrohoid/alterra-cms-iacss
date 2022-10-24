package com.alterra.iacss.repository;

import com.alterra.iacss.domain.dao.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {
    
    List<Card> findAllByBranchCode(String branchCode);

    Card findByCardNumberContaining(String cardNumber);

    List<Card> findAllByCardStatus(Long cardStatus);

}
