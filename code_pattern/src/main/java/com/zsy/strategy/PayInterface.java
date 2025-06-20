package com.zsy.strategy;

import java.util.Map;

public interface PayInterface {
    /**
     * 支付方法
     * @param amount 支付金额
     * @param paymentInfo 支付信息（如卡号、密码等）
     * @return 支付结果
     */
    boolean pay(double amount, Map<String, String> paymentInfo);
}
