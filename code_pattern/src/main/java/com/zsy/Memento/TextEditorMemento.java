package com.zsy.Memento;

// Memento - 存储Originator的内部状态
public class TextEditorMemento {
    private final String text;
    private final int cursorPosition;

    public TextEditorMemento(String text, int cursorPosition) {
        this.text = text;
        this.cursorPosition = cursorPosition;
    }

    // 注意：这些方法应该是包私有的或只有Originator可以访问
    String getSavedText() {
        return text;
    }

    int getSavedCursorPosition() {
        return cursorPosition;
    }
}
