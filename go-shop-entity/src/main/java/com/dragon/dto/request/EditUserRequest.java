package com.dragon.dto.request;

import lombok.Data;

@Data
public class EditUserRequest {
    private String id;

    private String userName;

    private String avatar;

    private String phone;

    private String school;

    private String oldPassword;

    private String newPassword;
}
