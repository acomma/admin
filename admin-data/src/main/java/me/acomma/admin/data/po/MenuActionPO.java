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
 * 菜单操作
 */
@Getter
@Setter
@ToString(callSuper = true)
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "menu_action")
public class MenuActionPO extends BasePO {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 操作ID
     */
    @TableId(value = "action_id", type = IdType.AUTO)
    private Long actionId;

    /**
     * 菜单ID
     */
    @TableField(value = "menu_id")
    private Long menuId;

    /**
     * 操作编码
     */
    @TableField(value = "code")
    private String code;

    /**
     * 操作名称
     */
    @TableField(value = "`name`")
    private String name;
}