package com.rest.order.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.rest.order.model.Customer;

//Set<String> phones = new HashSet<>();
// filling the set with any number of items

// customerRepository.findCustomerByPhones(phones);
public class CustomerRepositoryCustomImpl implements CustomerRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Customer> findCustomerByPhones(Set<String> phones) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Customer> query = cb.createQuery(Customer.class);
        Root<Customer> customer = query.from(Customer.class);

        Path<String> phonePath = customer.get("phone");

        List<Predicate> predicates = new ArrayList<>();
        for (String phone : phones) {
            predicates.add(cb.like(phonePath, phone));
        }
        query.select(customer)
                .where(cb.or(predicates.toArray(new Predicate[predicates.size()])));

        return entityManager.createQuery(query)
                .getResultList();
    }
}
