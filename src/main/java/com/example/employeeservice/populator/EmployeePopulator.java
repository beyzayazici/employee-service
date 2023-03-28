package com.example.employeeservice.populator.impl;

import com.example.employeeservice.dto.EmployeeData;
import com.example.employeeservice.model.Employee;
import com.example.employeeservice.populator.Populator;

public class EmployeePopulator implements Populator<Employee, EmployeeData> {

    @Override
    public void populate(Employee source, EmployeeData target) {
        target.setName(source.getFirstName());
        target.setSurname(source.getLastName());
        target.setEmail(source.getEmail());
    }
}
