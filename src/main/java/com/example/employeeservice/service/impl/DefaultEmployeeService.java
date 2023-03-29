package com.example.employeeservice.service.impl;

import com.example.employeeservice.dto.EmployeeDTO;
import com.example.employeeservice.model.Employee;
import com.example.employeeservice.populator.EmployeePopulator;
import com.example.employeeservice.repository.EmployeeRepository;
import com.example.employeeservice.requests.EmployeeCreateRequest;
import com.example.employeeservice.requests.EmployeeUpdateRequest;
import com.example.employeeservice.service.EmployeeService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class DefaultEmployeeService implements EmployeeService {
    private static final Logger LOG = Logger.getLogger( DefaultEmployeeService.class.getName() );

    @Resource
    private EmployeeRepository employeeRepository;

    @Resource
    private EmployeePopulator employeePopulator;

    @Override
    public ResponseEntity<String> addEmployee(EmployeeCreateRequest request) {
        Employee employee = new Employee();
        employee.setName(request.getName());
        employee.setEmail(request.getEmail());
        employee.setCountry(request.getCountry());
        employee.setPhone(request.getPhone());
        employee.setRegion(request.getRegion());
        employee.setPostalZip(request.getPostalZip());
        employee.setAddress(request.getAddress());
        Long id = employeeRepository.saveAndFlush(employee).getId();
        String message = request.getName() + " employee created with id : " + id + ".";
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
    @Override
    public ResponseEntity<String> updateEmployee(EmployeeUpdateRequest request) {
        try{
            Optional<Employee> employeeOptional =employeeRepository.findById(request.getId());
            if(employeeOptional.isPresent()){
                Employee employee= employeeOptional.get();
                if(Objects.nonNull(request.getName())) {
                    employee.setName(request.getName());
                }
                if(Objects.nonNull(request.getPhone())) {
                    employee.setPhone(request.getPhone());
                }
                if(Objects.nonNull(request.getCountry())) {
                    employee.setCountry(request.getCountry());
                }
                if(Objects.nonNull(request.getAddress())) {
                    employee.setAddress(request.getAddress());
                }
                if(Objects.nonNull(request.getEmail())) {
                    employee.setEmail(request.getEmail());
                }
                if(Objects.nonNull(request.getPostalZip())) {
                    employee.setPostalZip(request.getPostalZip());
                }
                if(Objects.nonNull(request.getRegion())) {
                    employee.setRegion(request.getRegion());
                }
                employeeRepository.save(employee);
            }
            else{
                LOG.error("Given employee id : " + request.getId() + " could not find database.");
                return new ResponseEntity<>("Employee not found.", HttpStatus.NOT_FOUND);
            }
        }
        catch (Exception e){
            LOG.error("An error occured when update the employee id : " + request.getId() + ".The error is : " + e.getMessage());
        }
        return new ResponseEntity<>("Employee updated.", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<EmployeeDTO>> findEmployeesWithName(String name) {
        List<Employee> employees= employeeRepository.findByNameContainingIgnoreCase(name);
        return new ResponseEntity<>(getAllEmployeeDTO(employees), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees() {
        List<Employee> employees =  employeeRepository.findAll();
        return new ResponseEntity<>(getAllEmployeeDTO(employees), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Page<Employee>> getEmployeesWithPagination(int offset, int pageSize){
        Page<Employee> employees = employeeRepository.findAll(PageRequest.of(offset,pageSize).withSort(Sort.by(Sort.Direction.ASC,"name")));
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> deleteEmployee(Long employeeId) {
        try{
            Optional<Employee> employee =employeeRepository.findById(employeeId);
            if(employee.isPresent()){
                employeeRepository.deleteById(employeeId);
            }
            else{
                LOG.error("Given employee id : " + employeeId + " could not find database.");
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
        catch (Exception e){
            LOG.error("An error occured when update the employee id : " + employeeId + ".The error is : " + e.getMessage());
        }
        return new ResponseEntity<>("The employee deleted.", HttpStatus.OK);
    }

    private List<EmployeeDTO> getAllEmployeeDTO(List<Employee> employees) {
        List<EmployeeDTO> employeeDTOList = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(employees)) {
            employees.forEach(employee-> {
                EmployeeDTO employeeDTO = new EmployeeDTO();
                employeePopulator.populate(employee, employeeDTO);
                employeeDTOList.add(employeeDTO);
            });
        }
        return employeeDTOList;
    }
}
