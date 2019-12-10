package com.tech.batch.demo.schedule.service;

import com.tech.batch.demo.schedule.dao.UtilDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FileToDatabaseService {
    private static final Logger logger = LoggerFactory.getLogger(FileToDatabaseService.class);

    @Autowired
    private UtilDAO utilDAO;

    @Transactional
    public void init(){
        utilDAO.cleanCustomers();
    }
}
