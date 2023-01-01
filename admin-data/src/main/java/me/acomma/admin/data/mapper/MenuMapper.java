package me.acomma.admin.data.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import me.acomma.admin.data.po.MenuPO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface MenuMapper extends BaseMapper<MenuPO> {
    @Select("""
            SELECT * FROM `menu`
            WHERE `id` IN (
                SELECT `menu_id` FROM `role_menu`
                WHERE `role_id` IN (
                    SELECT `role_id` FROM `user_role`
                    WHERE `user_id` = #{userId,jdbcType=BIGINT}
                )
            )
            """)
    List<MenuPO> listByUserId(@Param("userId") Long userId);
}
