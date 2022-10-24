package com.alterra.iacss.service;

import com.alterra.iacss.constant.AppConstant;
import com.alterra.iacss.domain.dao.LimitProfile;
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
public class LimitService {
    
    @Autowired
    private LimitProfileRepository limitProfileRepository;

    @Autowired
    private ModelMapper mapper;

    public ResponseEntity<Object> getLimitProfile() {
        log.info("Executing save new limit profile");
        try {
            List<LimitProfile> limitProfiles = limitProfileRepository.findAll();
            log.info( "limitProfiles:" + limitProfiles.size() );
            return ResponseUtil.build(AppConstant.ResponseCode.SUCCESS, limitProfiles, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Got an error when saving new limit profile. Error: {}", e.getMessage());
            return ResponseUtil.build(AppConstant.ResponseCode.UNKNOWN_ERROR, null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
