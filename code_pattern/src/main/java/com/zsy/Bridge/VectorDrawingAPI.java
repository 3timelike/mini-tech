package com.zsy.Bridge;

/**
 * 具体实现 - 矢量绘图API
 */
class VectorDrawingAPI implements DrawingAPI {
    @Override
    public void drawCircle(double x, double y, double radius) {
        System.out.printf("在矢量图形上绘制圆: (%.1f, %.1f) 半径 %.1f\n", x, y, radius);
    }

    @Override
    public void drawRectangle(double x1, double y1, double x2, double y2) {
        System.out.printf("在矢量图形上绘制矩形: 从(%.1f, %.1f) 到 (%.1f, %.1f)\n",
                x1, y1, x2, y2);
    }
}