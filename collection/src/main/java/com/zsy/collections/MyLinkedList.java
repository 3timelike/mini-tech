package com.zsy.collections;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * 自定义双向链表实现，支持泛型元素存储和基本列表操作
 *
 * <p>本实现提供类似Java LinkedList的核心功能，包括：</p>
 * <ul>
 *   <li>基于节点的双向链表结构</li>
 *   <li>支持头部和尾部高效操作</li>
 *   <li>提供元素遍历功能</li>
 *   <li>实现基本的增删改查操作</li>
 * </ul>
 *
 * <p><b>特性说明：</b></p>
 * <ul>
 *   <li>时间复杂度：访问O(n)，头尾操作O(1)</li>
 *   <li>空间复杂度：O(n)</li>
 *   <li><b>非线程安全</b> - 多线程环境下需要外部同步</li>
 * </ul>
 *
 * @param <E> 列表元素类型
 * @author zsy
 * @see List
 */
public class MyLinkedList<E> implements List<E> {
    /**
     * 当前列表中元素的数量
     */
    private int size;

    /**
     * 链表头节点
     */
    private Node<E> head;

    /**
     * 链表尾节点
     */
    private Node<E> tail;

    /**
     * 将指定元素追加到列表末尾
     *
     * @param element 要添加的元素
     */
    @Override
    public void add(E element) {
        Node<E> newNode = new Node<>(tail, element, null);
        if (tail != null) {
            tail.next = newNode;
        } else {
            head = newNode;
        }
        tail = newNode;
        size++;
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

        if (index == size) {
            add(element);
            return;
        }

        Node<E> target = getNode(index);
        Node<E> prev = target.pre;
        Node<E> newNode = new Node<>(prev, element, target);

        if (prev == null) {
            head = newNode;
        } else {
            prev.next = newNode;
        }
        target.pre = newNode;
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
        return unlink(getNode(index));
    }

    /**
     * 从列表中移除首次出现的指定元素
     *
     * @param element 要移除的元素
     * @return 如果列表包含该元素则返回true
     */
    @Override
    public boolean remove(E element) {
        for (Node<E> x = head; x != null; x = x.next) {
            if (Objects.equals(element, x.value)) {
                unlink(x);
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
        Node<E> node = getNode(index);
        E oldVal = node.value;
        node.value = element;
        return oldVal;
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
        return getNode(index).value;
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
        return new LinkedListIterator();
    }

    // ========== 私有辅助方法 ==========

    /**
     * 获取指定索引处的节点
     */
    private Node<E> getNode(int index) {
        if (index < (size >> 1)) {
            Node<E> x = head;
            for (int i = 0; i < index; i++)
                x = x.next;
            return x;
        } else {
            Node<E> x = tail;
            for (int i = size - 1; i > index; i--)
                x = x.pre;
            return x;
        }
    }

    /**
     * 从链表中移除指定节点
     */
    private E unlink(Node<E> node) {
        final E element = node.value;
        final Node<E> next = node.next;
        final Node<E> prev = node.pre;

        if (prev == null) {
            head = next;
        } else {
            prev.next = next;
            node.pre = null;
        }

        if (next == null) {
            tail = prev;
        } else {
            next.pre = prev;
            node.next = null;
        }

        node.value = null;
        size--;
        return element;
    }

    /**
     * 检查索引是否在有效范围内
     */
    private void rangeCheck(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }

    /**
     * 检查添加操作的索引是否在有效范围内
     */
    private void rangeCheckForAdd(int index) {
        if (index < 0 || index > size)
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }

    /**
     * 构造索引越界异常信息
     */
    private String outOfBoundsMsg(int index) {
        return "Index: "+index+", Size: "+size;
    }

    // ========== 内部类实现 ==========

    /**
     * 双向链表节点实现
     */
    private static class Node<E> {
        /**
         * 前驱节点
         */
        Node<E> pre;

        /**
         * 后继节点
         */
        Node<E> next;

        /**
         * 节点存储的值
         */
        E value;

        Node(Node<E> pre, E value, Node<E> next) {
            this.pre = pre;
            this.value = value;
            this.next = next;
        }
    }

    /**
     * 链表迭代器实现
     */
    private class LinkedListIterator implements Iterator<E> {
        /**
         * 当前迭代节点
         */
        private Node<E> current = head;

        /**
         * 检查是否还有更多元素可迭代
         *
         * @return 如果迭代有更多元素则返回true
         */
        @Override
        public boolean hasNext() {
            return current != null;
        }

        /**
         * 返回迭代中的下一个元素
         *
         * @return 迭代中的下一个元素
         * @throws NoSuchElementException 如果迭代没有更多元素
         */
        @Override
        public E next() {
            if (!hasNext())
                throw new NoSuchElementException();
            E element = current.value;
            current = current.next;
            return element;
        }
    }
}