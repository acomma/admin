package me.acomma.admin.core.application;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import me.acomma.admin.common.dto.role.UpdateRoleMenuDTO;
import me.acomma.admin.common.enums.RoleErrorCode;
import me.acomma.admin.common.exception.BusinessException;
import me.acomma.admin.core.service.MenuService;
import me.acomma.admin.core.service.RoleMenuService;
import me.acomma.admin.core.service.RoleService;
import me.acomma.admin.data.model.po.RoleMenuPO;
import me.acomma.admin.data.model.po.RolePO;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class RoleAppService {
    private final RoleService roleService;
    private final MenuService menuService;
    private final RoleMenuService roleMenuService;

    @Transactional(rollbackFor = Exception.class)
    public void updateRoleMenu(UpdateRoleMenuDTO dto) {
        RolePO existing = roleService.getById(dto.getRoleId());
        if (Objects.isNull(existing)) {
            throw new BusinessException(RoleErrorCode.ROLE_NOT_EXIST);
        }

        // 菜单ID列表为空表示清空角色与菜单的关系
        List<Long> validMenuIds;
        if (CollectionUtils.isEmpty(dto.getMenuIds())) {
            validMenuIds = Collections.emptyList();
        } else {
            validMenuIds = menuService.getValidMenuIds(dto.getMenuIds());
        }

        List<RoleMenuPO> existingRoleMenus = roleMenuService.list(Wrappers.<RoleMenuPO>lambdaQuery()
                .eq(RoleMenuPO::getRoleId, dto.getRoleId()));
        List<Long> existingMenuIds = existingRoleMenus.stream().map(RoleMenuPO::getMenuId).toList();

        List<Long> needRemoveMenuIds = existingMenuIds.stream().filter(e -> !validMenuIds.contains(e)).toList();
        if (!CollectionUtils.isEmpty(needRemoveMenuIds)) {
            roleMenuService.remove(Wrappers.<RoleMenuPO>lambdaQuery()
                    .eq(RoleMenuPO::getRoleId, dto.getRoleId())
                    .in(RoleMenuPO::getMenuId, needRemoveMenuIds));
        }

        List<Long> needAddMenuIds = validMenuIds.stream().filter(e -> !existingMenuIds.contains(e)).toList();
        if (!CollectionUtils.isEmpty(needAddMenuIds)) {
            List<RoleMenuPO> poList = needAddMenuIds.stream()
                    .map(needAddMenuId -> {
                        RoleMenuPO po = new RoleMenuPO();
                        po.setRoleId(dto.getRoleId());
                        po.setMenuId(needAddMenuId);
                        return po;
                    })
                    .toList();
            roleMenuService.saveBatch(poList);
        }
    }
}
