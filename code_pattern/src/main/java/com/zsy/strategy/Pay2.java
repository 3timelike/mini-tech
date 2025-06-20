package com.zsy.strategy;

import java.util.Map;

public class Pay2 implements PayInterface {
    @Override
    public boolean pay(double amount, Map<String, String> paymentInfo) {
        System.out.println("使用微信支付：" + amount + "元");
        // 实际微信支付逻辑...
        // 验证支付信息
        // 调用微信支付API
        // 处理支付结果
        return true; // 假设支付成功
    }
}
