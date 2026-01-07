package com.example.springweb.controllers;

import com.example.springweb.dto.EmployeeDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
public class EmployeeController {

    @GetMapping(path = "/getEmployee")
    public String getEmployee(){
        return "HuHaha";
    }

    @GetMapping(path = "/employees/{empId}")
    public EmployeeDto getEmployeeById(@PathVariable Long empId){
        return new EmployeeDto(empId,"Soumyajit",26,"soumyajit2k18@gmail.com", LocalDate.of(2022,06,26));
    }

    @GetMapping(path ="/employees")
    public String getEmployee(@RequestParam(required = false) Integer age,
                              @RequestParam(required = false) String name){
        return "Age is "+age +" ,name is: "+ name;
    }
}
