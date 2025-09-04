package com.analytics.dashboard.controller;

import com.analytics.dashboard.domain.dto.DepartmentStatsDTO;
import com.analytics.dashboard.service.SparkAnalyticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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

}
