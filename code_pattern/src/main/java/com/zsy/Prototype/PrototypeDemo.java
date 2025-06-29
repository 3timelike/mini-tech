package com.zsy.Prototype;

public class PrototypeDemo {
    public static void main(String[] args) {
        // 初始化原型缓存
        ShapeCache.loadCache();

        // 通过克隆创建新对象
        Shape clonedCircle = ShapeCache.getShape("红色圆形");
        System.out.print("克隆的圆形: ");
        clonedCircle.draw();

        Shape clonedRectangle = ShapeCache.getShape("蓝色矩形");
        System.out.print("克隆的矩形: ");
        clonedRectangle.draw();

        // 修改克隆后的对象
        if (clonedCircle instanceof Circle) {
            Circle circle = (Circle) clonedCircle;
            circle.setColor("绿色");
            circle.setRadius(15);
            System.out.print("修改后的圆形: ");
            circle.draw();
        }

        // 原始原型不受影响
        Shape originalCircle = ShapeCache.getShape("红色圆形");
        System.out.print("原始圆形: ");
        originalCircle.draw();
    }
}
