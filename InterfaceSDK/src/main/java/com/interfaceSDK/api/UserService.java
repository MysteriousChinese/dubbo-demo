package com.interfaceSDK.api;


import models.User;

public interface UserService {
    boolean add(User user);

    User query(int userId);

    String hello(String name);
}
