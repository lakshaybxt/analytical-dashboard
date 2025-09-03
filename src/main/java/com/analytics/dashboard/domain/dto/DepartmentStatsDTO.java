package com.analytics.dashboard.domain.dto;

import lombok.Builder;

@Builder
public record DepartmentStatsDTO (
        String department,
        long employeeCount,
        double avgSalary,
        double maxSalary,
        double minSalary
) {
}
