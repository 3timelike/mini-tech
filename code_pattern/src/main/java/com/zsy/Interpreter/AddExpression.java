package com.zsy.Interpreter;

// 非终结符表达式 - 加法
public class AddExpression implements Expression {
    private final Expression left;
    private final Expression right;

    public AddExpression(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public int interpret() {
        return left.interpret() + right.interpret();
    }

    @Override
    public String toString() {
        return "(" + left + "+" + right + ")";
    }
}
