package com.rest.order.repository;

import java.util.Date;
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
    public List<Order> findOrderByRequiredDateRange(Date fromDate, Date toDate) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Order> criteriaQuery = criteriaBuilder.createQuery(Order.class);
        Root<Order> root = criteriaQuery.from(Order.class);

        Predicate condition = criteriaBuilder.between(root.get("requiredDate"), fromDate, toDate);

        criteriaQuery.select(root).where(condition);
        List<Order> orders = entityManager.createQuery(criteriaQuery).getResultList();
        return orders;
    }

    @Override
    public List<Order> findOrderByIdAndRequiredDateRange(Integer id, Date fromDate, Date toDate) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Order> criteriaQuery = criteriaBuilder.createQuery(Order.class);
        Root<Order> root = criteriaQuery.from(Order.class);
        Predicate customerPredicate = criteriaBuilder.equal(root.get("customerNumber"), id);
        Predicate dateRangePredicate = criteriaBuilder.between(root.get("requiredDate"), fromDate, toDate);

        criteriaQuery.select(root).where(customerPredicate, dateRangePredicate);
        List<Order> orders = entityManager.createQuery(criteriaQuery).getResultList();
        return orders;
    }
}
