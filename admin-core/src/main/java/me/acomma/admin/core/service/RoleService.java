package me.acomma.admin.core.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import me.acomma.admin.common.dto.role.AddRoleDTO;
import me.acomma.admin.common.enums.RoleErrorCode;
import me.acomma.admin.common.exception.BusinessException;
import me.acomma.admin.data.mapper.RoleMapper;
import me.acomma.admin.data.po.RolePO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service
public class RoleService extends ServiceImpl<RoleMapper, RolePO> {
    @Transactional(rollbackFor = Exception.class)
    public void addRole(AddRoleDTO dto) {
        RolePO existing = super.getOne(Wrappers.<RolePO>lambdaQuery().eq(RolePO::getName, dto.getName()));
        if (Objects.nonNull(existing)) {
            throw new BusinessException(RoleErrorCode.ROLE_EXIST);
        }

        RolePO po = new RolePO();
        BeanUtils.copyProperties(dto, po);

        super.save(po);
    }

    public List<Long> getValidRoleIds(List<Long> roleIds) {
        if (Objects.isNull(roleIds)) {
            return Collections.emptyList();
        }
        List<RolePO> roles = super.listByIds(roleIds);
        return roles.stream().map(RolePO::getId).toList();
    }
}
