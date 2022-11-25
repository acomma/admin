package me.acomma.admin.data.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.io.Serial;

/**
 * 角色菜单关系
 */
@Getter
@Setter
@ToString(callSuper = true)
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "role_menu")
public class RoleMenuPO extends BasePO {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 关系ID
     */
    @TableId(value = "relation_id", type = IdType.AUTO)
    private Long relationId;

    /**
     * 角色ID
     */
    @TableField(value = "role_id")
    private Long roleId;

    /**
     * 菜单ID
     */
    @TableField(value = "menu_id")
    private Long menuId;
}