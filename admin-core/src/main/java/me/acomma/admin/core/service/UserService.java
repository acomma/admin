package me.acomma.admin.core.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import me.acomma.admin.common.dto.user.AddUserDTO;
import me.acomma.admin.common.enums.UserErrorCode;
import me.acomma.admin.common.exception.BusinessException;
import me.acomma.admin.data.mapper.UserMapper;
import me.acomma.admin.data.po.UserPO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
public class UserService extends ServiceImpl<UserMapper, UserPO> {
    @Transactional(rollbackFor = Exception.class)
    public void addUser(AddUserDTO dto) {
        UserPO existing = super.getOne(Wrappers.<UserPO>lambdaQuery().eq(UserPO::getUsername, dto.getUsername()));
        if (Objects.nonNull(existing)) {
            throw new BusinessException(UserErrorCode.USER_EXIST);
        }

        UserPO po = new UserPO();
        BeanUtils.copyProperties(dto, po);

        super.save(po);
    }

    public UserPO getByUsername(String username) {
        return super.getOne(Wrappers.<UserPO>lambdaQuery().eq(UserPO::getUsername, username));
    }
}
