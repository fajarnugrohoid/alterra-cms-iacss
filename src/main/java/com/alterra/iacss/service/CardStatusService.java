package com.alterra.iacss.service;

import com.alterra.iacss.constant.AppConstant;
import com.alterra.iacss.domain.dao.CardStatus;
import com.alterra.iacss.domain.dao.LimitProfile;
import com.alterra.iacss.repository.CardStatusRepository;
import com.alterra.iacss.repository.LimitProfileRepository;
import com.alterra.iacss.util.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class CardStatusService {
    
    @Autowired
    private CardStatusRepository cardStatusRepository;

    @Autowired
    private ModelMapper mapper;

    public ResponseEntity<Object> getCardStatus() {
        log.info("Executing save new card status");
        try {
            List<CardStatus> cardStatuses = cardStatusRepository.findAll();
            log.info( "cardStatuses:" + cardStatuses.size() );
            return ResponseUtil.build(AppConstant.ResponseCode.SUCCESS, cardStatuses, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Got an error when saving new card status. Error: {}", e.getMessage());
            return ResponseUtil.build(AppConstant.ResponseCode.UNKNOWN_ERROR, null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
