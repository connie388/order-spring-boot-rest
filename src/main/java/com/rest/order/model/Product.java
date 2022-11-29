package com.rest.order.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
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

@Entity
@ToString
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Table(name = "products")
public class Product extends AuditModel {
    @Id
    @NotNull
    @Size(max = 15)
    @Column(name = "productCode")
    private String productCode;

    @NotNull
    @Size(max = 70)
    @Column(name = "productName")
    private String productName;

    @NotNull
    @Size(max = 50)
    @Column(name = "productLine")
    private String productLine;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "productLine", nullable = false, insertable = false, updatable = false)
    private ProductLine prodLine;

    @NotNull
    @Size(max = 10)
    @Column(name = "productScale")
    private String productScale;

    @NotNull
    @Size(max = 50)
    @Column(name = "productVendor")
    private String productVendor;

    @NotNull
    @Size(max = 4000)
    @Column(name = "productDescription")
    private String productDescription;

    @NotNull
    @Column(name = "quantityInStock")
    private Integer quantityInStock;

    @NotNull
    @Column(name = "buyPrice")
    private BigDecimal buyPrice;

    @NotNull
    @Column(name = "msrp")
    private BigDecimal msrp;

    // Define this so that findAll or findById will get
    // ProductLine prodLine instead of String productLine
    // public ProductLine getProductLine() {
    // return prodLine;
    // }
}