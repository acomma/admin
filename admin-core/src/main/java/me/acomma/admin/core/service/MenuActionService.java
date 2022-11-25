package me.acomma.admin.core.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import me.acomma.admin.data.mapper.MenuActionMapper;
import me.acomma.admin.data.po.MenuActionPO;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;

@Service
public class MenuActionService extends ServiceImpl<MenuActionMapper, MenuActionPO> {
    public List<Long> getValidActionIds(List<Long> actionIds) {
        if (CollectionUtils.isEmpty(actionIds)) {
            return Collections.emptyList();
        }
        List<MenuActionPO> actions = super.listByIds(actionIds);
        return actions.stream().map(MenuActionPO::getActionId).toList();
    }

    public List<String> getMenuActionCodeByUserId(Long userId) {
        return this.baseMapper.getMenuActionCodeByUserId(userId);
    }
}
