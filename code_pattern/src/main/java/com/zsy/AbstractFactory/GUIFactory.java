package com.zsy.AbstractFactory;

/**
 * 抽象工厂接口 - 创建UI组件家族
 */
interface GUIFactory {
    Button createButton();
    Checkbox createCheckbox();
    TextField createTextField();
}
