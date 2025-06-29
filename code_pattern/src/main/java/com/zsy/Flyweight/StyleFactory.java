package com.zsy.Flyweight;

import java.util.HashMap;
import java.util.Map;

// 享元工厂
public class StyleFactory {
    private static final Map<String, CharacterStyle> styles = new HashMap<>();

    public static CharacterStyle getStyle(String font, int size, String color) {
        String key = font + size + color;
        if (!styles.containsKey(key)) {
            styles.put(key, new CharacterStyle(font, size, color));
            System.out.println("创建新样式: " + key);
        }
        return styles.get(key);
    }

    public static void showStyles() {
        System.out.println("\n当前所有样式:");
        styles.values().forEach(System.out::println);
    }
}