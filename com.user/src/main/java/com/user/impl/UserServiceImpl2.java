package com.user.impl;

import com.interfaceSDK.api.UserService;
import models.User;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;
import org.apache.dubbo.config.annotation.Service;

@DubboService(timeout = 3000,version = "2.0.0")
public class UserServiceImpl2 implements UserService {
    @Override
    public boolean add(User user) {
        return false;
    }

    @Override
    public User query(int userId) {
        return null;
    }

    @Override
    public String hello(String name) {
        return "name + 2.0.0";
    }
}
