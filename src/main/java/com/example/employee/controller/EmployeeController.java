package com.example.employee.controller;

import com.example.employee.model.Employee;
import com.example.employee.service.IEmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;


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
    public ModelAndView create(@Valid @ModelAttribute("employee") Employee employee, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView("employee/formCreat");
        }
        employeeService.save(employee);
        ModelAndView modelAndView = new ModelAndView("employee/formCreat");
        modelAndView.addObject("employee",new Employee());
        modelAndView.addObject("message","New Employee created successfully");
        return modelAndView;
    }
    @GetMapping("/list")
    public ModelAndView listEmployee(){
        ModelAndView modelAndView=new ModelAndView("employee/list");
        modelAndView.addObject("employee",employeeService.findAll());
        return modelAndView;
    }
    @GetMapping("/{id}/delete")
    public ModelAndView formDelete(@PathVariable ("id") Long id){
        Optional<Employee>employee=employeeService.findById(id);
        if(employee.isPresent()){
            ModelAndView modelAndView=new ModelAndView("employee/delete");
            modelAndView.addObject("employee",employee.get());
            return modelAndView;

        }else {
            return new ModelAndView("error404");
        }
    }
    @PostMapping("/delete")
    public String  delete(@ModelAttribute("employee") Employee employee){
        employeeService.remove(employee.getId());
        return "redirect:list";

    }
    @GetMapping("/{id}/edit")
    public ModelAndView showFormEdit(@PathVariable("id")Long id){
        Optional<Employee>employee=employeeService.findById(id);
        if(employee.isPresent()){
            ModelAndView modelAndView=new ModelAndView("employee/edit");
            modelAndView.addObject("employee",employee.get());
            return modelAndView;

        }else {
            return new ModelAndView("error404");
        }
    }
    @PostMapping("/edit")
    public ModelAndView updateEmployee( @Valid @ModelAttribute("employee") Employee employee,BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return new ModelAndView("employee/edit");
        }
        employeeService.save(employee);
        ModelAndView modelAndView=new ModelAndView("employee/edit");
        modelAndView.addObject("employee",employee);
        modelAndView.addObject("message","Successful employee correction");
        return modelAndView;
    }
    @GetMapping("/{id}/view")
    public ModelAndView viewEmployee(@PathVariable("id")Long id){
        Optional<Employee>employee=employeeService.findById(id);
        if(employee.isPresent()){
            ModelAndView modelAndView=new ModelAndView("employee/view");
            modelAndView.addObject("employee",employee.get());
            return modelAndView;

        }else {
            return new ModelAndView("error404");
        }
    }
    @GetMapping("/search")
    public ModelAndView seachName(@RequestParam("s")String name){
    Iterable<Employee>employees=employeeService.findByName(name);
    ModelAndView modelAndVie = new ModelAndView("employee/list");
        modelAndVie.addObject("employee",employees);
        return modelAndVie;
    }
    @GetMapping("/sort")
    public ModelAndView sort(){
        ModelAndView modelAndVie = new ModelAndView("employee/list");
        modelAndVie.addObject("employee", employeeService.sortSalary());
        return modelAndVie;
    }

}
