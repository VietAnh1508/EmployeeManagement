package com.tma.employee.service.impl;

import com.tma.employee.model.Employee;
import com.tma.employee.model.Role;
import com.tma.employee.repository.RoleRepository;
import com.tma.employee.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class RoleServiceImpl implements RoleService {

    private RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepo) {
        this.roleRepository = roleRepo;
    }

    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public Role findOne(int id) {
        return roleRepository.findOne(id);
    }

    @Override
    public void save(Role role) {
        roleRepository.save(role);
    }

    @Override
    public void saveAndFlush(Role role) {
        roleRepository.saveAndFlush(role);
    }

    @Override
    public void setEmployeeToRole(Employee employee) {
        Set<Role> roles = employee.getRoles();
        for (Role role : roles) {
            Role existedRole = roleRepository.getOne(Integer.valueOf(role.getName()));
            existedRole.getEmployees().add(employee);
            roleRepository.save(existedRole);
        }
    }

    @Override
    public void removeEmployeeFromRoles(Employee employee) {
        Set<Role> roles = employee.getRoles();
        for (Role role : roles) {
            Role existedRole = roleRepository.getByName(role.getName());
            existedRole.getEmployees().remove(employee);
            roleRepository.save(existedRole);
        }
    }

    @Override
    public void delete(int id) {
        roleRepository.delete(id);
    }

}
