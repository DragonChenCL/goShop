package com.dragon.dto.request;

import com.dragon.dto.base.BaseRequest;
import com.dragon.entity.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class SearchCourseRequest extends BaseRequest {
    private String grade;

    private String userId; //查看我的收藏

    private Boolean available;
}
