package org.example;

import static org.junit.Assert.assertTrue;

import com.interfaceSDK.api.UserService;
import models.User;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.retry.RetryOneTime;
import org.apache.dubbo.config.annotation.Reference;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * Unit test for simple App.
 */
public class AppTest {
    /**
     * Rigorous Test :-)
     */

    private CuratorFramework curatorFramework;

    @Reference
    private UserService userService;

    @Before
    public void Before() {
        curatorFramework = CuratorFrameworkFactory.newClient("localhost:2181", new RetryOneTime(3000));
        curatorFramework.start();
    }

    @Test
    public void shouldAnswerWithTrue() throws Exception {

        int id = 1;
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("dubboConfig.xml");
        //UserService userService = context.getBean("userService", UserService.class);
        String path = "/test";

        if(curatorFramework.checkExists().forPath(path) == null){
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
            if (userService != null && content.equals("user")) {
                System.out.println("=====================>" + userService);
            }
        });

        context.start();
        System.in.read();
    }

    @After
    public void After() {
        curatorFramework.close();
    }
}
