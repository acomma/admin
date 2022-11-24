package me.acomma.admin.core.application;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import me.acomma.admin.common.dto.role.UpdateRoleActionDTO;
import me.acomma.admin.common.dto.role.UpdateRoleMenuDTO;
import me.acomma.admin.common.enums.RoleErrorCode;
import me.acomma.admin.common.exception.BusinessException;
import me.acomma.admin.core.service.MenuActionService;
import me.acomma.admin.core.service.MenuService;
import me.acomma.admin.core.service.RoleActionService;
import me.acomma.admin.core.service.RoleMenuService;
import me.acomma.admin.core.service.RoleService;
import me.acomma.admin.data.model.po.RoleActionPO;
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
    private final MenuActionService menuActionService;
    private final RoleActionService roleActionService;

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

    @Transactional(rollbackFor = Exception.class)
    public void updateRoleAction(UpdateRoleActionDTO dto) {
        RolePO existing = roleService.getById(dto.getRoleId());
        if (Objects.isNull(existing)) {
            throw new BusinessException(RoleErrorCode.ROLE_NOT_EXIST);
        }

        // 操作ID列表为空表示清空角色与操作的关系
        List<Long> validActionIds;
        if (CollectionUtils.isEmpty(dto.getActionIds())) {
            validActionIds = Collections.emptyList();
        } else {
            validActionIds = menuActionService.getValidActionIds(dto.getActionIds());
        }

        List<RoleActionPO> existingRoleActions = roleActionService.list(Wrappers.<RoleActionPO>lambdaQuery()
                .eq(RoleActionPO::getRoleId, dto.getRoleId()));
        List<Long> existingActionIds = existingRoleActions.stream().map(RoleActionPO::getActionId).toList();

        List<Long> needRemoveActionIds = existingActionIds.stream().filter(e -> !validActionIds.contains(e)).toList();
        if (!CollectionUtils.isEmpty(needRemoveActionIds)) {
            roleActionService.remove(Wrappers.<RoleActionPO>lambdaQuery()
                    .eq(RoleActionPO::getRoleId, dto.getRoleId())
                    .in(RoleActionPO::getActionId, needRemoveActionIds));
        }

        List<Long> needAddActionIds = validActionIds.stream().filter(e -> !existingActionIds.contains(e)).toList();
        if (!CollectionUtils.isEmpty(needAddActionIds)) {
            List<RoleActionPO> poList = needAddActionIds.stream()
                    .map(needAddActionId -> {
                        RoleActionPO po = new RoleActionPO();
                        po.setRoleId(dto.getRoleId());
                        po.setActionId(needAddActionId);
                        return po;
                    })
                    .toList();
            roleActionService.saveBatch(poList);
        }
    }
}
