package com.zsy.Interpreter;

public class Calculator {
    // 解析并计算表达式
    public static int calculate(String expression) {
        // 这里简化处理，实际应用中需要更复杂的解析器
        // 假设表达式格式为 "a + b - c + d" 形式

        String[] elements = expression.split(" ");
        Expression result = new NumberExpression(Integer.parseInt(elements[0]));

        for (int i = 1; i < elements.length; i += 2) {
            String operator = elements[i];
            int nextNumber = Integer.parseInt(elements[i + 1]);

            if (operator.equals("+")) {
                result = new AddExpression(result, new NumberExpression(nextNumber));
            } else if (operator.equals("-")) {
                result = new SubtractExpression(result, new NumberExpression(nextNumber));
            }
        }

        return result.interpret();
    }
}
