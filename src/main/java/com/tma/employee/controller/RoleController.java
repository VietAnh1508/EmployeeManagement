package com.tma.employee.controller;

import com.tma.employee.model.Role;
import com.tma.employee.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/role")
public class RoleController {

    private RoleService roleService;

    @Autowired
    public void setRoleRepository(RoleService roleService) {
        this.roleService = roleService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String getAll(ModelMap model) {
        model.addAttribute("role", new Role());
        model.addAttribute("roles", roleService.findAll());
        return "role";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable int id, ModelMap model) {
        model.addAttribute("role", roleService.findOne(id));
        model.addAttribute("roles", roleService.findAll());
        return "role";
    }

    @RequestMapping(value = "/addOrEdit", method = RequestMethod.POST)
    public String addOrUpdate(@ModelAttribute("role") Role role) {
        if (role.getId() == null) {
            roleService.save(role);
        } else {
            Role oldRole = roleService.findOne(role.getId());
            oldRole.setName(role.getName());
            roleService.save(oldRole);
        }
        return "redirect:/role";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable int id) {
        roleService.delete(id);
        return "redirect:/role";
    }

}
