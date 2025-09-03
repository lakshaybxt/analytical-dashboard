package com.analytics.dashboard.service;

import com.analytics.dashboard.domain.dto.DepartmentStatsDTO;
import com.analytics.dashboard.domain.entity.Employee;
import com.analytics.dashboard.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Encoders;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.apache.spark.sql.functions.*;
import static org.apache.spark.sql.functions.desc;
import static org.apache.spark.sql.functions.min;


@Service
@RequiredArgsConstructor
public class SparkAnalyticsService {

    private final SparkSession sparkSession;
    private final EmployeeRepository employeeRepository;

    public Dataset<Row> loadEmployeeData() {
        List<Employee> employeeList = employeeRepository.findAll();
        return sparkSession.createDataFrame(employeeList, Employee.class);
    }

    public List<DepartmentStatsDTO> calculateDepartmentStats() {
        Dataset<Row> employees = loadEmployeeData()
                .groupBy("department")
                .agg(
                        count("*").alias("employee_count"),
                        avg("salary").alias("avg_salary"),
                        max("salary").alias("max_salary"),
                        min("salary").alias("min_salary")
                )
                .orderBy(desc("avg_salary"));

        return employees.collectAsList().stream().map(row ->
                DepartmentStatsDTO.builder()
                        .department(row.getString(0))
                        .employeeCount(row.getLong(1))
                        .avgSalary(row.getDouble(2))
                        .maxSalary(row.getDouble(3))
                        .minSalary(row.getDouble(4))
                        .build()
        ).toList();
    }

    public List<Employee> getTopPaidEmployees(int n) {
        return loadEmployeeData()
                .orderBy(desc("salary"))
                .limit(n)
                .as(Encoders.bean(Employee.class))
                .collectAsList();
    }

    public List<Employee> getEmployeesHiredAfter(String fromDate) {
        return loadEmployeeData()
                .filter(col("hireDate").gt(lit(fromDate)))
                .as(Encoders.bean(Employee.class))
                .collectAsList();
    }

    public Map<String, Long> getSalaryDistribution() {
        return loadEmployeeData()
                .groupBy("department")
                .agg(count("*").alias("count"))
                .collectAsList()
                .stream()
                .collect(Collectors.toMap(
                        row -> row.getString(0),
                        row -> row.getLong(1)
                ));
    }

    public List<DepartmentStatsDTO> calculateDepartmentStatsSQL() {
        Dataset<Row> df = loadEmployeeData();
        df.createOrReplaceTempView("employees");

        Dataset<Row> result = sparkSession.sql(
                "SELECT department, COUNT(*) as employee_count, " +
                        "AVG(salary) as avg_salary, MAX(salary) as max_salary, MIN(salary) as min_salary " +
                        "FROM employees GROUP BY department ORDER BY avg_salary DESC"
        );

        return result.collectAsList().stream().map(row ->
                DepartmentStatsDTO.builder()
                        .department(row.getString(0))
                        .employeeCount(row.getLong(1))
                        .avgSalary(row.getDouble(2))
                        .maxSalary(row.getDouble(3))
                        .minSalary(row.getDouble(4))
                        .build()
        ).toList();
    }
}