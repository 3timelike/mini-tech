package com.zsy.Adapter;

public class Main {
    public static void main(String[] args) {
        // 使用类适配器
        Target classAdapter = new ClassAdapter();
        classAdapter.request();

        // 使用对象适配器
        Adaptee adaptee = new Adaptee();
        Target objectAdapter = new ObjectAdapter(adaptee);
        objectAdapter.request();
    }
}