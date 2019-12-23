package com.dragon.entity.course;

import com.dragon.entity.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

/**
 * 优惠券
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "c_coupon_info")
@Entity
public class CouponInfo extends BaseEntity {
    private String name;

    private Integer amount;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private LocalDateTime expireDate; //过期时间

    @ManyToOne
    @JoinColumn(name = "course_id")
    @JsonIgnoreProperties("couponInfos")
    private CourseInfo courseInfo;
}
