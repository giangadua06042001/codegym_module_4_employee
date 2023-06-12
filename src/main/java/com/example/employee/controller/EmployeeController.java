package com.example.employee.controller;

import com.example.employee.model.Employee;
import com.example.employee.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private IEmployeeService employeeService;

    @GetMapping("")
    public ModelAndView showFormCreateEmployee() {
        ModelAndView modelAndView = new ModelAndView("employee/formCreat");
        modelAndView.addObject("employee", new Employee());
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView create(@Validated @ModelAttribute("employee") Employee employee,BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView("employee/formCreat");
        }
        ModelAndView modelAndView = new ModelAndView("employee/list");
        modelAndView.addObject("employees", employeeService.save(employee));
        return modelAndView;

    }
}
