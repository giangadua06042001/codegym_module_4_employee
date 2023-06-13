package com.example.employee.service;

import com.example.employee.model.Employee;

public interface IEmployeeService extends IGeneralService<Employee>{
    public Iterable<Employee>findByName(String name);
    public Iterable<Employee>sortSalary();

}
