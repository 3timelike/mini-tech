package com.zsy.collections;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * 自定义动态数组实现，支持泛型元素存储和基本列表操作
 *
 * <p>本实现提供类似Java ArrayList的核心功能，包括：</p>
 * <ul>
 *   <li>动态扩容机制（默认初始容量10，按2倍扩容）</li>
 *   <li>支持索引访问和修改</li>
 *   <li>提供元素遍历功能</li>
 *   <li>实现基本的增删改查操作</li>
 * </ul>
 *
 * <p><b>特性说明：</b></p>
 * <ul>
 *   <li>时间复杂度：随机访问O(1)，插入/删除平均O(n)</li>
 *   <li>空间复杂度：O(n)</li>
 *   <li><b>非线程安全</b> - 多线程环境下需要外部同步</li>
 * </ul>
 *
 * @param <E> 列表元素类型
 * @author zsy
 * @see List
 */
public class MyArrayList<E> implements List<E> {

    /**
     * 当前列表中实际存储的元素数量
     */
    private int size;

    /**
     * 列表当前分配的存储容量
     */
    private int capacity;

    /**
     * 存储列表元素的数组缓冲区
     */
    private Object[] elements;

    /**
     * 构造一个初始容量为10的空列表
     */
    public MyArrayList() {
        this(10);
    }

    /**
     * 构造具有指定初始容量的空列表
     *
     * @param initialCapacity 初始容量
     * @throws IllegalArgumentException 如果初始容量为负数
     */
    public MyArrayList(int initialCapacity) {
        if (initialCapacity < 0) {
            throw new IllegalArgumentException("容量必须>0" + initialCapacity);
        }
        this.capacity = initialCapacity;
        this.elements = new Object[initialCapacity];
    }

    /**
     * 将指定元素追加到列表末尾
     *
     * @param element 要添加的元素
     */
    @Override
    public void add(E element) {
        ensureCapacity();
        elements[size++] = element;
    }

    /**
     * 在列表的指定位置插入指定元素
     *
     * @param element 要插入的元素
     * @param index 插入位置的索引
     * @throws IndexOutOfBoundsException 如果索引超出范围
     */
    @Override
    public void add(E element, int index) {
        rangeCheckForAdd(index);
        ensureCapacity();
        System.arraycopy(elements, index, elements, index + 1, size - index);
        elements[index] = element;
        size++;
    }

    /**
     * 移除并返回列表中指定位置的元素
     *
     * @param index 要移除元素的索引
     * @return 被移除的元素
     * @throws IndexOutOfBoundsException 如果索引超出范围
     */
    @Override
    public E remove(int index) {
        rangeCheck(index);
        E oldValue = elementData(index);
        int numMoved = size - index - 1;
        if (numMoved > 0) {
            System.arraycopy(elements, index + 1, elements, index, numMoved);
        }
        elements[--size] = null; // 清除引用，帮助GC
        return oldValue;
    }

    /**
     * 从列表中移除首次出现的指定元素
     *
     * @param element 要移除的元素
     * @return 如果列表包含该元素则返回true
     */
    @Override
    public boolean remove(E element) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(element, elements[i])) {
                remove(i);
                return true;
            }
        }
        return false;
    }

    /**
     * 替换列表中指定位置的元素
     *
     * @param index 要替换元素的索引
     * @param element 要存储的元素
     * @return 先前在指定位置的元素
     * @throws IndexOutOfBoundsException 如果索引超出范围
     */
    @Override
    public E set(int index, E element) {
        rangeCheck(index);
        E oldValue = elementData(index);
        elements[index] = element;
        return oldValue;
    }

    /**
     * 返回列表中指定位置的元素
     *
     * @param index 要返回元素的索引
     * @return 列表中指定位置的元素
     * @throws IndexOutOfBoundsException 如果索引超出范围
     */
    @Override
    public E get(int index) {
        rangeCheck(index);
        return elementData(index);
    }

    /**
     * 返回列表中的元素数量
     *
     * @return 列表中的元素数量
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * 返回此列表中元素的迭代器
     *
     * @return 按适当顺序包含此列表中所有元素的迭代器
     */
    @Override
    public Iterator<E> iterator() {
        return new ArrayListIterator();
    }

    // ========== 私有辅助方法 ==========

    /**
     * 确保列表有足够的容量容纳新元素
     */
    private void ensureCapacity() {
        if (size == capacity) {
            resize();
        }
    }

    /**
     * 扩容列表存储容量
     */
    private void resize() {
        int newCapacity = capacity * 2;
        Object[] newElements = new Object[newCapacity];
        System.arraycopy(elements, 0, newElements, 0, size);
        elements = newElements;
        capacity = newCapacity;
    }

    /**
     * 检查索引是否在有效范围内
     */
    private void rangeCheck(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
        }
    }

    /**
     * 检查添加操作的索引是否在有效范围内
     */
    private void rangeCheckForAdd(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
        }
    }

    /**
     * 构造索引越界异常信息
     */
    private String outOfBoundsMsg(int index) {
        return "Index: " + index + ", Size: " + size;
    }

    /**
     * 获取指定位置的元素（带类型转换）
     */
    @SuppressWarnings("unchecked")
    private E elementData(int index) {
        return (E) elements[index];
    }

    // ========== 内部迭代器实现 ==========

    /**
     * 列表迭代器实现
     */
    private class ArrayListIterator implements Iterator<E> {
        /**
         * 当前迭代位置
         */
        private int cursor;

        /**
         * 检查是否还有更多元素可迭代
         *
         * @return 如果迭代有更多元素则返回true
         */
        @Override
        public boolean hasNext() {
            return cursor != size;
        }

        /**
         * 返回迭代中的下一个元素
         *
         * @return 迭代中的下一个元素
         * @throws NoSuchElementException 如果迭代没有更多元素
         */
        @Override
        public E next() {
            if (cursor >= size) {
                throw new NoSuchElementException();
            }
            return elementData(cursor++);
        }
    }
}