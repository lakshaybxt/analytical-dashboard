package com.analytics.dashboard.util;

import lombok.RequiredArgsConstructor;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Component
@RequiredArgsConstructor
@Profile("!test")
public class SparkUtils {

    private final SparkSession spark;

    @Value("${spring.datasource.url}")
    private String dbUrl;

    @Value("${spring.datasource.username}")
    private String dbUser;

    @Value("${spring.datasource.password}")
    private String dbPassword;

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
