package com.dragon.service.impl;

import com.dragon.service.IHelloWorldService;
import org.springframework.stereotype.Service;

@Service
public class HelloWorldServiceImpl implements IHelloWorldService {
    @Override
    public String helloWorld() {
        return "hello world";
    }
}
