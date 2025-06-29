package com.zsy.Memento;

public class MementoPatternDemo {
    public static void main(String[] args) {
        TextEditor editor = new TextEditor();
        TextEditorHistory history = new TextEditorHistory();

        // 编辑文本并保存状态
        editor.write("Hello");
        history.save(editor);

        editor.write(" World");
        history.save(editor);

        editor.write("!");
        System.out.println("\n当前状态:");
        editor.display();

        // 撤销操作
        System.out.println("\n第一次撤销:");
        history.undo(editor);

        System.out.println("\n第二次撤销:");
        history.undo(editor);

        System.out.println("\n尝试第三次撤销:");
        history.undo(editor);
    }
}
