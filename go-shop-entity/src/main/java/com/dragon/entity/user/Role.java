package com.dragon.entity.user;

import com.dragon.entity.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "c_sys_role")
@Entity
public class Role extends BaseEntity {
    private String roleName;

    private String remark;

    @ManyToMany
    @JoinTable(name = "c_user_role",joinColumns = {@JoinColumn(name = "role_id")},inverseJoinColumns = {@JoinColumn(name = "user_id")})
    private List<UserEntity> userEntities;
}
