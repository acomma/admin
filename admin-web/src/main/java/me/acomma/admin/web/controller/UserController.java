package me.acomma.admin.web.controller;

import lombok.RequiredArgsConstructor;
import me.acomma.admin.common.dto.user.AddUserDTO;
import me.acomma.admin.common.dto.user.UpdateUserRoleDTO;
import me.acomma.admin.core.application.UserAppService;
import me.acomma.admin.core.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
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
    private final PasswordEncoder passwordEncoder;

    @PostMapping
    @PreAuthorize("hasAuthority('user:add')")
    public void addUser(@Validated @RequestBody AddUserDTO dto) {
        dto.setPassword(passwordEncoder.encode(dto.getPassword()));
        userService.addUser(dto);
    }

    @PutMapping("/{userId}/roles")
    @PreAuthorize("hasAuthority('user:update')")
    public void updateUserRole(@PathVariable("userId") Long userId, @Validated @RequestBody UpdateUserRoleDTO dto) {
        dto.setUserId(userId);
        userAppService.updateUserRole(dto);
    }
}
