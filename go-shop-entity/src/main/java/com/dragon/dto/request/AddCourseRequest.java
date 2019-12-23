package com.dragon.dto.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

@Data
public class AddCourseRequest {

    private String courseName;

    private String videoUrl;

    private Boolean free;

    private BigDecimal price;

    private String grade; //年级

    private String coverPicture; //封面图
}
