package com.example.employee.managment.ems.repositories;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import com.example.employee.managment.ems.entities.Employee;

public interface EmployeeRepository extends CrudRepository<Employee,String> {
    Boolean existsByEmailIgnoreCaseAndIsDeletedIsFalse(String email);

    Optional<Employee> findByIdAndIsDeletedIsFalse(String id);

    Page<Employee> findAllByIsDeletedFalse(Pageable pageable);
}
