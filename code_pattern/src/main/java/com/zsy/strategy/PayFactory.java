package com.zsy.strategy;

public class PayFactory {
    public static PayInterface getStrategy(String paymentType) {
        switch (paymentType.toLowerCase()) {
            case "alipay":
                return new Pay1();
            case "wechat":
                return new Pay2();
            default:
                throw new IllegalArgumentException("不支持的支付方式: " + paymentType);
        }
    }
}
