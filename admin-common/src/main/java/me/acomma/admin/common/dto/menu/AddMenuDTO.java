package me.acomma.admin.common.dto.menu;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddMenuDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 上级ID
     */
    @NotNull(message = "{menu_parent_id_not_null}")
    @Min(value = 0, message = "{menu_parent_id_min}")
    private Long parentId;

    /**
     * 菜单名称
     */
    @NotBlank(message = "菜单名称不能为空")
    @Size(max = 32, message = "菜单名称长度不能超过{max}")
    private String name;

    /**
     * 菜单路径
     */
    @Size(max = 255, message = "菜单路径长度不能超过{max}")
    private String path;
}
