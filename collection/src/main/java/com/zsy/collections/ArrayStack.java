package com.zsy.collections;

public class ArrayStack<E> {
    private Object [] stackArray;
    private int top;
    private int capacity;
    public ArrayStack() {
        this(10);
    }
    public ArrayStack(int capacity) {
        stackArray = new Object[capacity];
        top = -1;
        this.capacity = capacity;
    }

    // 入栈
    public void push(E element) {
        if (isFull()) {
           //扩容
            resize();
        }
        stackArray[++top] = element;
    }

    private void resize() {
        int newCapacity = capacity * 2;
        Object[] newElements = new Object[newCapacity];
        System.arraycopy(stackArray, 0, newElements, 0, top);
        stackArray = newElements;
        capacity = newCapacity;
    }

    // 出栈
    public E pop() {
        if (isEmpty()) {
            throw new RuntimeException("Stack is empty");
        }
        return (E)stackArray[top--];
    }

    // 查看栈顶元素
    public E peek() {
        if (isEmpty()) {
            throw new RuntimeException("Stack is empty");
        }
        return (E)stackArray[top];
    }

    // 判断栈是否为空
    public boolean isEmpty() {
        return top == -1;
    }

    // 栈中有多少元素
    public int size() {
        return top + 1;
    }

    // 判断栈是否已满
    public boolean isFull() {
        return top == stackArray.length - 1;
    }
}
