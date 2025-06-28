package com.zsy.Decorator;

// 装饰器基类
class FilterInputStream extends InputStream {
    protected InputStream in;

    protected FilterInputStream(InputStream in) {
        this.in = in;
    }

    @Override
    public int read() {
        return in.read();
    }
}
