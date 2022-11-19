package me.acomma.admin.common.dto.role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AssignMenuDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @NotNull(message = "角色ID不能为空")
    private Long roleId;

    @NotNull(message = "菜单ID列表不能为空")
    @Size(min = 1, message = "至少需要一个菜单ID")
    private List<Long> menuIds;
}
