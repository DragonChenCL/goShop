package com.dragon.entity.course;

import com.dragon.entity.base.BaseEntity;
import com.dragon.entity.user.UserEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.List;

/**
 * 习题
 */
@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table(name = "c_exercise_info")
public class ExerciseInfo extends BaseEntity {
    private String content;

    private String result;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnoreProperties("exercises")
    private UserEntity questioner; //出题人

    @ManyToMany
    @JoinTable(name = "m_user_exercise",joinColumns = {@JoinColumn(name = "exercise_id")},inverseJoinColumns = {@JoinColumn(name = "user_id")})
    @JsonIgnoreProperties("favoriteExerciseInfos")
    private List<UserEntity> userEntities;  //我的学习
}
