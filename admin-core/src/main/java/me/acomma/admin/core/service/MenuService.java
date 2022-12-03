package me.acomma.admin.core.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import me.acomma.admin.common.constant.MenuConstant;
import me.acomma.admin.common.dto.menu.AddMenuDTO;
import me.acomma.admin.common.enums.MenuErrorCode;
import me.acomma.admin.common.exception.BusinessException;
import me.acomma.admin.data.mapper.MenuMapper;
import me.acomma.admin.data.po.MenuPO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service
public class MenuService extends ServiceImpl<MenuMapper, MenuPO> {
    @Transactional(rollbackFor = Exception.class)
    public void addMenu(AddMenuDTO dto) {
        if (!Objects.equals(dto.getParentId(), MenuConstant.ROOT_ID)) {
            MenuPO existing = super.getById(dto.getParentId());
            if (Objects.isNull(existing)) {
                throw new BusinessException(MenuErrorCode.PARENT_MENU_NOT_EXIST);
            }
        }

        MenuPO existing = super.getOne(Wrappers.<MenuPO>lambdaQuery()
                .eq(MenuPO::getParentId, dto.getParentId())
                .eq(MenuPO::getName, dto.getName()));
        if (Objects.nonNull(existing)) {
            throw new BusinessException(MenuErrorCode.MENU_EXIST);
        }

        MenuPO po = new MenuPO();
        BeanUtils.copyProperties(dto, po);

        super.save(po);
    }

    public List<Long> getValidMenuIds(List<Long> menuIds) {
        if (CollectionUtils.isEmpty(menuIds)) {
            return Collections.emptyList();
        }
        List<MenuPO> menus = super.listByIds(menuIds);
        return menus.stream().map(MenuPO::getMenuId).toList();
    }

    public List<MenuPO> listByUserId(Long userId) {
        return this.baseMapper.listByUserId(userId);
    }
}
