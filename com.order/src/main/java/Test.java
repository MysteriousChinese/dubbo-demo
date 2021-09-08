import com.config.OrderConfiguration;
import com.interfaceSDK.api.UserService;
import com.order.OrderServiceTest;
import com.order.impl.OrderServiceImpl;
import models.Order;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.retry.RetryOneTime;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;


public class Test {

 /*   @Reference
    private static UserService userService;*/


    public void Test() {

    }

    public static void main(String[] args) throws Exception {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(OrderConfiguration.class);
        context.start();

        OrderServiceImpl orderServiceImpl = context.getBean("orderServiceImpl", OrderServiceImpl.class);
        if (orderServiceImpl != null) {
            Thread thread = new Thread(() -> {
                OrderServiceTest orderServiceTest = context.getBean("orderServiceTest", OrderServiceTest.class);
                if (orderServiceTest != null) {
                    orderServiceTest.hello();
                }
            });
            thread.start();
        }

        System.in.read();


        /*String path = "/test";
        CuratorFramework curatorFramework = CuratorFrameworkFactory.newClient("localhost:2181", new RetryOneTime(3000));
        curatorFramework.start();
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
            if (userService != null) {
                if (content.equals("user")) {
                    System.out.println("=====================>" + userService);
                }
            }
            else{
                System.out.println("=====================>" + userService);
            }
        });*/

        //context.start();
        //System.in.read();
    }

}
