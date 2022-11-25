package com.rest.order.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;

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
@IdClass(PaymentId.class)
@Table(name = "payments")
public class Payment extends AuditModel {
    @Id
    @NotNull
    @Column(name = "customerNumber")
    private Integer customerNumber;

    @NotNull
    @Size(max = 50)
    @Id
    @Column(name = "checkNumber")
    private String checkNumber;

    @NotNull
    @Column(name = "paymentDate")
    private LocalDate paymentDate;

    @NotNull
    @Column(name = "amount")
    private BigDecimal amount;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "customerNumber", insertable = false, updatable = false, nullable = false)
    private Customer customer;

}