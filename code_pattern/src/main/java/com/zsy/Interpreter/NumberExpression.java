package com.zsy.Interpreter;

// 终结符表达式 - 数字
public class NumberExpression implements Expression {
    private final int number;

    public NumberExpression(int number) {
        this.number = number;
    }

    @Override
    public int interpret() {
        return number;
    }

    @Override
    public String toString() {
        return String.valueOf(number);
    }
}
