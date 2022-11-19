package me.acomma.admin.web.controller;

import lombok.RequiredArgsConstructor;
import me.acomma.admin.common.dto.user.AddUserDTO;
import me.acomma.admin.common.dto.user.AssignRoleDTO;
import me.acomma.admin.core.application.UserAppService;
import me.acomma.admin.core.service.UserService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final UserAppService userAppService;

    @PostMapping
    public void addUser(@Validated @RequestBody AddUserDTO dto) {
        userService.addUser(dto);
    }

    @PutMapping("/role")
    public void assignRole(@Validated @RequestBody AssignRoleDTO dto) {
        userAppService.assignRole(dto);
    }
}
