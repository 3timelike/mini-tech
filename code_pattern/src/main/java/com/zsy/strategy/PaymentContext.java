package com.zsy.strategy;

import java.util.Map;

/**
 * 支付上下文
 */
class PaymentContext {
    private PayInterface paymentStrategy;

    public PaymentContext(PayInterface paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    public void setPaymentStrategy(PayInterface paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    public boolean executePayment(double amount, Map<String, String> paymentInfo) {
        return paymentStrategy.pay(amount, paymentInfo);
    }
}
