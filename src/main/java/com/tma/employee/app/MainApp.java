package com.tma.employee.app;

import com.tma.employee.configuration.AppConfig;
import com.tma.employee.model.Department;
import com.tma.employee.model.Employee;
import com.tma.employee.model.Role;
import com.tma.employee.repository.DepartmentRepository;
import com.tma.employee.repository.EmployeeRepository;
import com.tma.employee.repository.RoleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainApp {

    public static void main(String[] args) {
        final Logger logger = LoggerFactory.getLogger(MainApp.class);

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(AppConfig.class);
        context.refresh();

        EmployeeRepository employeeRepo = (EmployeeRepository) context.getBean("employeeRepository");
        DepartmentRepository departmentRepo = (DepartmentRepository) context.getBean("departmentRepository");
        RoleRepository roleRepo = (RoleRepository) context.getBean("roleRepository");

        logger.info("Create initial data");

        logger.info("Creating new roles...");
        Role managerRole = new Role("Manager");
        Role leaderRole = new Role("Leader");
        Role developerRole = new Role("Developer");

        logger.info("Store roles to database");
        roleRepo.saveAndFlush(managerRole);
        roleRepo.saveAndFlush(leaderRole);
        roleRepo.saveAndFlush(developerRole);

        logger.info("Creating departments...");
        Department lab1 = new Department("Lab 1");
        Department lab2 = new Department("Lab 2");

        logger.info("Store departments to database...");
        departmentRepo.saveAndFlush(lab1);
        departmentRepo.saveAndFlush(lab2);

        logger.info("Create new employee...");
        Employee empJohn = new Employee("John");
        empJohn.setDepartment(lab1);
        empJohn.getRoles().add(managerRole);

        logger.info("Store John to database");
        employeeRepo.saveAndFlush(empJohn);

        logger.info("Update employee_role and department");
        managerRole.getEmployeeSet().add(empJohn);
        roleRepo.saveAndFlush(managerRole);

        lab1.getEmployeeList().add(empJohn);
        departmentRepo.saveAndFlush(lab1);

        logger.info("Create another employee");
        Employee empTony = new Employee("Tony");
        empTony.getRoles().add(leaderRole);
        empJohn.getRoles().add(developerRole);
        empJohn.setDepartment(lab2);

        logger.info("Store Tony to database");
        employeeRepo.saveAndFlush(empTony);

        logger.info("Update employee_role and department");
        leaderRole.getEmployeeSet().add(empTony);
        developerRole.getEmployeeSet().add(empTony);
        roleRepo.saveAndFlush(leaderRole);
        roleRepo.saveAndFlush(developerRole);

        lab2.getEmployeeList().add(empTony);
    }

}
