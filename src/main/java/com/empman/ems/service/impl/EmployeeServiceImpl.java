package com.empman.ems.service.impl;

import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.empman.ems.dto.EmployeeDto;
import com.empman.ems.entity.Employee;
import com.empman.ems.mapper.EmployeeMapper;
import com.empman.ems.repository.EmployeeRepository;
import com.empman.ems.service.EmployeeService;
import java.util.*;

import lombok.AllArgsConstructor;
@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        Employee employee = EmployeeMapper.mapToEmployee(employeeDto);
        Employee savedEmployee = employeeRepository.save(employee);
        return EmployeeMapper.mapToEmployeeDto(savedEmployee);
    }

    @Override
    public EmployeeDto getEmloyeeById(Long id) {
        Employee employee = employeeRepository.findById(id).orElseThrow();
        return EmployeeMapper.mapToEmployeeDto(employee);
    }

    @Override
    public List<EmployeeDto> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        return employees.stream()
                .map(employee -> EmployeeMapper.mapToEmployeeDto(employee))
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeDto updateEmployee(Long id, EmployeeDto updatedEmployeeDto) {
        Employee employee = employeeRepository.findById(id).orElseThrow();
        employee.setFirstName(updatedEmployeeDto.getFirstName());
        employee.setLastName(updatedEmployeeDto.getLastName());
        employee.setEmail(updatedEmployeeDto.getEmail());
        Employee updatedEmployee =  employeeRepository.save(employee);
        return EmployeeMapper.mapToEmployeeDto(updatedEmployee);
    }

}
