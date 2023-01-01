package me.acomma.admin.core.business;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import me.acomma.admin.common.dto.action.AddActionDTO;
import me.acomma.admin.common.enums.MenuErrorCode;
import me.acomma.admin.common.exception.BusinessException;
import me.acomma.admin.core.service.ActionService;
import me.acomma.admin.core.service.MenuService;
import me.acomma.admin.data.po.ActionPO;
import me.acomma.admin.data.po.MenuPO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class ActionBusinessService {
    private final MenuService menuService;
    private final ActionService actionService;

    @Transactional(rollbackFor = Exception.class)
    public void addAction(AddActionDTO dto) {
        MenuPO menu = menuService.getById(dto.getMenuId());
        if (Objects.isNull(menu)) {
            throw new BusinessException(MenuErrorCode.MENU_NOT_EXIST);
        }

        ActionPO existing = actionService.getOne(Wrappers.<ActionPO>lambdaQuery()
                .eq(ActionPO::getMenuId, dto.getMenuId())
                .eq(ActionPO::getCode, dto.getCode()));
        if (Objects.nonNull(existing)) {
            throw new BusinessException(MenuErrorCode.MENU_ACTION_EXIST);
        }

        ActionPO po = new ActionPO();
        BeanUtils.copyProperties(dto, po);

        actionService.save(po);
    }
}
