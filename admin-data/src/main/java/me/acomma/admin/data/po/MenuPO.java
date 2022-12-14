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
 * 菜单
 */
@Getter
@Setter
@ToString(callSuper = true)
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "menu")
public class MenuPO extends BasePO {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 上级ID
     */
    @TableField(value = "parent_id")
    private Long parentId;

    /**
     * 菜单名称
     */
    @TableField(value = "`name`")
    private String name;

    /**
     * 菜单路径
     */
    @TableField(value = "`path`")
    private String path;
}