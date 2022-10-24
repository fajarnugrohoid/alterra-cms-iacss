package com.alterra.iacss.repository;

import com.alterra.iacss.domain.dao.CardStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardStatusRepository extends JpaRepository<CardStatus, Long> {
    
}
