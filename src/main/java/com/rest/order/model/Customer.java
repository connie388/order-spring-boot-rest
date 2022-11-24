package com.rest.order.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
@Table(name = "customers")
public class Customer extends AuditModel {
    @Id
    @Setter(AccessLevel.NONE)
    @GeneratedValue
    @Column(name = "customerNumber", nullable = false)
    private Integer customerNumber;

    @NotNull
    @Size(max = 50)
    @Column(name = "customerName")
    private String customerName;

    @NotNull
    @Size(max = 50)
    @Column(name = "contactLastName")
    private String contactLastName;

    @NotNull
    @Size(max = 50)
    @Column(name = "contactFirstName")
    private String contactFirstName;

    @NotNull
    @Size(max = 50)
    @Column(name = "phone")
    private String phone;

    @NotNull
    @Size(max = 50)
    @Column(name = "addressLine1")
    private String addressLine1;

    @Size(max = 50)
    @Column(name = "addressLine2")
    private String addressLine2;

    @NotNull
    @Size(max = 50)
    @Column(name = "city")
    private String city;

    @Size(max = 50)
    @Column(name = "state")
    private String state;

    @Size(max = 15)
    @Column(name = "postalCode", length = 15)
    private String postalCode;

    @NotNull
    @Size(max = 50)
    @Column(name = "country", length = 50, nullable = false)
    private String country;

    @Column(name = "salesRepEmployeeNumber")
    private Integer salesRepEmployeeNumber;

    @Column(name = "creditLimit")
    private BigDecimal creditLimit;
}