package com.rest.order.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
@Table(name = "offices")
public class Office extends AuditModel {
    @Id
    @Column(name = "officeCode", nullable = false, length = 10)
    private String officeCode;

    @NotNull
    @Size(max = 50)
    @Column(name = "city")
    private String city;

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

    @Size(max = 50)
    @Column(name = "state")
    private String state;

    @NotNull
    @Size(max = 50)
    @Column(name = "country")
    private String country;

    @NotNull
    @Size(max = 15)
    @Column(name = "postalCode")
    private String postalCode;

    @Setter(AccessLevel.NONE)
    @Getter(AccessLevel.NONE)
    @OneToMany(mappedBy = "office", fetch = FetchType.LAZY)
    private Set<Employee> employees = new HashSet<>();
}