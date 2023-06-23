package me.acomma.admin.common.dto.action;

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
public class AddActionDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 菜单ID
     */
    @NotNull(message = "菜单ID不能为空")
    private Long menuId;

    /**
     * 操作编码
     */
    @NotBlank(message = "操作编码不能为空")
    @Size(max = 128, message = "操作编码长度不能超过128")
    private String code;

    /**
     * 操作名称
     */
    @NotBlank(message = "操作名称不能为空")
    @Size(max = 128, message = "操作名称长度不能超过128")
    private String name;
}
