package com.zsy.collections;

public interface List<E> extends  Iterable<E>{

    void add(E element);

    void add(E element, int index);

    E remove(int index);

    boolean remove(E element);

    E set(int index, E element);

    E get(int index);

    int size();

}
