package me.acomma.admin.data.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import me.acomma.admin.data.po.ActionPO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ActionMapper extends BaseMapper<ActionPO> {
    @Select("""
            SELECT `code` FROM `action`
            WHERE `id` IN (
                SELECT `action_id` FROM `role_action`
                WHERE `role_id` IN (
                    SELECT `role_id` FROM `user_role`
                    WHERE `user_id` = #{userId,jdbcType=BIGINT}
                )
            )
            """)
    List<String> getCodeByUserId(@Param("userId") Long userId);
}
