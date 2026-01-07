package com.example.springweb.dto;


import java.time.LocalDate;


public class EmployeeDto {

    private Long empId;
    private String name;
    private int age;
    private String email;
    private LocalDate dateOfJoining;

    public EmployeeDto(){

    }
    public EmployeeDto(Long empId, String name, int age, String email, LocalDate dateOfJoining) {
        this.empId = empId;
        this.name = name;
        this.age = age;
        this.email = email;
        this.dateOfJoining = dateOfJoining;
    }

    public Long getEmpId() {
        return empId;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getEmail() {
        return email;
    }

    public LocalDate getDateOfJoining() {
        return dateOfJoining;
    }

    public void setEmpId(Long empId) {
        this.empId = empId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDateOfJoining(LocalDate dateOfJoining) {
        this.dateOfJoining = dateOfJoining;
    }
}
