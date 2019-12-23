package com.dragon.entity.user;

import com.dragon.entity.base.BaseEntity;
import com.dragon.entity.course.CourseInfo;
import com.dragon.entity.course.ExerciseInfo;
import com.dragon.entity.course.OrderInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "c_sys_user")
public class UserEntity extends BaseEntity {

    @Column(columnDefinition = "VARCHAR(20) COMMENT '用户名'",nullable = false)
    private String userName;

    @Column(columnDefinition = "VARCHAR(20) COMMENT '盐值'",nullable = false)
    private String salt;

    @Column(columnDefinition = "VARCHAR(32) COMMENT '密码'",nullable = false)
    private String password;

    private String avatar;

    private String account;

    private String phone;

    private String school; //所属学校

    @ManyToMany(mappedBy = "userEntities")
    @JsonIgnoreProperties("userEntities")
    private List<Role> roles;

    @ManyToMany(mappedBy = "favoriteUsers")
    @JsonIgnoreProperties("favoriteUsers")
    private List<CourseInfo> favoriteCourses;

    @ManyToMany(mappedBy = "downloadUsers")
    @JsonIgnoreProperties("downloadUsers")
    private List<CourseInfo> downloadCourses;

    @OneToMany(mappedBy = "questioner")
    @JsonIgnoreProperties("questioner")
    private List<ExerciseInfo> exercises;  //出了多少习题

    @ManyToMany(mappedBy = "userEntities")
    @JsonIgnoreProperties("userEntities")
    private List<ExerciseInfo> favoriteExerciseInfos;  //收藏的题目

    @OneToMany(mappedBy = "userEntity")
    @JsonIgnoreProperties("userEntity")
    private List<OrderInfo> orderInfos;
}
