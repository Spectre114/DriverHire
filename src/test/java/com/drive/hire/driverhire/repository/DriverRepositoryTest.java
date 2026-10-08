package com.drive.hire.driverhire.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.drive.hire.driverhire.model.Driver;

@ExtendWith(MockitoExtension.class)
public class DriverRepositoryTest {

    @Mock
    MongoTemplate mongoTemplate;

    Driver driver;

    @InjectMocks
    DriverRepository driverRepository;

    @BeforeEach
    public void setUp() {
        driver = Driver.builder()
                .id(1)
                .name("Kartick")
                .pass("122345")
                .phNum(12345)
                .build();
    }

    @Test
    public void testInsertDriverSuccess() {
        when(mongoTemplate.insert(driver)).thenReturn(driver);
        String status = driverRepository.insertDriver(driver);
        assertEquals("Success", status);
    }

    @Test
    public void testInsertDriverNull() {
        doThrow(new DataAccessResourceFailureException("Insertion Failed")).when(mongoTemplate).insert(driver);
        String status = driverRepository.insertDriver(driver);
        assertEquals("Fail", status);
    }
}
