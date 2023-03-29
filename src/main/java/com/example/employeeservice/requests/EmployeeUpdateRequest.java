package com.example.employeeservice.requests;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class EmployeeUpdateRequest {
    @NonNull
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String address;
    private String region;
    private String postalZip;
    private String country;
}
