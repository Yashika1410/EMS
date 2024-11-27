package com.example.employee.managment.ems.dto.response;

import java.io.Serializable;

import com.example.employee.managment.ems.entities.Employee;
import com.example.employee.managment.ems.entities.JobType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployeeResponse implements Serializable {
    private String id;
    private String name;
    private String email;
    private String address;
    private JobType type;
    private String designation;

    public static EmployeeResponse modelToResponse(Employee employee){
        return EmployeeResponse.builder()
                               .address(employee.getAddress())
                               .designation(employee.getDesignation())
                               .email(employee.getEmail())
                               .id(employee.getId())
                               .name(employee.getName())
                               .type(employee.getType())
                               .build();

    }
}
