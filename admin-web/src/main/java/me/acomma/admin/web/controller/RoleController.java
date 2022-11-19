package me.acomma.admin.web.controller;

import lombok.RequiredArgsConstructor;
import me.acomma.admin.common.dto.role.AddRoleDTO;
import me.acomma.admin.common.dto.role.AssignMenuDTO;
import me.acomma.admin.core.application.RoleAppService;
import me.acomma.admin.core.service.RoleService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/roles")
@RequiredArgsConstructor
public class RoleController {
    private final RoleService roleService;
    private final RoleAppService roleAppService;

    @PostMapping
    public void addRole(@Validated @RequestBody AddRoleDTO dto) {
        roleService.addRole(dto);
    }

    @PutMapping("/menu")
    public void assignMenu(@Validated @RequestBody AssignMenuDTO dto) {
        roleAppService.assignMenu(dto);
    }
}
