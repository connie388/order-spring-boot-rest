package com.rest.order.repository;

import com.rest.order.model.Payment;
import com.rest.order.model.PaymentId;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, PaymentId>, PaymentRepositoryCustom {
    List<Payment> findByCustomerNumber(Integer customerNumber);

    Payment findPaymentByCustomerNumberAndCheckNumber(Integer customerNumber, String checkNumber);
}
