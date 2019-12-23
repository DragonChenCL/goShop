package com.dragon.web;

import com.dragon.dto.request.EditUserRequest;
import com.dragon.dto.request.LoginRequest;
import com.dragon.dto.request.SaveUserRequest;
import com.dragon.entity.user.UserEntity;
import com.dragon.exception.NormalException;
import com.dragon.result.Result;
import com.dragon.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
@Api(tags = {"用户模块"})
public class UserController {

    @Resource
    private IUserService userService;

    @ApiOperation(value = "用户注册")
    @PostMapping("/register")
    public String register(@Valid @RequestBody SaveUserRequest request){
        return userService.register(request);
    }

    @ApiOperation(value = "用户登录")
    @PostMapping("/login")
    public String login(@Valid @RequestBody LoginRequest request){
        return userService.login(request);
    }

    @ApiOperation(value = "获取个人信息")
    @GetMapping("/userInfo/{id}")
    public UserEntity getUserInfo(@PathVariable("id") String id){
        return userService.getUserInfo(id);
    }

    @ApiOperation(value = "修改个人信息")
    @PostMapping("/userInfo")
    public String editUserInfo(@RequestBody EditUserRequest request){
        return userService.editUserInfo(request);
    }

    @ApiOperation(value = "删除用户信息")
    @PostMapping("/delete/{userId}")
    public String deleteUser(@PathVariable("userId") String userId){
        return userService.deleteUser(userId);
    }
}
