package com.dragon.entity.user;

import com.dragon.entity.base.BaseEntity;
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
public class UserEntity extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1357900826704188523L;

    @Column(columnDefinition = "VARCHAR(20) COMMENT '用户名'",nullable = false)
    private String userName;

    @Column(columnDefinition = "VARCHAR(20) COMMENT '盐值'",nullable = false)
    private String salt;

    @Column(columnDefinition = "VARCHAR(32) COMMENT '密码'",nullable = false)
    private String password;

    @ManyToMany(mappedBy = "userEntities")
    private List<Role> roles;
}
