package com.example.employee.managment.ems.dto.request;

import java.io.Serializable;

import com.example.employee.managment.ems.entities.JobType;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateEmployeeRequest implements Serializable {
    @NotBlank(message = "Name is required")
    private String name;
    @NotBlank(message = "Email is required")
    @Email(message = "Email is not valid")
    private String email;
    @NotBlank(message = "Address is required")
    private String address;
    @NotNull(message = "Jobtype is required")
    private JobType type;
    @NotBlank(message = "Designation is required")
    private String designation;
}
