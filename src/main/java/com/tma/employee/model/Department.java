package com.tma.employee.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "department")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "department_name", length = 50)
    private String departmentName;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "department")
    private List<Employee> employeeList = new ArrayList<>();

    public Department() {
    }

    public Department(String name) {
        this.departmentName = name;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }

    @Override
    public String toString() {
        return "Department [id=" + id + ", department_name='" + departmentName + "']";
    }

}
