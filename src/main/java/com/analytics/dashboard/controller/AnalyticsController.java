package com.analytics.dashboard.controller;

import com.analytics.dashboard.domain.dto.DepartmentStatsDTO;
import com.analytics.dashboard.domain.entity.Employee;
import com.analytics.dashboard.service.SparkAnalyticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/analytics")
@RequiredArgsConstructor
public class AnalyticsController {

    private final SparkAnalyticsService sparkAnalyticsService;

    @GetMapping("/department-stats")
    public ResponseEntity<Map<String, Object>> getDepartmentStats() {
        try {
            List<DepartmentStatsDTO> stats = sparkAnalyticsService.calculateDepartmentStats();
            return ResponseEntity.ok(Map.of("success", true, "data", stats));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(Map.of
                    ("success", false, "error", e.getMessage()));
        }
    }

    @GetMapping("/top-paid")
    public ResponseEntity<Map<String, Object>> getTopPaidEmployees(@RequestParam(defaultValue = "5") int topN) {
        try {
            List<Employee> topEmployees = sparkAnalyticsService.getTopPaidEmployees(topN);
            return ResponseEntity.ok(Map.of("success", true, "data", topEmployees));
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(Map.of("success", false, "error", e.getMessage()));
        }
    }

    @GetMapping("/recent-hires")
    public ResponseEntity<Map<String, Object>> getRecentHires(@RequestParam String fromDate) {
        try {
            List<Employee> hires = sparkAnalyticsService.getEmployeesHiredAfter(fromDate);
            return ResponseEntity.ok(Map.of("success", true, "data", hires));
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(Map.of("success", false, "error", e.getMessage()));
        }
    }

    @GetMapping("/salary-distribution")
    public ResponseEntity<Map<String, Object>> getSalaryDistribution() {
        try {
            Map<String, Long> dist = sparkAnalyticsService.getSalaryDistribution();
            return ResponseEntity.ok(Map.of("success", true, "data", dist));
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(Map.of("success", false, "error", e.getMessage()));
        }
    }

    @GetMapping("/department-stats-sql")
    public ResponseEntity<Map<String, Object>> getDepartmentStatsSQL() {
        try {
            List<DepartmentStatsDTO> stats = sparkAnalyticsService.calculateDepartmentStatsSQL();
            return ResponseEntity.ok(Map.of("success", true, "data", stats));
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(Map.of("success", false, "error", e.getMessage()));
        }
    }
}