package com.drive.hire.driverhire.service;

import com.drive.hire.driverhire.model.User;
import com.drive.hire.driverhire.repository.UserRespository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

  @Mock
  UserRespository userRespository;

  @Mock
  BCryptPasswordEncoder passwordEncoder;

  User user;

  @InjectMocks
  UserService userService;

  @BeforeEach
  void setUp() {
    user = User.builder()
        .id(1)
        .name("name")
        .email("email")
        .pass("pass")
        .phNum(12345)
        .build();
  }

  @Test
  public void registerNewUserTest_Success() {
    when(userRespository.isEmailExists(user.getEmail())).thenReturn(false);
    when(userRespository.registerUser(user)).thenReturn(true);
    String response = userService.registerUser(user);
    assertEquals("User registered successfully", response);
  }

  @Test
  public void registerNewUserTest_Fail() {
    when(userRespository.isEmailExists(user.getEmail())).thenReturn(false);
    when(userRespository.registerUser(user)).thenReturn(false);
    String response = userService.registerUser(user);
    assertEquals("User not registered", response);
  }

  @Test
  public void registerNewUserTest_Exists() {
    when(userRespository.isEmailExists(user.getEmail())).thenReturn(true);
    assertThrowsExactly(RuntimeException.class, () -> userService.registerUser(user), "User already exists");
  }
}
