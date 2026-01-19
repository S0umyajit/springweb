package com.example.springweb.controllers;

import com.example.springweb.dto.EmployeeDto;
import com.example.springweb.entities.EmployeeEntity;
import com.example.springweb.exceptions.ResourceNotFoundException;
import com.example.springweb.repositories.EmployeeRepository;
import com.example.springweb.service.EmployeeService;
import jakarta.annotation.PostConstruct;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

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
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable(name="empId") Long id){
        Optional<EmployeeDto> employeeDto=employeeService.findById(id);
        return employeeDto.map(employeeDto1 -> ResponseEntity.ok(employeeDto1)).
                orElseThrow(()->new ResourceNotFoundException("Employee not found"));
    }

    @GetMapping
    public ResponseEntity<List<EmployeeDto>> getEmployee(@RequestParam(required = false) Integer age,
                                            @RequestParam(required = false) String name){
        return ResponseEntity.ok(employeeService.getAllEmployee());
    }
//    @PostMapping
//    public EmployeeEntity createEmp( @RequestBody EmployeeEntity employeeEntity) {
//        return employeeService.save(employeeEntity);
//    }
    @PostMapping
    public ResponseEntity<EmployeeDto> createEmp(@Valid @RequestBody EmployeeDto employeeDto) {
        EmployeeDto employeeDto1=employeeService.createEmp(employeeDto);
        return new ResponseEntity<>(employeeDto1,HttpStatus.CREATED);
    }
    @PutMapping(path="/{employeeId}")
    public ResponseEntity<EmployeeDto> updateEmployeeById(@PathVariable Long employeeId, @RequestBody @Valid EmployeeDto employeeDto){
        return ResponseEntity.ok(employeeService.updateEmployeeById(employeeId,employeeDto));
    }
    @DeleteMapping(path="/{employeeId}")
    public ResponseEntity<String> deleteEmployeeById(@PathVariable Long employeeId){
        Boolean empDelete= employeeService.deleteEmployeeById(employeeId);
        if(empDelete){
            return ResponseEntity.ok("Employee with ID " + employeeId + " deleted successfully.");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Employee with ID " + employeeId + " not found.");
    }

    @PatchMapping(path="/{employeeId}")
    public ResponseEntity<EmployeeDto> updatePartialEmployeeById(@RequestBody Map<String,Object> updates,
                                                 @PathVariable Long employeeId){
        EmployeeDto employeeDto=employeeService.updatePartialEmployeeById(employeeId,updates);
        if(employeeDto==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(employeeDto);
    }
}
