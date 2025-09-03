package com.analytics.dashboard.util;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Component
public class SparkUtils {

    private final SparkSession spark;

    @Value("${spring.datasource.url}")
    private String dbUrl;

    @Value("${spring.datasource.username}")
    private String dbUser;

    @Value("${spring.datasource.password}")
    private String dbPassword;

    public SparkUtils(SparkSession spark, String dbUrl, String dbUser, String dbPassword) {
        this.spark = spark;
        this.dbUrl = dbUrl;
        this.dbUser = dbUser;
        this.dbPassword = dbPassword;
    }

    private Properties getProps() {
        Properties props = new Properties();
        props.put("user", dbUser);
        props.put("password", dbPassword);
        props.setProperty("driver", "org.postgresql.Driver");
        return props;
    }

    public Dataset<Row> readTable(String tableName) {
        Properties props = getProps();

        return spark.read()
                .jdbc(dbUrl, tableName, props);
    }

    public void writeTable(Dataset<Row> df, String tableName, String mode) {
        Properties props = getProps();

        df.write()
                .mode(mode)
                .jdbc(dbUrl, tableName, props);
    }
}
