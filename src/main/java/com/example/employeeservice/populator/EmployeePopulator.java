package com.example.employeeservice.populator;

import com.example.employeeservice.dto.EmployeeDTO;
import com.example.employeeservice.library.Populator;
import com.example.employeeservice.model.Employee;
import org.springframework.stereotype.Component;

@Component
public class EmployeePopulator implements Populator<Employee, EmployeeDTO> {

    @Override
    public void populate(Employee source, EmployeeDTO target) {
        target.setName(source.getName());
        target.setPhone(source.getPhone());
        target.setCountry(source.getCountry());
        target.setRegion(source.getRegion());
    }
}
