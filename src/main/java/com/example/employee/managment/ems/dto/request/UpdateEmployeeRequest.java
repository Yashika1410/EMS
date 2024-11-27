package com.example.employee.managment.ems.dto.request;

import com.example.employee.managment.ems.entities.JobType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateEmployeeRequest {
    @NotBlank(message = "Name is required")
    private String name;
    @NotBlank(message = "Address is required")
    private String address;
    @NotNull(message = "Jobtype is required")
    private JobType type;
    @NotBlank(message = "Designation is required")
    private String designation;
}
