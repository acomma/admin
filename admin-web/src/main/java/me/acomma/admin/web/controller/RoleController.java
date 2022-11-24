package me.acomma.admin.web.controller;

import lombok.RequiredArgsConstructor;
import me.acomma.admin.common.dto.role.AddRoleDTO;
import me.acomma.admin.common.dto.role.UpdateRoleActionDTO;
import me.acomma.admin.common.dto.role.UpdateRoleMenuDTO;
import me.acomma.admin.core.application.RoleAppService;
import me.acomma.admin.core.service.RoleService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
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

    @PutMapping("/{roleId}/menus")
    public void updateRoleMenu(@PathVariable("roleId") Long roleId, @Validated @RequestBody UpdateRoleMenuDTO dto) {
        dto.setRoleId(roleId);
        roleAppService.updateRoleMenu(dto);
    }

    @PutMapping("/{roleId}/actions")
    public void updateRoleAction(@PathVariable("roleId") Long roleId, @Validated @RequestBody UpdateRoleActionDTO dto) {
        dto.setRoleId(roleId);
        roleAppService.updateRoleAction(dto);
    }
}
