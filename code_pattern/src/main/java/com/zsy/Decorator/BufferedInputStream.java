package com.zsy.Decorator;

// 具体装饰器
class BufferedInputStream extends FilterInputStream {
    public BufferedInputStream(InputStream in) {
        super(in);
    }

    @Override
    public int read() {
        System.out.println("缓冲读取");
        return super.read();
    }
}