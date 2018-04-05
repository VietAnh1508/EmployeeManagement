package com.tma.employee.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String role;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "roles")
    private List<Employee> employeeSet = new ArrayList<>();

    public Role() {
    }

    public Role(String role) {
        this.role = role;
    }

    public Role(String role, List<Employee> employeeSet) {
        this.role = role;
        this.employeeSet = employeeSet;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<Employee> getEmployeeSet() {
        return employeeSet;
    }

    public void setEmployeeSet(List<Employee> employeeSet) {
        this.employeeSet = employeeSet;
    }

    @Override
    public String toString() {
        return "Role { id: " + id + ", role: '" + role + "'} ";
    }
}
