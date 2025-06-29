package com.zsy.Memento;

import java.util.Stack;

// Caretaker - 负责保存和管理备忘录
public class TextEditorHistory {
    private final Stack<TextEditorMemento> history = new Stack<>();

    public void save(TextEditor editor) {
        history.push(editor.createMemento());
    }

    public void undo(TextEditor editor) {
        if (!history.isEmpty()) {
            editor.restoreFromMemento(history.pop());
        } else {
            System.out.println("无法撤销 - 历史记录为空");
        }
    }
}