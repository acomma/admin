package me.acomma.admin.web.controller;

import lombok.RequiredArgsConstructor;
import me.acomma.admin.common.dto.role.AddRoleDTO;
import me.acomma.admin.common.dto.role.UpdateRoleActionDTO;
import me.acomma.admin.common.dto.role.UpdateRoleMenuDTO;
import me.acomma.admin.core.business.RoleBusinessService;
import me.acomma.admin.core.service.RoleService;
import me.acomma.admin.web.security.SecurityUtils;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
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
    private final RoleBusinessService roleBusinessService;

    @PostMapping
    @PreAuthorize("hasAuthority('role:add')")
    public void addRole(@Validated @RequestBody AddRoleDTO dto) {
        roleService.addRole(dto);
    }

    @DeleteMapping("/{roleId}")
    public void deleteRole(@PathVariable("roleId") Long roleId) {
        if (SecurityUtils.isSystemAdministratorRole(roleId)) {
            throw new AccessDeniedException("不能删除系统管理员角色");
        }
    }

    @PutMapping("/{roleId}/menus")
    @PreAuthorize("hasAuthority('role:update')")
    public void updateRoleMenu(@PathVariable("roleId") Long roleId, @Validated @RequestBody UpdateRoleMenuDTO dto) {
        if (SecurityUtils.isSystemAdministratorRole(roleId) && !SecurityUtils.isSystemAdministratorUser()) {
            throw new AccessDeniedException("没有编辑系统管理员角色的权限");
        }

        dto.setRoleId(roleId);
        roleBusinessService.updateRoleMenu(dto);
    }

    @PutMapping("/{roleId}/actions")
    @PreAuthorize("hasAuthority('role:update')")
    public void updateRoleAction(@PathVariable("roleId") Long roleId, @Validated @RequestBody UpdateRoleActionDTO dto) {
        if (SecurityUtils.isSystemAdministratorRole(roleId) && !SecurityUtils.isSystemAdministratorUser()) {
            throw new AccessDeniedException("没有编辑系统管理员角色的权限");
        }

        dto.setRoleId(roleId);
        roleBusinessService.updateRoleAction(dto);
    }
}
