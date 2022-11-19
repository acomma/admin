package me.acomma.admin.web.controller;

import lombok.RequiredArgsConstructor;
import me.acomma.admin.common.dto.menu.AddMenuDTO;
import me.acomma.admin.common.vo.menu.MenuVO;
import me.acomma.admin.core.service.MenuService;
import me.acomma.admin.data.model.po.MenuPO;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/menus")
@RequiredArgsConstructor
public class MenuController {
    private final MenuService menuService;

    @PostMapping
    public void addMenu(@Validated @RequestBody AddMenuDTO dto) {
        menuService.addMenu(dto);
    }

    @GetMapping("/{menuId}")
    public MenuVO getMenu(@PathVariable("menuId") Long menuId) {
        MenuPO po = menuService.getById(menuId);
        MenuVO vo = new MenuVO();
        BeanUtils.copyProperties(po, vo);
        return vo;
    }
}
