package com.zsy.Iterator;

public interface Aggregate<T> {
    Iterator<T> createIterator();
}
