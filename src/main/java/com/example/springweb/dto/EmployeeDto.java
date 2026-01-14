package com.example.springweb.dto;



import com.example.springweb.annotations.EmployeeRoleValidation;
import jakarta.validation.constraints.*;

import java.time.LocalDate;


public class EmployeeDto {

    private Long empId;
    @NotNull(message = "name can't be empty")
    @NotEmpty(message = "name can't be empty")
    @NotBlank(message = "name can't be blank")
    @Size(min = 3, max = 10, message = "name should have at least 3 characters and at most 10")
    private String name;

    @Max(value = 60, message = "Age can't be more than 60")
    @Min(value = 18, message = "Age can't be less than 18")
    private Integer age;

    @Email(message = "Email should be a valid email")
    private String email;

//    @Pattern(regexp = "^ADMIN|USER$",message = "Role Must be ADMIN or USER")
    @NotBlank(message = "role must be provided")
    @EmployeeRoleValidation
    private String role;

    @NotNull(message = "Salary should be provided")
    @Positive(message = "Salary should be positive")
    @Digits(integer = 6,fraction = 2,message = "salary format should be in XXXXXX.YY")
    @DecimalMax("10000.99")
    @DecimalMin("100.11")
    private Double salary;

    @PastOrPresent(message = "date of joining date can't be in future")
    private LocalDate dateOfJoining;

    @AssertTrue(message = "Employee Should be active employee")
    private Boolean isActive;

    public EmployeeDto(){

    }
    public EmployeeDto(Long empId, String name, int age, String email, LocalDate dateOfJoining, boolean isActive) {
        this.empId = empId;
        this.name = name;
        this.age = age;
        this.email = email;
        this.dateOfJoining = dateOfJoining;
        this.isActive=isActive;
    }

    public Long getEmpId() {
        return empId;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
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

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDateOfJoining(LocalDate dateOfJoining) {
        this.dateOfJoining = dateOfJoining;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setActive(Boolean isActive) {
        this.isActive=isActive;
    }
    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
