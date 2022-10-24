package com.alterra.iacss.repository;

import com.alterra.iacss.domain.dao.LimitProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LimitProfileRepository extends JpaRepository<LimitProfile, Long> {
    
}
