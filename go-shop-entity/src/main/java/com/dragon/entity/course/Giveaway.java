package com.dragon.entity.course;

import com.dragon.entity.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

/**
 * 赠品
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "c_giveaway")
public class Giveaway extends BaseEntity {
    private String name; //教材名称

    private String coverPicture; //教材封面

    @ManyToOne
    @JoinColumn(name = "course_id")
    @JsonIgnoreProperties("giveaways")
    private CourseInfo courseInfo;
}
