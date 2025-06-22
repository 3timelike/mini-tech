package com.zsy.composite;

/**
 * 客户端代码
 * 模拟组合模式的一个过程
 */
public class Main {
    public static void main(String[] args) {
        // 创建文件
        FileSystemComponent file1 = new File("document.txt", 1024);
        FileSystemComponent file2 = new File("image.jpg", 2048);
        FileSystemComponent file3 = new File("report.pdf", 3072);
        FileSystemComponent file4 = new File("notes.txt", 512);

        // 创建子目录
        Directory subDir = new Directory("Subfolder");
        subDir.addComponent(file3);
        subDir.addComponent(file4);

        // 创建根目录
        Directory rootDir = new Directory("Root");
        rootDir.addComponent(file1);
        rootDir.addComponent(file2);
        rootDir.addComponent(subDir);

        // 显示文件系统结构
        System.out.println("文件系统结构:");
        rootDir.display("");

        // 计算总大小
        System.out.println("\n总大小: " + rootDir.getSize() + " bytes");
    }
}
