package com.alterra.iacss.controller;

import com.alterra.iacss.domain.dao.CardStatus;
import com.alterra.iacss.service.CardStatusService;
import com.alterra.iacss.service.LimitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/v1/card_status")
public class CardStatusController {

    @Autowired
    private CardStatusService cardStatusService;


    @GetMapping(value = "")
    public ResponseEntity<Object> getAllCardStatus() {
        return cardStatusService.getCardStatus();
    }
    
}
