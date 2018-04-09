package com.tma.employee.service;

import com.tma.employee.model.Employee;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface EmployeeService {

    List<Employee> findAll();

    Employee findOne(int id);

    void saveAndFlush(Employee employee);

    void delete(int id);

}
