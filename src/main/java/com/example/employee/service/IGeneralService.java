package com.example.employee.service;

import com.example.employee.model.Employee;

import java.util.Optional;

public interface IGeneralService <T>{
    void save(T t);
    void remove(Long id);
    Iterable<T>findAll();
    Optional<T>findById(Long id);
}
