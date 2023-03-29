package com.example.employeeservice.controller;

import com.example.employeeservice.dto.EmployeeDTO;
import com.example.employeeservice.model.Employee;
import com.example.employeeservice.requests.EmployeeCreateRequest;
import com.example.employeeservice.requests.EmployeeUpdateRequest;
import com.example.employeeservice.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/employee")
public class EmployeeController {

    @Resource
    private EmployeeService employeeService;

    @PostMapping(value = "/add")
    @Operation(summary = "add employee from database")
    public ResponseEntity<String> addEmployee(@RequestBody @Valid EmployeeCreateRequest request){
        return employeeService.addEmployee(request);
    }

    @PutMapping(value = "/update")
    @Operation(summary = "update employee from database")
    public ResponseEntity<String> updateEmployee(@RequestBody @Valid EmployeeUpdateRequest request){
        return employeeService.updateEmployee(request);
    }

    @GetMapping(value = "/search")
    @Operation(summary = "get employee from database")
    public ResponseEntity<List<EmployeeDTO>> getEmployee(@RequestParam("name") String name){
        return employeeService.findEmployeesWithName(name);
    }

    @GetMapping(value = "/retrieve")
    @Operation(summary = "retrieve all employees  from database")
    public ResponseEntity<List<EmployeeDTO>> retrieveAllEmployee(){
        return employeeService.getAllEmployees();
    }

    @GetMapping(value = "/retrieveWithPagination/{offset}/{pageSize}")
    @Operation(summary = "retrieve all employees from database with pagination")
    public ResponseEntity<Page<Employee>> retrieveWithPagination(@PathVariable("offset") int offset, @PathVariable("pageSize") int pageSize){
        return employeeService.getEmployeesWithPagination(offset, pageSize);
    }

    @DeleteMapping(value = "/delete")
    @Operation(summary = "delete employee by id from database")
    public ResponseEntity<String> deleteEmployee(@RequestParam("employeeId") Long employeeId){
        return employeeService.deleteEmployee(employeeId);
    }
}
