package com.dragon.service.impl;

import com.dragon.dto.request.EditUserRequest;
import com.dragon.dto.request.LoginRequest;
import com.dragon.dto.request.SaveUserRequest;
import com.dragon.entity.user.Role;
import com.dragon.entity.user.UserEntity;
import com.dragon.exception.NormalException;
import com.dragon.service.IUserService;
import com.dragon.user.RoleRepository;
import com.dragon.user.UserRepository;
import lombok.SneakyThrows;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService {
    @Resource
    private UserRepository userRepository;
    @Resource
    private RoleRepository roleRepository;

    @Override
    public String deleteUser(String userId) {
        UserEntity userEntity = this.checkUserById(userId);
        userEntity.setDelete(Boolean.TRUE);
        userRepository.save(userEntity);
        return "删除成功";
    }

    @Override
    public String editUserInfo(EditUserRequest request) {
        UserEntity userEntity = this.checkUserById(request.getId());
        userEntity.setUserName(request.getUserName());
        userEntity.setAvatar(request.getAvatar());
        userEntity.setPhone(request.getPhone());
        userEntity.setSchool(request.getSchool());
        if (StringUtils.isNotBlank(request.getOldPassword()) && StringUtils.isNotBlank(request.getNewPassword())){
            if (!new SimpleHash("MD5",request.getOldPassword(),userEntity.getSalt(),1024).toHex().equals(userEntity.getPassword())){
                throw new NormalException("旧密码不正确");
            }
            userEntity.setPassword(new SimpleHash("MD5",request.getNewPassword(),userEntity.getSalt(),1024).toHex());
        }
        userRepository.save(userEntity);
        return "修改成功";
    }

    @Override
    public UserEntity getUserInfo(String id) {
        return this.checkUserById(id);
    }

    @Override
    public String login(LoginRequest request) {
        UserEntity userEntity = this.checkUserByAccount(request.getAccount());
        if (!userEntity.getPassword().equals(new SimpleHash("MD5",request.getPassword(),userEntity.getSalt(),1024).toHex())){
            throw new NormalException("密码不正确");
        }
        //todo 密码规则
        return userEntity.getId();
    }

    private UserEntity checkUserByAccount(String account) {
        Optional<UserEntity> userEntityOptional = userRepository.findByDeleteIsFalseAndAvailableIsTrueAndAccount(account);
        if (!userEntityOptional.isPresent()){
            throw new NormalException("用户不存在");
        }
        return userEntityOptional.get();
    }

    private UserEntity checkUserById(String id) {
        Optional<UserEntity> userEntityOptional = userRepository.findByDeleteIsFalseAndAvailableIsTrueAndId(id);
        if (!userEntityOptional.isPresent()){
            throw new NormalException("用户不存在");
        }
        return userEntityOptional.get();
    }

    @Override
    public String register(SaveUserRequest request) {
        Optional<UserEntity> userEntityOptional = userRepository.findByDeleteIsFalseAndAvailableIsTrueAndAccount(request.getAccount());
        if (userEntityOptional.isPresent()){
            throw new NormalException("用户名重复");
        }
        UserEntity userEntity = new UserEntity();
        Optional<Role> roleOptional = roleRepository.findById(request.getRoleId());
        if (!roleOptional.isPresent()){
            throw new NormalException("角色不存在");
        }
        if (StringUtils.isNotBlank("wwww")) {
            System.out.println("xxxx");
        }
        BeanUtils.copyProperties(request,userEntity);
        userEntity.setSalt(RandomStringUtils.randomAlphabetic(10));
        userEntity.setPassword(new SimpleHash("MD5",request.getPassword(),userEntity.getSalt(),1024).toHex());
        userRepository.save(userEntity);
        return "注册成功";
    }
}
