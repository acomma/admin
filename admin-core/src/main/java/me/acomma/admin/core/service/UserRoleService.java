package me.acomma.admin.core.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import me.acomma.admin.data.mapper.UserRoleMapper;
import me.acomma.admin.data.model.po.UserRolePO;
import org.springframework.stereotype.Service;

@Service
public class UserRoleService extends ServiceImpl<UserRoleMapper, UserRolePO> {
}
