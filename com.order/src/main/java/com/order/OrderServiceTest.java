package com.order;

import com.interfaceSDK.api.UserService;
import org.apache.dubbo.common.utils.AtomicPositiveInteger;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.dubbo.rpc.RpcStatus;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceTest {
    @DubboReference(version = "1.0.0",check = false)
    private UserService userService;

    public void hello() {
        if(userService != null){
            System.out.println(userService.hello("版本"));
        }


    }
}
