package com.zsy.Interpreter;

import java.util.Map;

class GreaterThanExpression implements SQLExpression {
    private String field;
    private int value;

    // 解释 age > 18
    public boolean interpret(Map<String, Object> row) {
        return (int)row.get(field) > value;
    }
}