package com.zsy.composite;


/**
 * 抽象文件系统组件
 */
public interface FileSystemComponent {

    void display(String indent);

    long getSize();
}
