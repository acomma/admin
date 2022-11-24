package me.acomma.admin.common.dto.user;

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
public class UpdateUserRoleDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Long userId;

    private List<Long> roleIds;
}
