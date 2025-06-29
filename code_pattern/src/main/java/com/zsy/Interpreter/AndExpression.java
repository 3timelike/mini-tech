package com.zsy.Interpreter;

import java.util.Map;


//定义and表达式
class AndExpression implements SQLExpression {
    private SQLExpression left;
    private SQLExpression right;

    // 解释 AND 连接的条件
    public boolean interpret(Map<String, Object> row) {
        return left.interpret(row) && right.interpret(row);
    }
}

