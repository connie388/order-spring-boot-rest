package com.rest.order.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "orders")
public class Order extends AuditModel {
    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    @NotNull
    @Column(name = "orderNumber")
    private Integer orderNumber;

    @NotNull
    @Column(name = "orderDate")
    private Date orderDate;

    @NotNull
    @Column(name = "requiredDate")
    private Date requiredDate;

    @Column(name = "shippedDate")
    private Date shippedDate;

    @NotNull
    @Size(max = 15)
    @Column(name = "status")
    private String status;

    @Size(max = 4000)
    @Column(name = "comments")
    private String comments;

    @Column(name = "customerNumber")
    private int customerNumber;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "customerNumber", insertable = false, updatable = false,
    nullable = false)
    @JsonIgnore
    private Customer customer;

    @ElementCollection(targetClass = OrderDetail.class)
    @CollectionTable(name = "orderdetails", joinColumns = @JoinColumn(name = "orderNumber"))
    @LazyCollection(LazyCollectionOption.FALSE)
    @OrderBy("orderLineNumber DESC")
    private List<OrderDetail> orderDetailList;
}