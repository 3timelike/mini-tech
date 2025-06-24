package com.zsy.Bridge;

/**
 * 桥接模式 Demo
 * 演示如何将图形形状（抽象部分）与绘图API（实现部分）解耦
 */
public class Main {
    public static void main(String[] args) {
        // 创建不同的绘图API（实现部分）
        DrawingAPI rasterAPI = new RasterDrawingAPI();
        DrawingAPI vectorAPI = new VectorDrawingAPI();

        // 创建不同的形状（抽象部分），并与绘图API桥接
        Shape circle1 = new Circle(1, 2, 3, rasterAPI);
        Shape circle2 = new Circle(5, 7, 11, vectorAPI);

        Shape rectangle1 = new Rectangle(1, 2, 4, 6, rasterAPI);
        Shape rectangle2 = new Rectangle(3, 5, 8, 13, vectorAPI);

        // 绘制形状
        System.out.println("使用不同的绘图API绘制形状:");
        circle1.draw();
        circle2.draw();
        rectangle1.draw();
        rectangle2.draw();

        System.out.println("\n调整大小并重新绘制:");
        circle1.resize(2.5);
        circle1.draw();

        rectangle2.resize(0.5);
        rectangle2.draw();
    }
}
