package com.example.employee.service;

import com.example.employee.model.Employee;
import com.example.employee.repo.IEmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class EmployeeService implements IEmployeeService{
    @Autowired
    private IEmployeeRepository employeeRepository;
    @Override
    public void save(Employee employee) {
        employeeRepository.save(employee);

    }

    @Override
    public void remove(Long id) {
        employeeRepository.deleteById(id);

    }

    @Override
    public Iterable<Employee> findAll() {
      return employeeRepository.findAll();
    }

    @Override
    public Optional<Employee> findById(Long id) {
        return employeeRepository.findById(id);
    }

    @Override
    public Iterable<Employee> findByName(String name) {
      return   employeeRepository.findEmployeesByNameContaining(name);
    }

    @Override
    public Iterable<Employee> sortSalary() {
        return employeeRepository.findAllByOrderBySalaryDesc();
    }


}
