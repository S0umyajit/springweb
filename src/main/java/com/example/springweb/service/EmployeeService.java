package com.example.springweb.service;

import com.example.springweb.dto.EmployeeDto;
import com.example.springweb.entities.EmployeeEntity;
import com.example.springweb.repositories.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    private final ModelMapper modelMapper;

    public EmployeeService(ModelMapper modelMapper){
        this.modelMapper=modelMapper;
    }

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
    return modelMapper.map(employeeEntity,EmployeeDto.class);
}

//    public EmployeeEntity save(EmployeeEntity inputEmployee) {
//        return employeeRepository.save(inputEmployee);
//    }

    public List<EmployeeDto> findAll() {
        List<EmployeeEntity> employeeEntity=employeeRepository.findAll();
//        return employeeEntity
//                .stream()
//                .map(employeeEntity1 ->modelMapper.map(employeeEntity1,EmployeeDto.class))
//                .collect(Collectors.toList());
        List<EmployeeDto> empDto=new ArrayList<>();

        for(EmployeeEntity entity:employeeEntity){
            EmployeeDto dto= modelMapper.map(entity,EmployeeDto.class);
            empDto.add(dto);
        }
        return empDto;
    }
    public EmployeeDto save(EmployeeDto inputEmployee) {
        EmployeeEntity toSaveEmp=modelMapper.map(inputEmployee,EmployeeEntity.class);
        EmployeeEntity savedEmp= employeeRepository.save(toSaveEmp);
        return modelMapper.map(savedEmp,EmployeeDto.class);
    }

    public EmployeeDto updateEmployeeById(Long employeeId, EmployeeDto employeeDto) {
        boolean empExists=employeeRepository.existsById(employeeId);
        if(empExists) {
            EmployeeEntity employeeEntity = modelMapper.map(employeeDto, EmployeeEntity.class);
            employeeEntity.setEmpId(employeeId);
            EmployeeEntity employeeEntity1 = employeeRepository.save(employeeEntity);
            return modelMapper.map(employeeEntity1, EmployeeDto.class);
        }
        else {
            EmployeeEntity employeeEntity=modelMapper.map(employeeDto,EmployeeEntity.class);
            EmployeeEntity employeeEntity1=employeeRepository.save(employeeEntity);
            return modelMapper.map(employeeEntity1,EmployeeDto.class);
        }
    }

    public String deleteEmployeeById(Long employeeId) {
        boolean empExists=employeeRepository.existsById(employeeId);
        if(!empExists)
            return "Employee id doesn't exists";
        employeeRepository.deleteById(employeeId);
        return "Employee id: "+employeeId +" has been deleted";
    }
}
