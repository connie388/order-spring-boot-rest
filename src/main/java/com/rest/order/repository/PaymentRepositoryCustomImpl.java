package com.rest.order.repository;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.rest.order.model.Payment;

public class PaymentRepositoryCustomImpl implements PaymentRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Payment> findOutstandingPaymentsByCustomer(Integer customerNumber, Date fromDate, Date toDate) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Payment> criteriaQuery = criteriaBuilder.createQuery(Payment.class);
        Root<Payment> payment = criteriaQuery.from(Payment.class);

        Predicate outstandings = criteriaBuilder.between(payment.get("paymentDate"), fromDate, toDate);
        Predicate customer = criteriaBuilder.equal(payment.get("customerNumber"), customerNumber);

        Predicate finalPredicate = criteriaBuilder.and(outstandings, customer);
        criteriaQuery.select(payment).where(finalPredicate);
        List<Payment> payments = entityManager.createQuery(criteriaQuery).getResultList();
        return payments;
    }
}
