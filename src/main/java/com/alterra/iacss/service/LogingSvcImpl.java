package com.alterra.iacss.service;

import com.alterra.iacss.repository.LoggingRepository;
import com.alterra.iacss.domain.dao.LoggingModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.UUID;

@Service
public class LogingSvcImpl implements LoggingSvc{

    private final LoggingRepository loggingRepository;

    @Autowired
    public LogingSvcImpl(LoggingRepository loggingRepository) {
        this.loggingRepository = loggingRepository;
    }

    @Override
    public void createLog(HashMap<String, Object> map, String type) {
        LoggingModel logging = new LoggingModel();
        logging.setId(UUID.randomUUID().toString().replaceAll("-", ""));
        logging.setMap(map);
        logging.setType(type);
        loggingRepository.save(logging);
    }
}
