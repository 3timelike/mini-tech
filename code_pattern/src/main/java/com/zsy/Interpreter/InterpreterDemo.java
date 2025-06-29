package com.zsy.Interpreter;

public class InterpreterDemo {
    public static void main(String[] args) {
        String expression1 = "10 + 5 - 3 + 2";
        String expression2 = "20 - 5 + 10 - 8";

        System.out.println(expression1 + " = " + Calculator.calculate(expression1));
        System.out.println(expression2 + " = " + Calculator.calculate(expression2));
    }
}
