package com.zsy.Decorator;

class DataInputStream extends FilterInputStream {
    public DataInputStream(InputStream in) {
        super(in);
    }

    @Override
    public int read() {
        System.out.println("按数据类型读取");
        return super.read();
    }
}