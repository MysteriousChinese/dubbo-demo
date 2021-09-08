package org.example;

import static org.junit.Assert.assertTrue;

import com.config.UserConfiguration;
import com.interfaceSDK.api.OrderService;
import com.interfaceSDK.api.UserService;
import models.Order;
import models.User;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.retry.RetryOneTime;
import org.apache.dubbo.config.ApplicationConfig;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.util.List;

/**
 * Unit test for simple App.
 */
public class AppTest {

    private CuratorFramework curatorFramework;

    @Before
    public void Before() {
        curatorFramework = CuratorFrameworkFactory.newClient("localhost:2181", new RetryOneTime(3000));
        curatorFramework.start();
    }


    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue() throws Exception {

        //注解方式
       /* AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(UserConfiguration.class);
        context.start();
        System.in.read();*/

        //xml配置方式
        ClassPathXmlApplicationContext context1 = new ClassPathXmlApplicationContext("dubboConfig.xml");
       /* String path = "/test";
        if (curatorFramework.checkExists().forPath(path) == null) {
            curatorFramework.create().creatingParentContainersIfNeeded().forPath(path);
        }
        NodeCache nodeCache = new NodeCache(curatorFramework, "/test", false);
        try {
            nodeCache.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
        nodeCache.getListenable().addListener(() -> {
            String content = new String(nodeCache.getCurrentData().getData());
            if (orderService != null && content.equals("order")) {
                System.out.println("=====================>" + orderService);
            }
        });*/
        context1.start();

        OrderService orderService1 = context1.getBean("orderService", OrderService.class);
        List<Order> orders = orderService1.queryOrderByUserId(1);
        orders.forEach(i -> System.out.println("================" + i.getOrderId()));

        System.in.read();


    }


    @After
    public void After() {
        curatorFramework.close();
    }
}
