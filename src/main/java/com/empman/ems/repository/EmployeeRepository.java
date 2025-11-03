package com.empman.ems.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.empman.ems.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {
    
}
