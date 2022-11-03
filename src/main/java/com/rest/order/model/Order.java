package com.rest.order.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.AccessLevel;

@ToString
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
@Table(name = "orders")
public class Order extends AuditModel {
    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    @Column(name = "orderNumber", nullable = false)
    private Integer orderNumber;

    @Column(name = "orderDate", nullable = false)
    private Date orderDate;

    @Column(name = "requiredDate", nullable = false)
    private Date requiredDate;

    @Column(name = "shippedDate")
    private Date shippedDate;

    @Column(name = "status", nullable = false, length = 15)
    private String status;

    @Column(name = "comments", length = 4000)
    private String comments;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "customerNumber", insertable = false, updatable = false, nullable = false)
    @JsonIgnore
    private Customer customer;
}