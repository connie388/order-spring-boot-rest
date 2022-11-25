package com.rest.order.repository;

import java.time.LocalDate;
import java.util.List;

import com.rest.order.model.Payment;

public interface PaymentRepositoryCustom {
    List<Payment> findPaymentsByCustomerDateRange(Integer customerNumber, LocalDate fromDate, LocalDate toDate);
}
