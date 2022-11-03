package com.rest.order.model;

import javax.persistence.*;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
@Table(name = "employees")
public class Employee extends AuditModel {
    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    @Column(name = "employeeNumber", nullable = false)
    private Integer employeeNumber;

    @Column(name = "lastName", nullable = false, length = 50)
    private String lastName;

    @Column(name = "firstName", nullable = false, length = 50)
    private String firstName;

    @Column(name = "extension", nullable = false, length = 10)
    private String extension;

    @Column(name = "email", nullable = false, length = 100)
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

    @Column(name = "jobTitle", nullable = false, length = 50)
    private String jobTitle;

}