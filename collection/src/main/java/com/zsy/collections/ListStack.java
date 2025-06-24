package com.zsy.collections;

public class ListStack {
    private StackNode top;

    // 入栈
    public void push(int element) {
        StackNode newNode = new StackNode(element);
        newNode.next = top;
        top = newNode;
    }

    // 出栈
    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("栈是空的");
        }
        int popped = top.data;
        top = top.next;
        return popped;
    }

    // 查看栈顶元素
    public int peek() {
        if (isEmpty()) {
            throw new RuntimeException("栈是空的");
        }
        return top.data;
    }

    // 判断栈是否为空
    public boolean isEmpty() {
        return top == null;
    }
    class StackNode {
        int data;
        StackNode next;

        public StackNode(int data) {
            this.data = data;
            this.next = null;
        }
    }
}
