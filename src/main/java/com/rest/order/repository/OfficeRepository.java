package com.rest.order.repository;

import com.rest.order.model.Office;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OfficeRepository extends JpaRepository<Office, String> {
    Office findByOfficeCode(String officeCode);
}