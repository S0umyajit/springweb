package com.example.springweb.controllers;

import com.example.springweb.dto.EmployeeDto;
import jakarta.annotation.PostConstruct;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping(path = "/employee")
public class EmployeeController {

//    @GetMapping(path = "/getEmployee")
//    public String getEmployee(){
//        return "HuHaha";
//    }

    @GetMapping(path = "/{empId}")
    public EmployeeDto getEmployeeById(@PathVariable(name="empId") Long id){
        return new EmployeeDto(id,"Soumyajit",26,"soumyajit2k18@gmail.com", LocalDate.of(2022,06,26));
    }

    @GetMapping
    public String getEmployee(@RequestParam(required = false) Integer age,
                              @RequestParam(required = false) String name){
        return "Age is "+age +" ,name is: "+ name;
    }

    @PostMapping
    public EmployeeDto createEmp( @RequestBody EmployeeDto employeeDto){
        employeeDto.setEmpId(1L);
        return employeeDto;
    }
    @PutMapping
    public String updateEmp(){
        return "Hello from put";
    }
}
