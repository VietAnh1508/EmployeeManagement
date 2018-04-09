package com.tma.employee.service;

import com.tma.employee.model.Employee;
import com.tma.employee.model.Role;

import java.util.List;

public interface RoleService {

    List<Role> findAll();

    Role findOne(int id);

    void save(Role role);

    void saveAndFlush(Role role);

    void setEmployeeToRole(Employee employee);

    void removeEmployeeFromRoles(Employee employee);

    void delete(int id);

}
