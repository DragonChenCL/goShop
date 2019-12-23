package com.dragon.entity.course;

import com.dragon.entity.base.BaseEntity;
import com.dragon.entity.user.UserEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "c_sys_course_info")
public class CourseInfo extends BaseEntity {
    private String courseName;

    private String videoUrl;

    @Column(columnDefinition = "tinyint(1)")
    private Boolean free;

    private BigDecimal price;

    private String grade; //年级

    private String coverPicture; //封面图

    @ManyToMany
    @JoinTable(name = "z_favorite_course",joinColumns = {@JoinColumn(name = "course_id")},inverseJoinColumns = {@JoinColumn(name = "user_id")})
    @JsonIgnoreProperties("favoriteCourses")
    private List<UserEntity> favoriteUsers;

    @ManyToMany
    @JoinTable(name = "z_download_course",joinColumns = {@JoinColumn(name = "course_id")},inverseJoinColumns = {@JoinColumn(name = "user_id")})
    @JsonIgnoreProperties("downloadUsers")
    private List<UserEntity> downloadUsers;

    @OneToMany(mappedBy = "courseInfo")
    @JsonIgnoreProperties("courseInfo")
    private List<CourseComment> comments;

    @OneToMany(mappedBy = "courseInfo")
    @JsonIgnoreProperties("courseInfo")
    private List<CouponInfo> couponInfos;

    @OneToMany(mappedBy = "courseInfo")
    @JsonIgnoreProperties("courseInfo")
    private List<Giveaway> giveaways;
}
