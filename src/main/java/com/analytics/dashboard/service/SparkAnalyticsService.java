package com.analytics.dashboard.service;

import com.analytics.dashboard.domain.dto.DepartmentStatsDTO;
import com.analytics.dashboard.util.SparkUtils;
import lombok.RequiredArgsConstructor;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.apache.spark.sql.functions.*;
import static org.apache.spark.sql.functions.desc;
import static org.apache.spark.sql.functions.min;

@Service
@RequiredArgsConstructor
public class SparkAnalyticsService {

    private final SparkUtils sparkUtils;

    public Dataset<Row> loadEmployeeData() {

        return sparkUtils.readTable("employee");
    }

    public List<DepartmentStatsDTO> calculateDepartmentStats() {
        Dataset<Row> employees = loadEmployeeData().groupBy("department")
                .agg(
                        count("*").alias("employee_count"),
                        avg("salary").alias("avg_salary"),
                        max("salary").alias("max_salary"),
                        min("salary").alias("min_salary")
                ).orderBy(desc("avg_salary"));

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
}
