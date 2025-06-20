package com.zsy.strategy;

import java.util.Map;

public class Pay1 implements PayInterface {
    @Override
    public boolean pay(double amount, Map<String, String> paymentInfo) {
        System.out.println("使用支付宝支付：" + amount + "元");
        // 实际支付宝支付逻辑...
        // 验证支付信息
        // 调用支付宝API
        // 处理支付结果
        return true; // 假设支付成功
    }
}
