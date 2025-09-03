package com.analytics.dashboard.service;

import com.analytics.dashboard.domain.entity.Employee;
import com.analytics.dashboard.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final EmployeeRepository employeeRepository;

    @Override
    public void run(String... args) throws Exception {
        if(employeeRepository.count() == 0)
            loadSampleData();
    }

    private void loadSampleData() {
        List<Employee> employees = Arrays.asList(
                new Employee("Aarav", "Sharma", "aarav.sharma@tcs.com", "Engineering",
                        new BigDecimal("85000"), LocalDate.of(2022, 3, 15)),
                new Employee("Priya", "Verma", "priya.verma@tcs.com", "Engineering",
                        new BigDecimal("92000"), LocalDate.of(2021, 7, 20)),
                new Employee("Rohit", "Kapoor", "rohit.kapoor@infosys.com", "Sales",
                        new BigDecimal("65000"), LocalDate.of(2023, 1, 10)),
                new Employee("Sneha", "Reddy", "sneha.reddy@wipro.com", "Marketing",
                        new BigDecimal("70000"), LocalDate.of(2022, 9, 5)),
                new Employee("Vikram", "Singh", "vikram.singh@tcs.com", "Engineering",
                        new BigDecimal("95000"), LocalDate.of(2020, 11, 12)),
                new Employee("Ananya", "Iyer", "ananya.iyer@hcl.com", "HR",
                        new BigDecimal("72000"), LocalDate.of(2023, 2, 28)),
                new Employee("Arjun", "Nair", "arjun.nair@infosys.com", "Sales",
                        new BigDecimal("68000"), LocalDate.of(2022, 6, 18)),
                new Employee("Kavya", "Deshmukh", "kavya.deshmukh@wipro.com", "Marketing",
                        new BigDecimal("75000"), LocalDate.of(2021, 4, 22)),
                new Employee("Manish", "Patel", "manish.patel@tcs.com", "Engineering",
                        new BigDecimal("88000"), LocalDate.of(2023, 5, 8)),
                new Employee("Riya", "Joshi", "riya.joshi@icicibank.com", "Finance",
                        new BigDecimal("80000"), LocalDate.of(2022, 12, 3)),
                new Employee("Karan", "Mehta", "karan.mehta@infosys.com", "Sales",
                        new BigDecimal("63000"), LocalDate.of(2023, 8, 14)),
                new Employee("Neha", "Chopra", "neha.chopra@hcl.com", "HR",
                        new BigDecimal("74000"), LocalDate.of(2021, 10, 30)),
                new Employee("Aditya", "Rao", "aditya.rao@tcs.com", "Engineering",
                        new BigDecimal("91000"), LocalDate.of(2020, 8, 17)),
                new Employee("Pooja", "Banerjee", "pooja.banerjee@wipro.com", "Marketing",
                        new BigDecimal("73000"), LocalDate.of(2023, 3, 25)),
                new Employee("Siddharth", "Gupta", "siddharth.gupta@icicibank.com", "Finance",
                        new BigDecimal("82000"), LocalDate.of(2022, 1, 7))
        );

        employeeRepository.saveAll(employees);
        System.out.println("Sample data loaded: " + employees.size() + " employees");
    }
}
