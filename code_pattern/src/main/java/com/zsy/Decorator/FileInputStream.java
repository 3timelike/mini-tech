package com.zsy.Decorator;


class FileInputStream extends InputStream {
    @Override
    public int read() {
        System.out.println("读取文件字节");
        return 0;
    }
}
