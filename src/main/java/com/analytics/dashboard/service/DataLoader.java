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
import java.util.UUID;

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
                        85000.00, LocalDate.of(2022, 3, 15)),
                new Employee("Priya", "Verma", "priya.verma@tcs.com", "Engineering",
                        92000.00, LocalDate.of(2021, 7, 20)),
                new Employee("Rohit", "Kapoor", "rohit.kapoor@infosys.com", "Sales",
                        65000.00, LocalDate.of(2023, 1, 10)),
                new Employee("Sneha", "Reddy", "sneha.reddy@wipro.com", "Marketing",
                        70000.00, LocalDate.of(2022, 9, 5)),
                new Employee("Vikram", "Singh", "vikram.singh@tcs.com", "Engineering",
                        95000.00, LocalDate.of(2020, 11, 12)),
                new Employee("Ananya", "Iyer", "ananya.iyer@hcl.com", "HR",
                        65000.00, LocalDate.of(2023, 2, 28)),
                new Employee("Arjun", "Nair", "arjun.nair@infosys.com", "Sales",
                        92000.00, LocalDate.of(2022, 6, 18)),
                new Employee("Kavya", "Deshmukh", "kavya.deshmukh@wipro.com", "Marketing",
                        78000.00, LocalDate.of(2021, 4, 22)),
                new Employee("Manish", "Patel", "manish.patel@tcs.com", "Engineering",
                        59000.00, LocalDate.of(2023, 5, 8)),
                new Employee("Riya", "Joshi", "riya.joshi@icicibank.com", "Finance",
                        90000.00, LocalDate.of(2022, 12, 3)),
                new Employee("Karan", "Mehta", "karan.mehta@infosys.com", "Sales",
                        32000.00, LocalDate.of(2023, 8, 14)),
                new Employee("Neha", "Chopra", "neha.chopra@hcl.com", "HR",
                        54000.00, LocalDate.of(2021, 10, 30)),
                new Employee("Aditya", "Rao", "aditya.rao@tcs.com", "Engineering",
                        44000.00, LocalDate.of(2020, 8, 17)),
                new Employee("Pooja", "Banerjee", "pooja.banerjee@wipro.com", "Marketing",
                        66000.00, LocalDate.of(2023, 3, 25)),
                new Employee("Siddharth", "Gupta", "siddharth.gupta@icicibank.com", "Finance",
                        67000.00, LocalDate.of(2022, 1, 7))
        );

        employeeRepository.saveAll(employees);
        System.out.println("Sample data loaded: " + employees.size() + " employees");
        System.out.println("Sample data loaded: " + String.valueOf(UUID.randomUUID()));
    }
}
