package com.zsy.Prototype;

import java.util.HashMap;
import java.util.Map;

// 原型管理器
public class ShapeCache {
    private static Map<String, Shape> shapeMap = new HashMap<>();

    public static Shape getShape(String shapeId) {
        Shape cachedShape = shapeMap.get(shapeId);
        return (Shape) cachedShape.clone();
    }

    public static void loadCache() {
        Circle circle = new Circle("红色", 10);
        shapeMap.put("红色圆形", circle);

        Rectangle rectangle = new Rectangle("蓝色", 20, 30);
        shapeMap.put("蓝色矩形", rectangle);
    }
}
