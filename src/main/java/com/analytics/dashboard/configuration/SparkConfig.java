package com.analytics.dashboard.configuration;

import jakarta.annotation.PreDestroy;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.SparkSession;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SparkConfig {

    @Value("${spark.app.name}")
    private String appName;

    @Value("${spark.master}")
    private String masterUri;

    private SparkSession sparkSession;

    @Bean
    public SparkConf conf() {
        return new SparkConf()
                .setAppName(appName)
                .setMaster(masterUri)
                .set("spark.serializer", "org.apache.spark.serializer.JavaSerializer")
                .set("spark.ui.enabled", "false");
    }

    @Bean
    public SparkSession sparkSession() {
        return SparkSession.builder()
                .appName(appName)
                .master(masterUri)
                .config(conf())
                .getOrCreate();
    }

    @PreDestroy
    public void closeSparkSession() {
        if (sparkSession != null) {
            sparkSession.stop();
            System.out.println("✅ SparkSession stopped cleanly.");
        }
    }
}
