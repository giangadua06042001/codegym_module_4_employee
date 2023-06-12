package com.example.employee.service;

import java.util.Optional;

public interface IGeneralService <T>{
    Object save(T t);
    void remove(Long id);
    Iterable<T>findAll();
    Optional<T>findById(Long id);
}
