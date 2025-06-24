package com.zsy.Bridge;

/**
 * 抽象部分 - 形状
 */
abstract class Shape {
    protected DrawingAPI drawingAPI;

    protected Shape(DrawingAPI drawingAPI) {
        this.drawingAPI = drawingAPI;
    }

    public abstract void draw();     // 低层次操作
    public abstract void resize(double scale); // 高层次操作
}