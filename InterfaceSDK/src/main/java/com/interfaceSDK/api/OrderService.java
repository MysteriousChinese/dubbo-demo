package com.interfaceSDK.api;

import models.Order;

import java.util.List;

public interface OrderService {
    boolean add(Order order);

    List<Order> queryOrderByUserId(int userId);
}
