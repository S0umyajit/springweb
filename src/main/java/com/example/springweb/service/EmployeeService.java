package com.example.springweb.service;

import com.example.springweb.dto.EmployeeDto;
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

//    public EmployeeEntity findById(Long id) {
//        return employeeRepository.findById(id).orElse(null);
//    }
public EmployeeDto findById(Long id) {
    EmployeeEntity employeeEntity = employeeRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Employee not found"));

    // 2. Now it is safe to access data because we know it's not null
    return new EmployeeDto(
            employeeEntity.getEmpId(),
            employeeEntity.getName(),
            employeeEntity.getAge(),
            employeeEntity.getEmail(),
            employeeEntity.getDateOfJoining()
        );
}

//    public EmployeeEntity save(EmployeeEntity inputEmployee) {
//        return employeeRepository.save(inputEmployee);
//    }
        public EmployeeDto save(EmployeeEntity inputEmployee) {

        EmployeeEntity employeeEntity= employeeRepository.save(inputEmployee);

        return new EmployeeDto(
                employeeEntity.getEmpId(),
                employeeEntity.getName(),
                employeeEntity.getAge(),
                employeeEntity.getEmail(),
                employeeEntity.getDateOfJoining()
        );
    }
//
//    public List<EmployeeEntity> findAll() {
//        return employeeRepository.findAll();
//    }
}
