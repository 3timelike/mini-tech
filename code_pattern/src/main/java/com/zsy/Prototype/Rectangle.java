package com.zsy.Prototype;

// 具体原型 - 矩形
public class Rectangle implements Shape {
    private String color;
    private int width;
    private int height;

    public Rectangle(String color, int width, int height) {
        this.color = color;
        this.width = width;
        this.height = height;
    }

    @Override
    public void draw() {
        System.out.println("绘制矩形: 颜色=" + color +
                ", 宽度=" + width + ", 高度=" + height);
    }

    @Override
    public Shape clone() {
        try {
            return (Shape) super.clone();
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setDimensions(int width, int height) {
        this.width = width;
        this.height = height;
    }
}
