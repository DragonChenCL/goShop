package com.dragon.service;

import com.dragon.dto.request.EditUserRequest;
import com.dragon.dto.request.LoginRequest;
import com.dragon.dto.request.SaveUserRequest;
import com.dragon.entity.user.UserEntity;

public interface IUserService {

    //用户注册
    String register(SaveUserRequest request);

    //用户登录
    String login(LoginRequest request);

    //获取个人信息
    UserEntity getUserInfo(String id);

    //修改个人信息
    String editUserInfo(EditUserRequest request);

    //删除用户信息
    String deleteUser(String userId);
}
