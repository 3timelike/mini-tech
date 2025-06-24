package com.zsy.Bridge;

/**
 * 扩展抽象部分 - 圆形
 */
class Circle extends Shape {
    private double x, y, radius;

    public Circle(double x, double y, double radius, DrawingAPI drawingAPI) {
        super(drawingAPI);
        this.x = x;
        this.y = y;
        this.radius = radius;
    }

    @Override
    public void draw() {
        drawingAPI.drawCircle(x, y, radius);
    }

    @Override
    public void resize(double scale) {
        radius *= scale;
        System.out.printf("圆形缩放 %.1f 倍 -> 新半径: %.1f\n", scale, radius);
    }
}