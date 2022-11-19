package me.acomma.admin.common.vo;

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
@NoArgsConstructor
@AllArgsConstructor
public class PageVO<T> implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private long pageNo;

    private long pageSize;

    private long total;

    private List<T> records;
}
