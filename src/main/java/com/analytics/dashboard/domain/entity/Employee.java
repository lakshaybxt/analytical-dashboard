package com.analytics.dashboard.domain.entity;


import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Employee {

    private String id = String.valueOf(UUID.randomUUID()); // ✅ initialize here
    private String firstName;
    private String lastName;
    private String email;
    private String department;
    private double salary;
    private LocalDate hireDate;

    public Employee(String firstName, String lastName, String email,
                    String department, double salary, LocalDate hireDate) {
        this.id = String.valueOf(UUID.randomUUID());
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.department = department;
        this.salary = salary;
        this.hireDate = hireDate;
    }
}
