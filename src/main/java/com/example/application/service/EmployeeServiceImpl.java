package com.example.application.service;

import com.example.application.dal.entity.Employee;
import com.example.application.dal.repository.EmployeeRepository;
import com.example.application.exception.EmployeeAlreadyExistsException;
import com.example.application.exception.EmployeeDoesNotExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Employee saveEmployee(Employee employee) {

        if(employee == null) {
            throw new EmployeeDoesNotExistException("Employee does not exist");
        }

        if(employeeCheckByEmail(employee.getEmail())) {
            throw new EmployeeAlreadyExistsException("Employee with specified email already exists");
        }

        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> getAllEmployees() {
        List<Employee> employeeList = employeeRepository.findAll();
        employeeList.sort(Comparator.comparing(Employee::getCreatedDate));
        return employeeList;
    }

    boolean employeeCheckByEmail(String email) {
        return employeeRepository.findByEmail(email) != null;
    }
}
