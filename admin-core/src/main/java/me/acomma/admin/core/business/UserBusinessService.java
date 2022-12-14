package me.acomma.admin.core.business;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import me.acomma.admin.common.dto.user.UpdateUserRoleDTO;
import me.acomma.admin.common.enums.UserErrorCode;
import me.acomma.admin.common.exception.BusinessException;
import me.acomma.admin.core.service.RoleService;
import me.acomma.admin.core.service.UserRoleService;
import me.acomma.admin.core.service.UserService;
import me.acomma.admin.data.po.UserPO;
import me.acomma.admin.data.po.UserRolePO;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class UserBusinessService {
    private final UserService userService;
    private final RoleService roleService;
    private final UserRoleService userRoleService;

    @Transactional(rollbackFor = Exception.class)
    public void updateUserRole(UpdateUserRoleDTO dto) {
        UserPO existing = userService.getById(dto.getUserId());
        if (Objects.isNull(existing)) {
            throw new BusinessException(UserErrorCode.USER_NOT_EXIST);
        }

        // 角色ID列表为空表示清空用户与角色的关系
        List<Long> validRoleIds;
        if (CollectionUtils.isEmpty(dto.getRoleIds())) {
            validRoleIds = Collections.emptyList();
        } else {
            validRoleIds = roleService.getValidRoleIds(dto.getRoleIds());
        }

        List<UserRolePO> existingUserRoles = userRoleService.list(Wrappers.<UserRolePO>lambdaQuery()
                .eq(UserRolePO::getUserId, dto.getUserId()));
        List<Long> existingRoleIds = existingUserRoles.stream().map(UserRolePO::getRoleId).toList();

        List<Long> needRemoveRoleIds = existingRoleIds.stream().filter(e -> !validRoleIds.contains(e)).toList();
        if (!CollectionUtils.isEmpty(needRemoveRoleIds)) {
            userRoleService.remove(Wrappers.<UserRolePO>lambdaQuery()
                    .eq(UserRolePO::getUserId, dto.getUserId())
                    .in(UserRolePO::getRoleId, needRemoveRoleIds));
        }

        List<Long> needAddRoleIds = validRoleIds.stream().filter(e -> !existingRoleIds.contains(e)).toList();
        if (!CollectionUtils.isEmpty(needAddRoleIds)) {
            List<UserRolePO> poList = needAddRoleIds.stream().map(needAddRoleId -> {
                        UserRolePO po = new UserRolePO();
                        po.setUserId(dto.getUserId());
                        po.setRoleId(needAddRoleId);
                        return po;
                    })
                    .toList();
            userRoleService.saveBatch(poList);
        }
    }
}
