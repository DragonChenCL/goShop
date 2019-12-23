package com.dragon.dto.base;

import lombok.Data;

@Data
public class BaseRequest {
    private Integer pageNum = 0;

    private Integer pageSize = 10;
}
