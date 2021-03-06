package com.tma.employee.repository;

import com.tma.employee.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {

    Department findByName(String departmentName);

}
