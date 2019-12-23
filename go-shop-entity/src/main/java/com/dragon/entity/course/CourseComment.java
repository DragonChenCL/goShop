package com.dragon.entity.course;

import com.dragon.entity.base.BaseEntity;
import com.dragon.entity.user.UserEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "c_course_comment")
public class CourseComment extends BaseEntity {
    private String content;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity commentUser;

    @ManyToOne
    @JoinColumn(name = "course_id")
    @JsonIgnoreProperties("comments")
    private CourseInfo courseInfo;
}
