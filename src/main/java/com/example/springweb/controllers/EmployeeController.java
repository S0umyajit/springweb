package com.example.springweb.controllers;

import com.example.springweb.dto.EmployeeDto;
import com.example.springweb.entities.EmployeeEntity;
import com.example.springweb.repositories.EmployeeRepository;
import com.example.springweb.service.EmployeeService;
import jakarta.annotation.PostConstruct;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(path = "/employee")
public class EmployeeController {

//    @GetMapping(path = "/getEmployee")
//    public String getEmployee(){
//        return "HuHaha";
//    }
//
//    @GetMapping(path = "/{empId}")
//    public EmployeeDto getEmployeeById(@PathVariable(name="empId") Long id){
//        return new EmployeeDto(id,"Soumyajit",26,"soumyajit2k18@gmail.com", LocalDate.of(2022,06,26));
//    }
//
//    @GetMapping
//    public String getEmployee(@RequestParam(required = false) Integer age,
//                              @RequestParam(required = false) String name){
//        return "Age is "+age +" ,name is: "+ name;
//    }
//
//    @PostMapping
//    public EmployeeDto createEmp( @RequestBody EmployeeDto employeeDto){
//        employeeDto.setEmpId(1L);
//        return employeeDto;
//    }
//    @PutMapping
//    public String updateEmp(){
//        return "Hello from put";
//    }

//    private final EmployeeRepository employeeRepository;
//
//    public EmployeeController(EmployeeRepository employeeRepository){
//        this.employeeRepository=employeeRepository;
//    }

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService){
        this.employeeService=employeeService;
    }
    @GetMapping(path = "/{empId}")
    public EmployeeEntity getEmployeeById(@PathVariable(name="empId") Long id){
        return employeeService.findById(id);
    }

    @GetMapping
    public List<EmployeeEntity> getEmployee(@RequestParam(required = false) Integer age,
                                            @RequestParam(required = false) String name){
        return employeeService.findAll();
    }
    @PostMapping
    public EmployeeEntity createEmp( @RequestBody EmployeeEntity employeeEntity) {
        return employeeService.save(employeeEntity);
    }
}
