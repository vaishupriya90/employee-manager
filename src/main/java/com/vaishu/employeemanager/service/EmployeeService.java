package com.vaishu.employeemanager.service;

import com.vaishu.employeemanager.data.EmployeeRepository;
import com.vaishu.employeemanager.exception.userNotFoundException;
import com.vaishu.employeemanager.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class EmployeeService {

    @Autowired
    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Employee addEmployee(Employee employee){
        employee.setEmployeeCode(UUID.randomUUID().toString());// UUID generates a random number
        return employeeRepository.save(employee);
    }

    public List<Employee> findAllEmployees(){
        return employeeRepository.findAll();
    }

    public Employee findEmployeeById(Long id){
        return employeeRepository.findEmployeeById(id)
                .orElseThrow(()-> new userNotFoundException("User by id " + id + " was not found"));
        //findByEmployeeId method is not available by default so we have to create this method in the employeeRepo
        //which will be a query method- meaning: just the method signature is enough , spring will tc of the implementation
        //provided with proper naming convention - findEmployeeById.

        //this method returns an optional<>, but our expected return type is Employee,
        // so  to fix this we will have to create an exception to be thrown if the
        //employee is not available with the given id.

        //userNotFoundException is a custom exception created with a custom message.
    }


    public Employee updateEmployee(Employee employee){
        return employeeRepository.save(employee);
    }
    public void deleteEmployee(Long id){
        employeeRepository.deleteEmployeeById(id);
    }
}
