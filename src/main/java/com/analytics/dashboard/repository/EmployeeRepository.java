package com.analytics.dashboard.repository;

import com.analytics.dashboard.domain.entity.Employee;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

//@Repository
//public interface EmployeeRepository extends JpaRepository<Employee, UUID> {
//}
@Repository
public class EmployeeRepository {

    private final Map<String, Employee> employeeMap = new ConcurrentHashMap<>();

    public Employee save(Employee employee) {
        if (employee.getId() == null) {
            employee.setId(String.valueOf(UUID.randomUUID()));
        }
        employeeMap.put(employee.getId(), employee);
        return employee;
    }

    public Optional<Employee> findById(String id) {
        return Optional.ofNullable(employeeMap.get(id));
    }

    public List<Employee> findAll() {
        return new ArrayList<>(employeeMap.values());
    }

    public void deleteById(String id) {
        employeeMap.remove(id);
    }

    public long count() {
        return employeeMap.size();
    }

    public List<Employee> saveAll(List<Employee> employees) {
        employees.forEach(this::save);
        return employees;
    }

}
