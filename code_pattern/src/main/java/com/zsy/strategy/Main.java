package com.zsy.strategy;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        // 准备支付信息
        Map<String, String> paymentInfo = new HashMap<>();
        paymentInfo.put("account", "user123");
        paymentInfo.put("password", "123456");
        paymentInfo.put("cardNumber", "622588******1234"); // 银行卡支付需要


        // 创建支付上下文
        PaymentContext context = new PaymentContext(PayFactory.getStrategy("wechat"));

        // 使用支付宝支付
        boolean result1 = context.executePayment(100.0, paymentInfo);
        System.out.println("支付宝支付结果: " + (result1 ? "成功" : "失败"));

        // 动态切换到微信支付
        context.setPaymentStrategy(PayFactory.getStrategy("alipay"));
        boolean result2 = context.executePayment(200.0, paymentInfo);
        System.out.println("微信支付结果: " + (result2 ? "成功" : "失败"));

        //添加银行卡支付方式，可以通过动态代理的方式进行实现，不展开介绍可以看看proxy包的实现

    }
}
