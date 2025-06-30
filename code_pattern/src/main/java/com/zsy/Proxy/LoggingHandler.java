package com.zsy.Proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;

public class LoggingHandler implements InvocationHandler {
    private Object target;

    public LoggingHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 方法调用前记录日志
        System.out.println("Before method: " + method.getName());
        if (args != null) {
            System.out.println("Arguments: " + Arrays.toString(args));
        }

        // 调用真实方法
        Object result = method.invoke(target, args);

        // 方法调用后记录日志
        System.out.println("After method: " + method.getName());

        return result;
    }
}