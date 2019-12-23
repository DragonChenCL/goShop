package com.dragon.dto.request;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AddCouponInfoRequest {
    private String name;

    private Integer amount;

    private LocalDateTime expireDate;

    private String courseId;
}
