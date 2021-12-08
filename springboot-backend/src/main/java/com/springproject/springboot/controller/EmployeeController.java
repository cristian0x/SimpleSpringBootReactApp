package com.springproject.springboot.controller;

import com.springproject.springboot.exception.ResourceNotFoundException;
import com.springproject.springboot.model.Employee;
import com.springproject.springboot.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/employees")
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    // build create employee REST API
    @PostMapping
    public Employee createEmployee(@RequestBody Employee employee) {
        return employeeRepository.save(employee);
    }

    // build get employee by id REST API
    @GetMapping("{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable long id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee with id: " + id + " does not exist"));
        return ResponseEntity.ok(employee);
    }

    // build update employee REST API
    @PutMapping("{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable long id, @RequestBody Employee employeeDetails) {
        Employee updatedEmployee = employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee with id: " + id + " does not exist"));

        updatedEmployee.setFirstName(employeeDetails.getFirstName());
        updatedEmployee.setLastName(employeeDetails.getLastName());
        updatedEmployee.setEmailId(employeeDetails.getEmailId());

        employeeRepository.save(updatedEmployee);
        return ResponseEntity.ok(updatedEmployee);
    }

    //build delete employee REST API
    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deleteEmployee(@PathVariable long id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee with id: " + id + " does not exist"));

        employeeRepository.delete(employee);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
