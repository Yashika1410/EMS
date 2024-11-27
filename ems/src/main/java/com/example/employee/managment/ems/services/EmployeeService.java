package com.example.employee.managment.ems.services;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.data.domain.Pageable;
import com.example.employee.managment.ems.dto.request.CreateEmployeeRequest;
import com.example.employee.managment.ems.dto.request.UpdateEmployeeRequest;
import com.example.employee.managment.ems.dto.response.EmployeeResponse;
import com.example.employee.managment.ems.dto.response.OrderEnum;
import com.example.employee.managment.ems.entities.Employee;
import com.example.employee.managment.ems.repositories.EmployeeRepository;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeResponse createEmployee(CreateEmployeeRequest createEmployeeRequest){
        if(employeeRepository.existsByEmailIgnoreCaseAndIsDeletedIsFalse(createEmployeeRequest.getEmail()))
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email Id already exists");

        Employee employee = Employee.builder()
                            .address(createEmployeeRequest.getAddress())
                            .designation(createEmployeeRequest.getDesignation())
                            .email(createEmployeeRequest.getEmail())
                            .type(createEmployeeRequest.getType())
                            .name(createEmployeeRequest.getName())
                            .createdAt(LocalDateTime.now())
                            .updatedAt(LocalDateTime.now())
                            .isDeleted(false)
                            .build();
        employee = employeeRepository.save(employee);
        return EmployeeResponse.modelToResponse(employee);
    }

    public EmployeeResponse getEmployeeById(String id){
        return  EmployeeResponse.modelToResponse(findEmployeeById(id));
    }
    
    private Employee findEmployeeById(String id) {
        return employeeRepository.findByIdAndIsDeletedIsFalse(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found by id " + id));
    }

    public String deleteEmployeeById(String id){
        Employee employee = findEmployeeById(id);
        employee.setIsDeleted(true);
        employee.setUpdatedAt(LocalDateTime.now());
        employeeRepository.save(employee);
        return "Sucessfully deleted the employee by id "+id;
    }

    public Page<EmployeeResponse> getListOfEmployees(int page, int pageSize,OrderEnum order){
        Pageable pageable = PageRequest.of(page, pageSize, Sort.by(order.equals(OrderEnum.ASC)?Direction.ASC:Direction.DESC, "updatedAt"));
        Page<Employee> eployess = employeeRepository.findAllByIsDeletedFalse(pageable);
        return new PageImpl<EmployeeResponse>(eployess.getContent().stream().map(e->EmployeeResponse.modelToResponse(e)).toList(), eployess.getPageable(), eployess.getTotalElements());
    }

    public EmployeeResponse updateEmployee(String id, UpdateEmployeeRequest updateEmployeeRequest) {
        Employee employee = findEmployeeById(id);
        employee.setAddress(updateEmployeeRequest.getAddress());
        employee.setDesignation(updateEmployeeRequest.getDesignation());
        employee.setName(updateEmployeeRequest.getName());
        employee.setType(updateEmployeeRequest.getType());
        employee.setUpdatedAt(LocalDateTime.now());
        return EmployeeResponse.modelToResponse(employeeRepository.save(employee));
    }
    
    
}
