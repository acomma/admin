package me.acomma.admin.web.controller;

import lombok.RequiredArgsConstructor;
import me.acomma.admin.common.dto.action.AddActionDTO;
import me.acomma.admin.core.business.ActionBusinessService;
import me.acomma.admin.web.security.SecurityUtils;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/actions")
@RequiredArgsConstructor
public class ActionController {
    private final ActionBusinessService actionBusinessService;

    @PostMapping
    @PreAuthorize("hasAuthority('action:add')")
    public void addMenuAction(@Validated @RequestBody AddActionDTO dto) {
        if (!SecurityUtils.isSystemAdministratorUser()) {
            throw new AccessDeniedException("不是系统管理员");
        }

        actionBusinessService.addAction(dto);
    }
}
