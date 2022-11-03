package com.rest.order.service;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rest.order.exception.InvalidInputException;
import com.rest.order.exception.ResourceAlreadyExistsException;
import com.rest.order.exception.ResourceNotFoundException;
import com.rest.order.model.Payment;
import com.rest.order.repository.PaymentRepository;

@Service
@Transactional
public class PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;

    public List<Payment> list() {
        return paymentRepository.findAll();
    }

    public List<Payment> get(Integer customerNumber) {
        if (null == customerNumber)
            throw new InvalidInputException("Invalid Customer Number");
        return paymentRepository.findByCustomerNumber(customerNumber);
    }

    public Payment get(Integer customerNumber, String checkNumber) {
        if (null == customerNumber)
            throw new InvalidInputException("Invalid Customer Number");
        if (null == checkNumber)
            throw new InvalidInputException("Invalid Check Number");
        return paymentRepository.findPaymentByCustomerNumberAndCheckNumber(customerNumber, checkNumber);
    }

    public List<Payment> get(Integer customerNumber, Date fromDate, Date toDate) {
        if (null == customerNumber)
            throw new InvalidInputException("Invalid Customer Number.");
        if (null == fromDate)
            throw new InvalidInputException("Invalid Start Date.");
        if (null == toDate)
            throw new InvalidInputException("Invalid To Date.");
        return paymentRepository.findOutstandingPaymentsByCustomer(customerNumber, fromDate, toDate);
    }

    public Payment add(Payment payment) {
        Payment existingPayment = paymentRepository.findPaymentByCustomerNumberAndCheckNumber(
                payment.getCustomerNumber(),
                payment.getCheckNumber());
        if (existingPayment != null)
            throw new ResourceAlreadyExistsException("Payment already exists.");

        return paymentRepository.save(payment);
    }

    public Payment update(Integer customerNumber, String checkNumber, Payment payment) {
        if (null == customerNumber)
            throw new InvalidInputException("Invalid Customer Number.");
        if (null == checkNumber)
            throw new InvalidInputException("Invalid Check Number");
        Payment _payment = paymentRepository.findPaymentByCustomerNumberAndCheckNumber(customerNumber, checkNumber);
        if (_payment == null)
            throw new ResourceNotFoundException("Payment record not found");
        _payment.setPaymentDate(payment.getPaymentDate());
        _payment.setAmount(payment.getAmount());
        return paymentRepository.save(_payment);
    }

    public String delete(Integer customerNumber, String checkNumber) {
        if (null == customerNumber)
            throw new InvalidInputException("Invalid Customer Number.");
        if (null == checkNumber)
            throw new InvalidInputException("Invalid Check Number");
        Payment _payment = paymentRepository.findPaymentByCustomerNumberAndCheckNumber(customerNumber, checkNumber);
        if (_payment == null)
            throw new ResourceNotFoundException("Payment record not found");
        paymentRepository.delete(_payment);
        return "Payment is deleted successfully";
    }
}