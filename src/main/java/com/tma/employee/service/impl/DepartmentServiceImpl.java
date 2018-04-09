package com.tma.employee.service.impl;

import com.tma.employee.model.Department;
import com.tma.employee.model.Employee;
import com.tma.employee.repository.DepartmentRepository;
import com.tma.employee.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DepartmentServiceImpl implements DepartmentService {

    private DepartmentRepository departmentRepository;

    @Autowired
    public DepartmentServiceImpl(DepartmentRepository departmentRepo) {
        this.departmentRepository = departmentRepo;
    }

    @Override
    public List<Department> findAll() {
        return departmentRepository.findAll();
    }

    @Override
    public Department findOne(int id) {
        return departmentRepository.findOne(id);
    }

    @Override
    public void addEmployeeToDepartment(Employee employee) {
        Department department = departmentRepository.findOne(employee.getDepartment().getId());
        department.getEmployeeList().add(employee);
        departmentRepository.saveAndFlush(department);
    }

    @Override
    public void removeEmployeeFromDepartment(Employee employee) {
        Department department = departmentRepository.findOne(employee.getDepartment().getId());
        department.getEmployeeList().remove(employee);
        departmentRepository.save(department);
    }

    @Override
    public void save(Department department) {
        departmentRepository.save(department);
    }

    @Override
    public void saveAndFlush(Department department) {
        departmentRepository.saveAndFlush(department);
    }

    @Override
    public void delete(int id) {
        departmentRepository.delete(id);
    }

}
