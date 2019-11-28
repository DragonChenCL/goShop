package com.dragon.web;

import com.dragon.entity.base.Result;
import com.dragon.entity.user.UserEntity;
import com.dragon.service.IHelloWorldService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/goshop")
@Api(tags = "用户测试类")
public class HelloWorldController {
    @Autowired
    private IHelloWorldService helloWorldService;

    @GetMapping("/hello")
    @ApiOperation(value = "xxxxx",notes = "2")
    @RequiresAuthentication
    public String helloWorld(){
        return helloWorldService.helloWorld();
    }

    @GetMapping("/hello2")
    @ApiOperation(value = "zzzz",notes = "2")
    public String helloWorld2(){
        return helloWorldService.helloWorld();
    }
}
