package com.example.tdd.service;

import com.example.tdd.model.BookingModel;
import com.example.tdd.service.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Period;
import java.util.Optional;

@Service
public class BookingService {

    @Autowired
    BookingRepository bookingRepository;

    public int daysCalculatorDatabase(String name) {
        Optional<BookingModel> bookingModelOptional = bookingRepository.finByReserveName(name);

        int days = Period.between(bookingModelOptional.get().getCheckin(), bookingModelOptional.get().getCheckout()).getDays();
        return days;
    }
}
