package com.zsy.composite;

import java.util.ArrayList;
import java.util.List;

/**
 * 文件夹（容器节点）
 */
class Directory implements FileSystemComponent {
    private String name;
    private List<FileSystemComponent> children = new ArrayList<>();

    public Directory(String name) {
        this.name = name;
    }

    public void addComponent(FileSystemComponent component) {
        children.add(component);
    }

    public void removeComponent(FileSystemComponent component) {
        children.remove(component);
    }

    @Override
    public void display(String indent) {
        System.out.println(indent + "📁 " + name);
        for (FileSystemComponent component : children) {
            component.display(indent + "    ");
        }
    }

    @Override
    public long getSize() {
        long totalSize = 0;
        for (FileSystemComponent component : children) {
            totalSize += component.getSize();
        }
        return totalSize;
    }
}
