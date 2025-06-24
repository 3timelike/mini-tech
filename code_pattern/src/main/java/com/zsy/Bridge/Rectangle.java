package com.zsy.Bridge;

/**
 * 扩展抽象部分 - 矩形
 */
class Rectangle extends Shape {
    private double x1, y1, x2, y2;

    public Rectangle(double x1, double y1, double x2, double y2, DrawingAPI drawingAPI) {
        super(drawingAPI);
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    @Override
    public void draw() {
        drawingAPI.drawRectangle(x1, y1, x2, y2);
    }

    @Override
    public void resize(double scale) {
        double width = x2 - x1;
        double height = y2 - y1;

        double newWidth = width * scale;
        double newHeight = height * scale;

        x2 = x1 + newWidth;
        y2 = y1 + newHeight;

        System.out.printf("矩形缩放 %.1f 倍 -> 新尺寸: %.1f x %.1f\n",
                scale, newWidth, newHeight);
    }
}
