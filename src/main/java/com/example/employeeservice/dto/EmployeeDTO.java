package com.example.employeeservice.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class EmployeeData implements Serializable {
    private String name;
    private String surname;
    private String email;
}
