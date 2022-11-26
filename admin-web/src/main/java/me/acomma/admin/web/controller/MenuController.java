package me.acomma.admin.web.controller;

import lombok.RequiredArgsConstructor;
import me.acomma.admin.common.dto.menu.AddMenuDTO;
import me.acomma.admin.common.enums.MenuErrorCode;
import me.acomma.admin.common.exception.BusinessException;
import me.acomma.admin.common.vo.menu.MenuVO;
import me.acomma.admin.core.service.MenuService;
import me.acomma.admin.data.po.MenuPO;
import me.acomma.admin.web.security.SecurityUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequestMapping("/menus")
@RequiredArgsConstructor
public class MenuController {
    private final MenuService menuService;

    @PostMapping
    @PreAuthorize("hasAuthority('menu:add')")
    public void addMenu(@Validated @RequestBody AddMenuDTO dto) {
        if (!SecurityUtils.isSystemAdministratorUser()) {
            throw new AccessDeniedException("不是系统管理员");
        }

        menuService.addMenu(dto);
    }

    @GetMapping("/{menuId}")
    @PreAuthorize("hasAuthority('menu:view')")
    public MenuVO getMenu(@PathVariable("menuId") Long menuId) {
        MenuPO po = menuService.getById(menuId);
        if (Objects.isNull(po)) {
            throw new BusinessException(MenuErrorCode.MENU_NOT_EXIST);
        }
        MenuVO vo = new MenuVO();
        BeanUtils.copyProperties(po, vo);
        return vo;
    }
}
