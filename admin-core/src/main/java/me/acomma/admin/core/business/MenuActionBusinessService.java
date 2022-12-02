package me.acomma.admin.core.business;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import me.acomma.admin.common.dto.menu.AddMenuActionDTO;
import me.acomma.admin.common.enums.MenuErrorCode;
import me.acomma.admin.common.exception.BusinessException;
import me.acomma.admin.core.service.MenuActionService;
import me.acomma.admin.core.service.MenuService;
import me.acomma.admin.data.po.MenuActionPO;
import me.acomma.admin.data.po.MenuPO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class MenuActionBusinessService {
    private final MenuService menuService;
    private final MenuActionService menuActionService;

    @Transactional(rollbackFor = Exception.class)
    public void addMenuAction(AddMenuActionDTO dto) {
        MenuPO menu = menuService.getById(dto.getMenuId());
        if (Objects.isNull(menu)) {
            throw new BusinessException(MenuErrorCode.MENU_NOT_EXIST);
        }

        MenuActionPO existing = menuActionService.getOne(Wrappers.<MenuActionPO>lambdaQuery()
                .eq(MenuActionPO::getMenuId, dto.getMenuId())
                .eq(MenuActionPO::getCode, dto.getCode()));
        if (Objects.nonNull(existing)) {
            throw new BusinessException(MenuErrorCode.MENU_ACTION_EXIST);
        }

        MenuActionPO po = new MenuActionPO();
        BeanUtils.copyProperties(dto, po);

        menuActionService.save(po);
    }
}
