package com.example.springweb.service;

import com.example.springweb.dto.EmployeeDto;
import com.example.springweb.entities.EmployeeEntity;
import com.example.springweb.repositories.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
    boolean empExist(long employeeId){
        return employeeRepository.existsById(employeeId);
    }
    public String deleteEmployeeById(Long employeeId) {
        boolean empExists=empExist(employeeId);
        if(!empExists)
            return "Employee id doesn't exists";
        employeeRepository.deleteById(employeeId);
        return "Employee id: "+employeeId +" has been deleted";
    }

    public EmployeeDto updatePartialEmployeeById(Long employeeId, Map<String, Object> updates) {
        boolean empExists=empExist(employeeId);

        if(!empExists) return null;
        EmployeeEntity employeeEntity=employeeRepository.findById(employeeId).get();
        updates.forEach((field,value) -> {
            Field fieldToBeUpdated=ReflectionUtils.findRequiredField(EmployeeEntity.class,field);
            fieldToBeUpdated.setAccessible(true);
            Object finalValue=value;

            if(fieldToBeUpdated.getType().equals(LocalDate.class)){
                finalValue=LocalDate.parse(value.toString());
            }
            ReflectionUtils.setField(fieldToBeUpdated,employeeEntity,finalValue);
        });
        return modelMapper.map(employeeRepository.save(employeeEntity),EmployeeDto.class);
    }
}
