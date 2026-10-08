package com.drive.hire.driverhire.repository;

import com.drive.hire.driverhire.model.Driver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;


import java.util.List;

@Component
@Slf4j
public class DriverRepository {

    private final MongoTemplate mongoTemplate;

    public DriverRepository(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public String insertDriver(Driver driver) {
        try {
            mongoTemplate.insert(driver);
            return "Success";
        } catch (DataAccessException e) {
            LOGGER.error(String.valueOf(e));
            return "Fail";
        }
    }
    public List<Driver> getDriver(int driverId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(driverId));
        return mongoTemplate.find(query, Driver.class);
    }
}
