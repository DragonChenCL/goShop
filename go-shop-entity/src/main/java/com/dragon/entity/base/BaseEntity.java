package com.dragon.entity.base;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {
    @Id
    @GeneratedValue(generator = "jpa-uuid")
    @GenericGenerator(name = "jpa-uuid",strategy = "uuid")
    private String id;

    @Column(name = "`delete`",columnDefinition = "tinyint(1) DEFAULT 0 COMMENT '是否删除'",nullable=false)
    private Boolean delete;

    @Column(name = "`available`",columnDefinition = "tinyint(1) DEFAULT 1 COMMENT '是否启用'",nullable=false)
    private Boolean available;

    @CreatedDate
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;

    @LastModifiedDate
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDate;

    @PrePersist
    private void prePersist(){
        if (getAvailable() == null){
            setAvailable(Boolean.TRUE);
        }
        if (getDelete() == null){
            setDelete(Boolean.FALSE);
        }
    }
}
