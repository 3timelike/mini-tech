package com.zsy.Flyweight;

// 享元类 - 字符格式（内在状态）
public class CharacterStyle {
    private final String font;
    private final int size;
    private final String color;

    public CharacterStyle(String font, int size, String color) {
        this.font = font;
        this.size = size;
        this.color = color;
    }

    public void applyStyle(char character) {
        System.out.printf("字符 '%c' [字体: %s, 大小: %d, 颜色: %s]\n",
                character, font, size, color);
    }

    @Override
    public String toString() {
        return String.format("Style[%s,%d,%s]", font, size, color);
    }
}
