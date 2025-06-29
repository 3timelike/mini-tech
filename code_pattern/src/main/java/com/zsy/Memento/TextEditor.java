package com.zsy.Memento;

// Originator - 需要保存状态的对象
public class TextEditor {
    private String text;
    private int cursorPosition;

    public TextEditor() {
        this.text = "";
        this.cursorPosition = 0;
    }

    public void write(String text) {
        this.text += text;
        this.cursorPosition += text.length();
        System.out.println("当前文本: " + this.text);
        System.out.println("光标位置: " + this.cursorPosition);
    }

    public void setCursorPosition(int position) {
        this.cursorPosition = position;
        System.out.println("设置光标位置: " + this.cursorPosition);
    }

    // 创建备忘录
    public TextEditorMemento createMemento() {
        System.out.println("创建备忘录...");
        return new TextEditorMemento(text, cursorPosition);
    }

    // 从备忘录恢复
    public void restoreFromMemento(TextEditorMemento memento) {
        this.text = memento.getSavedText();
        this.cursorPosition = memento.getSavedCursorPosition();
        System.out.println("从备忘录恢复...");
        System.out.println("恢复后文本: " + this.text);
        System.out.println("恢复后光标位置: " + this.cursorPosition);
    }

    public void display() {
        System.out.println("当前状态 - 文本: " + text + ", 光标位置: " + cursorPosition);
    }
}