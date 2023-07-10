package com.example.tdd;

import com.example.tdd.model.BookingModel;
import com.example.tdd.service.BookingService;
import com.example.tdd.service.repository.BookingRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.event.annotation.BeforeTestMethod;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
public class BookingServiceTest {

    @TestConfiguration
    @ContextConfiguration( classes = BookingServiceTestConfiguration.class)
    static class BookingServiceTestConfiguration{

        @Bean
        public BookingService bookingService(){
            return new BookingService();
        }
    }

    @Autowired
    BookingService bookingService;

    @MockBean
    BookingRepository bookingRepository;

    @Test
    public void bookingTestServiceDaysCalculator(){
        String name = "Eliel";
        int days = bookingService.daysCalculatorDatabase(name);

        Assertions.assertEquals(days, 10);
    }

    @BeforeEach
    public void setup(){
        LocalDate checkin = LocalDate.parse("2020-11-10");
        LocalDate checkout = LocalDate.parse("2020-11-20");
        BookingModel bookingModel = new BookingModel("1", "Eliel", checkin, checkout, 2);

        Mockito.when(bookingRepository.finByReserveName(bookingModel.getReserveName()))
                .thenReturn(Optional.of(bookingModel));

    }
}
