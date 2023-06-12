package com.example.employee.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import javax.validation.constraints.*;

@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "không nên để tên trống")
    private String name;
    @Email(message = "Nhập đúng định dạng email")
    private String email;
    @Pattern(regexp = "(\"(^$|[0-9]*$)\")",message = "nhap dung dinh dang so dien thoai")
    @Size(min = 10,max = 11)
    private String number;
    @Min(value = 18)
    private int age;
    @Min(value = 1500)
    private double salary;

    public Employee(Long id, String name, String email, String number, int age, double salary) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.number = number;
        this.age = age;
        this.salary = salary;
    }

    public Employee() {
    }

    public Employee(String name, String email, String number, int age, double salary) {
        this.name = name;
        this.email = email;
        this.number = number;
        this.age = age;
        this.salary = salary;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}
