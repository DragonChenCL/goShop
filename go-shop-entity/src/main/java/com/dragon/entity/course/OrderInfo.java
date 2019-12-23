package com.dragon.entity.course;

import com.dragon.entity.base.BaseEntity;
import com.dragon.entity.user.UserEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * 订单
 */

@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "c_order_info")
@Entity
public class OrderInfo extends BaseEntity {
    private String number;

    private BigDecimal price;

    private String status;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnoreProperties("orderInfos")
    private UserEntity userEntity;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private CourseInfo courseInfo;

    @PrePersist
    private void prePersist(){
        if (StringUtils.isNotBlank(number)){
            this.number = RandomStringUtils.randomAlphabetic(10);
        }
    }
}
