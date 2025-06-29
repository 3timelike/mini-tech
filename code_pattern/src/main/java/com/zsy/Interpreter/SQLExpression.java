package com.zsy.Interpreter;

import java.util.Map;

//定义表达式接口
interface SQLExpression {
    boolean interpret(Map<String, Object> row);
}