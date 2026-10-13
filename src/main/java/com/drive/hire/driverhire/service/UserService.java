package com.drive.hire.driverhire.service;

import com.drive.hire.driverhire.model.User;
import com.drive.hire.driverhire.repository.UserRespository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserService {

  private final UserRespository userRespository;
  private final BCryptPasswordEncoder passwordEncoder;

  public UserService(UserRespository userRespository,
                     BCryptPasswordEncoder passwordEncoder) {
    this.userRespository = userRespository;
    this.passwordEncoder = passwordEncoder;
  }

  public String registerUser(User user) {
    boolean emailExists = userRespository.isEmailExists(user.getEmail());
    if (emailExists) {
      LOGGER.info("User already exists");
      throw new RuntimeException("User already exists");
    }
    user.setPass(passwordEncoder.encode(user.getPass()));
    boolean isRegistered = userRespository.registerUser(user);


    return isRegistered ? "User registered successfully" : "User not registered";
  }
}
