package com.drive.hire.driverhire.repository;

import com.drive.hire.driverhire.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserRepositoryTest {

  @Mock
  MongoTemplate mongoTemplate;

  User user;

  @InjectMocks
  UserRespository userRepository;

  @BeforeEach
  public void setUp() {
    user = User.builder()
        .id(1)
        .name("name")
        .email("email")
        .pass("pass")
        .phNum(12345)
        .build();
  }

  @Test
  public void isEmailExists() {
    when(mongoTemplate.find(any(), any())).thenReturn(List.of(user));
    boolean emailExists = userRepository.isEmailExists(user.getEmail());
    assertTrue(emailExists);

  }

  @Test
  public void isEmailNotExists() {
    when(mongoTemplate.find(any(), any())).thenReturn(Collections.emptyList());
    boolean emailExists = userRepository.isEmailExists(user.getEmail());
    assertFalse(emailExists);

  }

  @Test
  public void testRegisterUser_Success() {
    when(mongoTemplate.insert(user)).thenReturn(user);
    boolean status = userRepository.registerUser(user);
    assertTrue(status);
  }

  @Test
  public void testRegisterDriver_Fail() {
    doThrow(new DataAccessResourceFailureException("Insertion Failed")).when(mongoTemplate).insert(user);
    boolean status = userRepository.registerUser(user);
    assertFalse(status);
  }
}
