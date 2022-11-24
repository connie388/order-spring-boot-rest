package com.rest.order.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
@Table(name = "employees")
public class Employee extends AuditModel {
    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    @Column(name = "employeeNumber", nullable = false)
    private Integer employeeNumber;

    @NotNull
    @Size(max = 50)
    @Column(name = "lastName")
    private String lastName;

    @NotNull
    @Size(max = 50)
    @Column(name = "firstName")
    private String firstName;

    @NotNull
    @Size(max = 10)
    @Column(name = "extension")
    private String extension;

    @NotNull
    @Size(max = 100)
    @Column(name = "email")
    private String email;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "officeCode", nullable = false)
    private Office office;

    // bi-directional many-to-one association to Employee
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "reportsTo")
    private Employee reportsTo;

    // bi-directional many-to-one association to Employee
    @OneToMany(mappedBy = "reportsTo")
    private List<Employee> employees;

    @NotNull
    @Size(max = 50)
    @Column(name = "jobTitle")
    private String jobTitle;

}