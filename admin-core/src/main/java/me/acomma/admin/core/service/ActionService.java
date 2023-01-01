package me.acomma.admin.core.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import me.acomma.admin.data.mapper.ActionMapper;
import me.acomma.admin.data.po.ActionPO;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;

@Service
public class ActionService extends ServiceImpl<ActionMapper, ActionPO> {
    public List<Long> getValidActionIds(List<Long> actionIds) {
        if (CollectionUtils.isEmpty(actionIds)) {
            return Collections.emptyList();
        }
        List<ActionPO> actions = super.listByIds(actionIds);
        return actions.stream().map(ActionPO::getId).toList();
    }

    public List<String> getActionCodeByUserId(Long userId) {
        return this.baseMapper.getCodeByUserId(userId);
    }
}
