package com.dragon.result;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Result<T> {
    private Integer code;

    private String message;

    private T data;
}
