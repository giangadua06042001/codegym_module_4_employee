package com.example.employee.repo;

import com.example.employee.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IEmployeeRepository extends JpaRepository<Employee,Long> {
    Iterable<Employee>findEmployeesByNameContaining(String name);
    Iterable<Employee> findAllByOrderBySalaryDesc();
}
