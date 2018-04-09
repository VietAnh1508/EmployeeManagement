package com.tma.employee.service;

import com.tma.employee.model.Department;
import com.tma.employee.model.Employee;

import java.util.List;

public interface DepartmentService {

    List<Department> findAll();

    Department findOne(int id);

    void addEmployeeToDepartment(Employee employee);

    void removeEmployeeFromDepartment(Employee employee);

    void save(Department department);

    void saveAndFlush(Department department);

    void delete(int id);

}
