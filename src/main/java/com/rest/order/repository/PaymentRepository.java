package com.rest.order.repository;

import com.rest.order.model.Payment;
import com.rest.order.model.PaymentId;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PaymentRepository extends JpaRepository<Payment, PaymentId>, PaymentRepositoryCustom {
    List<Payment> findByCustomerNumber(Integer customerNumber);

    @Query("SELECT d FROM Payment d WHERE d.customerNumber = ?1 and (?2 is null or d.paymentDate >= ?2) and (?3 is null or d.paymentDate <= ?3) and (?4 is null or d.checkNumber =?4)")
    List<Payment> findPaymentsByCustomerDateRange(Integer customerNumber, LocalDate fromDate, LocalDate toDate,
            String checkNumber);

    Payment findPaymentByCustomerNumberAndCheckNumber(Integer customerNumber, String checkNumber);
}
