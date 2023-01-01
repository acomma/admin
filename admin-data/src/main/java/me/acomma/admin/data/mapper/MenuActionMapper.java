package me.acomma.admin.data.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import me.acomma.admin.data.po.MenuActionPO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface MenuActionMapper extends BaseMapper<MenuActionPO> {
    @Select("""
            SELECT `code` FROM `menu_action`
            WHERE `id` IN (
                SELECT `action_id` FROM `role_action`
                WHERE `role_id` IN (
                    SELECT `role_id` FROM `user_role`
                    WHERE `user_id` = #{userId,jdbcType=BIGINT}
                )
            )
            """)
    List<String> getMenuActionCodeByUserId(@Param("userId") Long userId);
}
