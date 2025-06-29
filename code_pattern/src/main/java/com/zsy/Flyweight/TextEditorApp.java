package com.zsy.Flyweight;

public class TextEditorApp {
    public static void main(String[] args) {
        Document doc = new Document();

        // 添加文档内容
        doc.addCharacter('H', 1, "Arial", 12, "黑色");
        doc.addCharacter('e', 2, "Arial", 12, "黑色");
        doc.addCharacter('l', 3, "Arial", 12, "黑色");
        doc.addCharacter('l', 4, "Arial", 12, "黑色");
        doc.addCharacter('o', 5, "Arial", 12, "黑色");

        // 强调某个字符
        doc.addCharacter('W', 6, "Arial", 16, "红色");
        doc.addCharacter('o', 7, "Arial", 16, "红色");
        doc.addCharacter('r', 8, "Arial", 16, "红色");
        doc.addCharacter('l', 9, "Arial", 16, "红色");
        doc.addCharacter('d', 10, "Arial", 16, "红色");

        // 显示所有样式
        StyleFactory.showStyles();

        // 显示文档
        doc.display();
    }
}
