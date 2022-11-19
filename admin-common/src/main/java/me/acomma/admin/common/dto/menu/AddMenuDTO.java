package me.acomma.admin.common.dto.menu;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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
    @NotNull(message = "上级ID不能为空")
    @Min(value = 0, message = "上级ID不能小于0")
    private Long parentId;

    /**
     * 菜单名称
     */
    @NotBlank(message = "菜单名称不能为空")
    @Size(max = 32, message = "菜单名称长度不能超过32")
    private String name;

    /**
     * 菜单路径
     */
    @Size(max = 255, message = "菜单路径长度不能超过255")
    private String path;
}
