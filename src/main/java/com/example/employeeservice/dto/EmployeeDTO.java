package com.example.employeeservice.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class EmployeeDTO implements Serializable {
    private String name;
    private String phone;
    private String country;
    private String region;
}
