package com.rest.order.repository;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.rest.order.model.Order;

public class OrderRepositoryCustomImpl implements OrderRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Order> findOrderByRequiredDateRange(LocalDate fromDate, LocalDate toDate) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Order> criteriaQuery = criteriaBuilder.createQuery(Order.class);
        Root<Order> root = criteriaQuery.from(Order.class);

        Predicate condition = criteriaBuilder.between(root.get("requiredDate"), fromDate, toDate);

        criteriaQuery.select(root).where(condition);
        List<Order> orders = entityManager.createQuery(criteriaQuery).getResultList();
        return orders;
    }

    @Override
    public List<Order> findOrderByIdAndRequiredDateRange(Integer customerNo,
            LocalDate fromDate,
            LocalDate toDate) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Order> criteriaQuery = criteriaBuilder.createQuery(Order.class);
        Root<Order> root = criteriaQuery.from(Order.class);
        Predicate conditions = criteriaBuilder.equal(root.get("customerNumber"), customerNo);

        if (fromDate != null) {
            Predicate startPredicate = criteriaBuilder.greaterThanOrEqualTo(root.<LocalDate>get("orderDate"), fromDate);
            conditions = criteriaBuilder.and(conditions, startPredicate);
        }
        if (toDate != null) {
            Predicate endPredicate = criteriaBuilder.lessThanOrEqualTo(root.<LocalDate>get("orderDate"), toDate);
            conditions = criteriaBuilder.and(conditions, endPredicate);
        }

        criteriaQuery.select(root).where(conditions);
        List<Order> orders = entityManager.createQuery(criteriaQuery).getResultList();
        return orders;
    }
}
