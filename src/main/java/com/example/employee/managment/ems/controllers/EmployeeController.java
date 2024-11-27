package com.example.employee.managment.ems.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.employee.managment.ems.dto.request.CreateEmployeeRequest;
import com.example.employee.managment.ems.dto.request.UpdateEmployeeRequest;
import com.example.employee.managment.ems.dto.response.EmployeeResponse;
import com.example.employee.managment.ems.dto.response.OrderEnum;
import com.example.employee.managment.ems.services.EmployeeService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
@RequestMapping("/api/v1/employees")
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;
    @PostMapping
    public EmployeeResponse createEmployee(@RequestBody @Valid CreateEmployeeRequest createEmployeeRequest){
        return employeeService.createEmployee(createEmployeeRequest);
    }
    @GetMapping("/{id}")
    public EmployeeResponse getEmployeeById(@PathVariable("id") String id) {
        return employeeService.getEmployeeById(id);
    }
 
    @GetMapping
    public Page<EmployeeResponse> getMethodName(@RequestParam(name = "page",required = false,defaultValue = "0") int page,
                                                @RequestParam(name = "pageSize",required = false,defaultValue = "10") int pageSize,
                                                @RequestParam(name = "order",required = false,defaultValue = "DESC") OrderEnum orderEnum) {
        return employeeService.getListOfEmployees(page, pageSize, orderEnum);
    }

    @PutMapping("/{id}")
    public EmployeeResponse putMethodName(@PathVariable String id, @RequestBody @Valid UpdateEmployeeRequest updateEmployeeRequest) {
        return employeeService.updateEmployee(id,updateEmployeeRequest);
    }
    
    @DeleteMapping("/{id}")
    public String getMethodName(@PathVariable String id) {
        return employeeService.deleteEmployeeById(id);
    }
    
    
}
