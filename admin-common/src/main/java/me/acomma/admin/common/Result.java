package me.acomma.admin.common;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@ToString
@NoArgsConstructor
@SuperBuilder
public class Result<T> implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Integer code;

    private String message;

    private T data;
}
