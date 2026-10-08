package com.drive.hire.driverhire.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
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
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.Collections;
import java.util.List;

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
    public void testInsertDriver_Success() {
        when(mongoTemplate.insert(driver)).thenReturn(driver);
        String status = driverRepository.insertDriver(driver);
        assertEquals("Success", status);
    }

    @Test
    public void testInsertDriver_Null() {
        doThrow(new DataAccessResourceFailureException("Insertion Failed")).when(mongoTemplate).insert(driver);
        String status = driverRepository.insertDriver(driver);
        assertEquals("Fail", status);
    }

    @Test
    public void testGetDriverById_Sucess() {
        when(mongoTemplate.find(any(Query.class), any())).thenReturn(Collections.singletonList(driver));
        List<Driver> fetchDriver = driverRepository.getDriver(1);
        assertEquals(1, fetchDriver.size());
    }

    @Test
    public void testGetDriverById_NotFound() {
        when(mongoTemplate.find(any(Query.class), any())).thenReturn(Collections.emptyList());
        List<Driver> fetchDriver = driverRepository.getDriver(1);
        assertEquals(0, fetchDriver.size());
    }
}
