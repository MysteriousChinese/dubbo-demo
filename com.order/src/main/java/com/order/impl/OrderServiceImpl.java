package com.order.impl;

import com.interfaceSDK.api.OrderService;
import models.Order;
import org.apache.dubbo.config.annotation.DubboService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@DubboService
public class OrderServiceImpl implements OrderService {
    @Override
    public boolean add(Order order) {
        return false;
    }

    @Override
    public List<Order> queryOrderByUserId(int userId) {
        List<Order> orders = new ArrayList<>();
        Order order = new Order("1", new BigDecimal(50), new Date(), userId);
        orders.add(order);
        return orders;
    }
}
