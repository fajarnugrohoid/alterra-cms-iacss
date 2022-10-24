package com.alterra.iacss.service;

import java.util.HashMap;

public interface LoggingSvc {

    void createLog(HashMap<String, Object> map, String type);
}
