package me.acomma.admin.common.dto.role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateRoleMenuDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Long roleId;

    private List<Long> menuIds;
}
