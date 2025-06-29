package com.zsy.Flyweight;

// 包含外在状态的对象
public class DocumentCharacter {
    private final char character;
    private final int position;
    private final CharacterStyle style;

    public DocumentCharacter(char character, int position, CharacterStyle style) {
        this.character = character;
        this.position = position;
        this.style = style;
    }

    public void display() {
        System.out.printf("位置 %d: ", position);
        style.applyStyle(character);
    }
}
