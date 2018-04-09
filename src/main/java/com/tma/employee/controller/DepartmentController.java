package com.tma.employee.controller;

import com.tma.employee.model.Department;
import com.tma.employee.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/department")
public class DepartmentController {

    private DepartmentService departmentService;

    @Autowired
    public void setDepartmentRepository(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String getAll(ModelMap model) {
        model.addAttribute("department", new Department());
        model.addAttribute("departments", departmentService.findAll());
        return "department";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable int id, ModelMap model) {
        model.addAttribute("department", departmentService.findOne(id));
        model.addAttribute("departments", departmentService.findAll());
        return "department";
    }

    @RequestMapping(value = "/addOrEdit", method = RequestMethod.POST)
    public String addOrUpdate(@ModelAttribute("department") Department department) {
        if (department.getId() == null) {
            departmentService.saveAndFlush(department);
        } else {
            Department oldDepartment = departmentService.findOne(department.getId());
            oldDepartment.setName(department.getName());
            departmentService.saveAndFlush(oldDepartment);
        }
        return "redirect:/department";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable int id) {
        departmentService.delete(id);
        return "redirect:/department";
    }

}
