package com.tech.batch.demo.schedule.dao;

import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UtilDAO {
    private static final Logger logger = LoggerFactory.getLogger(UtilDAO.class);

    private DataSource dataSource;

    private JdbcTemplate jdbcTemplate;
    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void cleanCustomers() {
        this.jdbcTemplate.update("truncate table demo_customer");
        logger.info("clean demo_customer: truncate table demo_customer");
    }
}
