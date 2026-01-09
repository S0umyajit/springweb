package com.example.springweb.service;

import com.example.springweb.entities.EmployeeEntity;
import com.example.springweb.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

//    public EmployeeService(EmployeeRepository employeeRepository) {
//        this.employeeRepository = employeeRepository;
//    }

    public EmployeeEntity findById(Long id) {
        return employeeRepository.findById(id).orElse(null);
    }

    public EmployeeEntity save(EmployeeEntity inputEmployee) {
        return employeeRepository.save(inputEmployee);
    }

    public List<EmployeeEntity> findAll() {
        return employeeRepository.findAll();
    }
}
