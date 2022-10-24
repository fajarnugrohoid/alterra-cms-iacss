package com.alterra.iacss.controller;

import com.alterra.iacss.service.LimitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/v1/limit")
public class LimitProfileController {

    @Autowired
    private LimitService limitService;


    @GetMapping(value = "")
    public ResponseEntity<Object> getAllLimit() {
        return limitService.getLimitProfile();
    }
    
}
