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

import java.util.List;
import java.util.Set;

public class MainApp {

    public static void main(String[] args) {
        final Logger logger = LoggerFactory.getLogger(MainApp.class);

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(AppConfig.class);
        context.refresh();

        EmployeeRepository employeeRepo = (EmployeeRepository) context.getBean("employeeRepository");
        DepartmentRepository departmentRepo = (DepartmentRepository) context.getBean("departmentRepository");
        RoleRepository roleRepo = (RoleRepository) context.getBean("roleRepository");

        logger.info("Clean up database...");
        employeeRepo.deleteAllInBatch();
        departmentRepo.deleteAllInBatch();
        roleRepo.deleteAllInBatch();

        logger.info("Create initial data");

        logger.info("Creating new roles...");
        Role managerRole = new Role("Manager");
        Role leaderRole = new Role("Leader");
        Role developerRole = new Role("Developer");

        logger.info("Creating departments...");
        Department lab1 = new Department("Lab 1");
        Department lab2 = new Department("Lab 2");

        logger.info("Create new employee...");
        Employee empJohn = new Employee("John");
        Employee empTony = new Employee("Tony");
        Employee empAnn = new Employee("Ann");

        logger.info("Add employees to departments");
        empJohn.setDepartment(lab1);
        empTony.setDepartment(lab2);
        empAnn.setDepartment(lab2);

        logger.info("Update department");
        lab1.getEmployeeList().add(empJohn);
        lab2.getEmployeeList().add(empTony);

        logger.info("Save department to database");
        departmentRepo.saveAndFlush(lab1);
        departmentRepo.saveAndFlush(lab2);

        logger.info("Save employee to database");
        employeeRepo.saveAndFlush(empJohn);
        employeeRepo.saveAndFlush(empTony);
        employeeRepo.saveAndFlush(empAnn);

        logger.info("Set role for employees");
        empJohn.getRoles().add(managerRole);

        empTony.getRoles().add(leaderRole);
        empTony.getRoles().add(developerRole);

        empAnn.getRoles().add(managerRole);

        logger.info("Update employee_role");
        managerRole.getEmployeeSet().add(empJohn);
        managerRole.getEmployeeSet().add(empAnn);

        leaderRole.getEmployeeSet().add(empTony);

        developerRole.getEmployeeSet().add(empTony);

        logger.info("Save role to database");
        roleRepo.saveAndFlush(managerRole);
        roleRepo.saveAndFlush(leaderRole);
        roleRepo.saveAndFlush(developerRole);

        logger.info("Save employee to database");
        employeeRepo.saveAndFlush(empJohn);
        employeeRepo.saveAndFlush(empTony);
        employeeRepo.saveAndFlush(empAnn);

        logger.info("Employee list");
        List<Employee> employees = employeeRepo.findAll();
        for (Employee emp : employees) {
            System.out.println(emp);
        }

        logger.info("Department list");
        List<Department> departments = departmentRepo.findAll();
        for (Department dep : departments) {
            System.out.println(dep);
        }

        logger.info("Get employee John");
        Employee employee = employeeRepo.findByName("John");

        logger.info("John's roles: ");
        Set<Role> rolesOfJohn = employee.getRoles();
        for (Role role : rolesOfJohn) {
            System.out.println(role);
        }
    }

}
