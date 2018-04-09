package com.tma.employee.controller;

import com.tma.employee.model.Employee;
import com.tma.employee.service.DepartmentService;
import com.tma.employee.service.EmployeeService;
import com.tma.employee.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

    private EmployeeService employeeService;
    private DepartmentService departmentService;
    private RoleService roleService;

    @Autowired
    public EmployeeController(EmployeeService employeeService,
                              DepartmentService departmentService,
                              RoleService roleService) {
        this.employeeService = employeeService;
        this.departmentService = departmentService;
        this.roleService = roleService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String getAll(ModelMap model) {
        model.addAttribute("employee", new Employee());
        model.addAttribute("employees", employeeService.findAll());
        model.addAttribute("roles", roleService.findAll());
        model.addAttribute("departments", departmentService.findAll());

        return "employee";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable int id, ModelMap model) {
        model.addAttribute("employee", employeeService.findOne(id));
        model.addAttribute("employees", employeeService.findAll());
        model.addAttribute("roles", roleService.findAll());
        model.addAttribute("departments", departmentService.findAll());

        return "employee";
    }

    @RequestMapping(value = "/addOrEdit", method = RequestMethod.POST)
    public String add(@ModelAttribute("employee") Employee newEmployee) {
        if (newEmployee.getId() == null) {
            departmentService.addEmployeeToDepartment(newEmployee);
            roleService.setEmployeeToRole(newEmployee);
            employeeService.saveAndFlush(newEmployee);
        } else {
            Employee oldEmployee = employeeService.findOne(newEmployee.getId());

            departmentService.removeEmployeeFromDepartment(oldEmployee);
            roleService.removeEmployeeFromRoles(oldEmployee);

            departmentService.addEmployeeToDepartment(newEmployee);
            roleService.setEmployeeToRole(newEmployee);

            oldEmployee.setName(newEmployee.getName());
            oldEmployee.getRoles().clear();
            oldEmployee.setRoles(newEmployee.getRoles());
            oldEmployee.setDepartment(newEmployee.getDepartment());
            employeeService.saveAndFlush(oldEmployee);
        }
        return "redirect:/employee";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable int id) {
        employeeService.delete(id);
        return "redirect:/employee";
    }

}
