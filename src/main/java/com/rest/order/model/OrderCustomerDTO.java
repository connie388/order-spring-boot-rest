package com.rest.order.model;

import java.time.LocalDate;
import java.util.Date;

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
public class OrderCustomerDTO {

    private Integer customerNumber;
    private String customerName;
    private Integer orderNumber;

    private LocalDate orderDate;

    private LocalDate requiredDate;

    private LocalDate shippedDate;

    private String status;

    private String comments;
    private Date createdAt;
    private Date updatedAt;
}