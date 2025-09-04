package com.analytics.dashboard.service;

import com.analytics.dashboard.util.SparkUtils;
import lombok.RequiredArgsConstructor;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.RowFactory;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.types.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final SparkSession spark;
    private final SparkUtils sparkUtils;

    @Override
    public void run(String... args) throws Exception {
            loadSampleData();
    }

    private void loadSampleData() {
        List<Row> employees = new ArrayList<>();

        employees.add(RowFactory.create(UUID.randomUUID().toString(), "Aarav", "Sharma", "aarav.sharma@tcs.com", "Engineering",
                85000.0, "2022-03-15"));
        employees.add(RowFactory.create(UUID.randomUUID().toString(), "Priya", "Verma", "priya.verma@mcs.com", "Judge",
                92000.0, "2021-07-20"));

        employees.add(RowFactory.create(UUID.randomUUID().toString(), "Garav", "Barma", "ninja.hatori@bcs.com", "Doctor",
                79000.0, "2024-03-18"));
        employees.add(RowFactory.create(UUID.randomUUID().toString(), "Riya", "Garma", "priya.verma@ucs.com", "PM",
                92000.0, "2023-05-19"));

        StructType schema = DataTypes.createStructType(new StructField[]{
                DataTypes.createStructField("id", DataTypes.StringType, false),
                DataTypes.createStructField("firstName", DataTypes.StringType, false),
                DataTypes.createStructField("lastName", DataTypes.StringType, false),
                DataTypes.createStructField("email", DataTypes.StringType, false),
                DataTypes.createStructField("department", DataTypes.StringType, false),
                DataTypes.createStructField("salary", DataTypes.DoubleType, false),
                DataTypes.createStructField("hireDate", DataTypes.StringType, false)
        });


        Dataset<Row> df = spark.createDataFrame(employees, schema);
        sparkUtils.writeTable(df, "employee", "append");

        System.out.println("Sample data loaded: " + employees.size() + " employees");
    }
}
