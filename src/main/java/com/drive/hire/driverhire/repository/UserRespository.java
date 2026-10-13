package com.drive.hire.driverhire.repository;

import com.drive.hire.driverhire.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class UserRespository {

  private final MongoTemplate mongoTemplate;

  public UserRespository(MongoTemplate mongoTemplate) {
    this.mongoTemplate = mongoTemplate;
  }

  public boolean isEmailExists(String email) {
    Query query = new Query(Criteria.where("email").is(email));
    List<User> records = mongoTemplate.find(query, User.class);
    return !records.isEmpty();
  }

  public boolean registerUser(User user) {

    try {
      mongoTemplate.insert(user);
      return true;
    } catch (DataAccessException e) {
      LOGGER.error(String.valueOf(e));
      return false;
    }
  }
}
