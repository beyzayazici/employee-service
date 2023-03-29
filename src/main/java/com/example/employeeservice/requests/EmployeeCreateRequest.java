package com.example.employeeservice.requests;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class EmployeeCreateRequest {
    @NonNull
    private String name;
    @NonNull
    private String email;
    @NonNull
    private String phone;
    private String address;
    @NonNull
    private String region;
    private String postalZip;
    @NonNull
    private String country;
}
