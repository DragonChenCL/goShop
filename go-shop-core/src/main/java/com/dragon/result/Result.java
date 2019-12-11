package com.dragon.result;

import lombok.Builder;

@Builder
public class Result<T> {
    private Integer code;

    private String message;

    private T data;
}
