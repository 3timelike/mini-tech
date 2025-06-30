package com.zsy.Proxy;

import java.lang.reflect.Proxy;

public class ProxyTest {
    public static void main(String[] args) {
        //extractedf1();
        // 创建真实对象
        f2();
    }

    private static void f2() {
        UserService realService = new UserServiceImpl();

        // 创建动态代理
        UserService proxy = (UserService) Proxy.newProxyInstance(
                UserService.class.getClassLoader(),
                new Class[]{UserService.class},
                new LoggingHandler(realService)
        );

        // 通过代理调用方法
        proxy.addUser("Alice");
        proxy.deleteUser("Bob");
    }

    private static void extractedf1() {
        DatabaseQuery proxy = new DatabaseQueryProxy();

        // 第一次查询 - 执行真实查询
        System.out.println(proxy.query("SELECT * FROM users"));

        // 第二次相同查询 - 从缓存获取
        System.out.println(proxy.query("SELECT * FROM users"));

        // 新查询
        System.out.println(proxy.query("SELECT * FROM orders"));
    }
}