package com.zsy.Flyweight;

import java.util.ArrayList;
import java.util.List;

public class Document {
    private final List<DocumentCharacter> characters = new ArrayList<>();

    public void addCharacter(char c, int position, String font, int size, String color) {
        CharacterStyle style = StyleFactory.getStyle(font, size, color);
        characters.add(new DocumentCharacter(c, position, style));
    }

    public void display() {
        System.out.println("\n文档内容:");
        characters.forEach(DocumentCharacter::display);
    }
}
