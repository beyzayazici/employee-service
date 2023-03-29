package com.example.employeeservice.service;

import com.example.employeeservice.dto.EmployeeDTO;
import com.example.employeeservice.model.Employee;
import com.example.employeeservice.requests.EmployeeCreateRequest;
import com.example.employeeservice.requests.EmployeeUpdateRequest;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface EmployeeService {
    ResponseEntity<String> addEmployee(EmployeeCreateRequest request);
    ResponseEntity<String> updateEmployee(EmployeeUpdateRequest request);
    ResponseEntity<List<EmployeeDTO>> findEmployeesWithName(String name);
    ResponseEntity<List<EmployeeDTO>> getAllEmployees();
    ResponseEntity<Page<Employee>> getEmployeesWithPagination(int offset, int pageSize);
    ResponseEntity<String> deleteEmployee(Long employeeId);
}
