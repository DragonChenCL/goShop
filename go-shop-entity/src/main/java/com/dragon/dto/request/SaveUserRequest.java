package com.dragon.dto.request;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Data
public class SaveUserRequest {

    @NotBlank(message = "用户名不能为空")
    private String userName;

    @NotBlank(message = "密码不能为空")
    @Length(min = 5,max = 12,message = "密码长度不能少于五个字符，不能多于12个字符")
    private String password;

    @NotBlank(message = "账号不能为空")
    @Length(min = 5,max = 12,message = "账号长度不能少于五个字符，不能多于12个字符")
    private String account;

    private String avatar;

    private String phone;

    private String school; //所属学校

    @NotBlank(message = "密码不能为空")
    private String roleId;


}
