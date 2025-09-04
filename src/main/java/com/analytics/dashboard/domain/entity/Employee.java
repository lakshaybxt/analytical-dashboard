package com.analytics.dashboard.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Employee {

//    @Id
//    @GeneratedValue(strategy = GenerationType.UUID)
    private String id = String.valueOf(UUID.randomUUID());

//    @Column(name = "first_name", nullable = false)
    private String firstName;

//    @Column(name = "last_name", nullable = false)
    private String lastName;

//    @Column(name = "email", unique = true, nullable = false)
    private String email;

//    @Column(name = "department")
    private String department;

//    @Column(name = "salary", precision = 10, scale = 2)
    private double salary;

//    @Column(name = "hire_date")
    private String hireDate;

    public Employee(String firstName, String lastName, String email,
                    String department, double salary, String hireDate) {
        this.id = String.valueOf(UUID.randomUUID());
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.department = department;
        this.salary = salary;
        this.hireDate = hireDate;
    }
}