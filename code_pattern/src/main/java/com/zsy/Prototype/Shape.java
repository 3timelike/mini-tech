package com.zsy.Prototype;

// 原型接口
public interface Shape extends Cloneable {
    void draw();
    Shape clone();
}
