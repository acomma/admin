package me.acomma.admin.web.controller;

import lombok.RequiredArgsConstructor;
import me.acomma.admin.common.dto.menu.AddMenuActionDTO;
import me.acomma.admin.core.application.MenuActionAppService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/menus/{menuId}")
@RequiredArgsConstructor
public class MenuActionController {
    private final MenuActionAppService menuActionAppService;

    @PostMapping("/actions")
    @PreAuthorize("hasAuthority('menu:action:add')")
    public void addMenuAction(@PathVariable("menuId") Long menuId, @Validated @RequestBody AddMenuActionDTO dto) {
        dto.setMenuId(menuId);
        menuActionAppService.addMenuAction(dto);
    }
}
