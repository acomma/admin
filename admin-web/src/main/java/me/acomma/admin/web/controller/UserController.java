package me.acomma.admin.web.controller;

import lombok.RequiredArgsConstructor;
import me.acomma.admin.common.constant.MenuConstant;
import me.acomma.admin.common.dto.user.AddUserDTO;
import me.acomma.admin.common.dto.user.UpdateUserRoleDTO;
import me.acomma.admin.common.vo.menu.MenuVO;
import me.acomma.admin.core.business.UserBusinessService;
import me.acomma.admin.core.service.MenuService;
import me.acomma.admin.core.service.UserService;
import me.acomma.admin.data.po.MenuPO;
import me.acomma.admin.web.security.SecurityUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final UserBusinessService userBusinessService;
    private final PasswordEncoder passwordEncoder;
    private final MenuService menuService;

    @PostMapping
    @PreAuthorize("hasAuthority('user:add')")
    public void addUser(@Validated @RequestBody AddUserDTO dto) {
        dto.setPassword(passwordEncoder.encode(dto.getPassword()));
        userService.addUser(dto);
    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable("userId") Long userId) {
        if (SecurityUtils.isSystemAdministratorUser(userId)) {
            throw new AccessDeniedException("?????????????????????????????????");
        }
    }

    @PutMapping("/{userId}/roles")
    @PreAuthorize("hasAuthority('user:update')")
    public void updateUserRole(@PathVariable("userId") Long userId, @Validated @RequestBody UpdateUserRoleDTO dto) {
        if (SecurityUtils.isSystemAdministratorUser(userId) && !SecurityUtils.isSystemAdministratorUser()) {
            throw new AccessDeniedException("??????????????????????????????????????????");
        }

        dto.setUserId(userId);
        userBusinessService.updateUserRole(dto);
    }

    @GetMapping("/{userId}/menus")
    public List<MenuVO> getUserMenu(@PathVariable("userId") Long userId) {
        List<MenuPO> pos = menuService.listByUserId(userId);
        return buildMenuTree(pos);
    }

    /**
     * ???????????????????????? <a href="https://mp.weixin.qq.com/s/krp-QdvnKYOYqEgoadI1Zw">Java ???????????????????????????</a>
     */
    private List<MenuVO> buildMenuTree(List<MenuPO> pos) {
        // ??????????????????
        if (CollectionUtils.isEmpty(pos)) {
            return Collections.emptyList();
        }

        // ??????????????????
        int size = pos.size();
        List<MenuVO> roots = new ArrayList<>(size);
        Map<Long, MenuVO> menuMap = new HashMap<>(size);
        for (MenuPO po : pos) {
            // ??????????????????
            Long id = po.getId();
            MenuVO menu = menuMap.get(id);
            if (Objects.isNull(menu)) {
                menu = new MenuVO();
                menu.setChildren(new ArrayList<>());
                menuMap.put(id, menu);
            }
            BeanUtils.copyProperties(po, menu, "children");

            // ?????????????????????
            Long parentId = po.getParentId();
            if (!Objects.equals(parentId, MenuConstant.ROOT_ID)) {
                // ?????????????????????
                MenuVO parent = menuMap.get(parentId);
                if (Objects.isNull(parent)) {
                    parent = new MenuVO();
                    parent.setId(parentId);
                    parent.setChildren(new ArrayList<>());
                    menuMap.put(parentId, parent);
                }

                // ?????????????????????
                parent.getChildren().add(menu);
            } else {
                // ?????????????????????
                roots.add(menu);
            }
        }

        // ?????????????????????
        return roots;
    }
}
