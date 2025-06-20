package com.zsy.collections;

/**
 * 自定义HashMap实现，基于数组+链表的结构
 *
 * <p>本实现提供类似Java HashMap的核心功能，包括：</p>
 * <ul>
 *   <li>基于哈希表的键值对存储</li>
 *   <li>使用链表解决哈希冲突</li>
 *   <li>自动扩容机制</li>
 *   <li>基本CRUD操作</li>
 * </ul>
 *
 * <p><b>特性说明：</b></p>
 * <ul>
 *   <li>初始容量：16</li>
 *   <li>负载因子：0.75</li>
 *   <li>扩容策略：容量翻倍</li>
 *   <li>哈希计算：key.hashCode() & (capacity-1)</li>
 *   <li><b>非线程安全</b> - 多线程环境下需要外部同步</li>
 * </ul>
 *
 * @param <K> 键类型
 * @param <V> 值类型
 * @author zsy
 */
public class MyHashMap<K, V> {

    /**
     * 默认初始容量 - 必须是2的幂
     */
    private static final int DEFAULT_INITIAL_CAPACITY = 16;

    /**
     * 默认负载因子
     */
    private static final float DEFAULT_LOAD_FACTOR = 0.75f;

    /**
     * 哈希表数组
     */
    private Node<K, V>[] table;

    /**
     * map中键值对的数量
     */
    private int size;

    /**
     * 构造一个具有默认初始容量(16)和默认负载因子(0.75)的空HashMap
     */
    public MyHashMap() {
        this.table = new Node[DEFAULT_INITIAL_CAPACITY];
    }

    /**
     * 将指定的键值对存入map
     *
     * @param key 键
     * @param value 值
     * @return 与key关联的旧值，如果没有则返回null
     */
    public V put(K key, V value) {
        int index = hash(key);
        Node<K, V> head = table[index];

        // 查找是否已存在相同key
        while (head != null) {
            if (head.key.equals(key)) {
                V oldValue = head.value;
                head.value = value;
                return oldValue;
            }
            head = head.next;
        }

        // 添加新节点
        Node<K, V> newNode = new Node<>(key, value);
        newNode.next = table[index];
        table[index] = newNode;
        size++;

        // 检查是否需要扩容
        resizeIfNeeded();
        return null;
    }

    /**
     * 获取指定键对应的值
     *
     * @param key 键
     * @return 与key关联的值，如果没有则返回null
     */
    public V get(K key) {
        int index = hash(key);
        Node<K, V> node = table[index];
        while (node != null) {
            if (node.key.equals(key)) {
                return node.value;
            }
            node = node.next;
        }
        return null;
    }

    /**
     * 移除指定键对应的键值对
     *
     * @param key 键
     * @return 被移除的值，如果没有则返回null
     */
    public V remove(K key) {
        int index = hash(key);
        Node<K, V> node = table[index];
        Node<K, V> prev = null;

        while (node != null) {
            if (node.key.equals(key)) {
                if (prev == null) {
                    table[index] = node.next;
                } else {
                    prev.next = node.next;
                }
                size--;
                return node.value;
            }
            prev = node;
            node = node.next;
        }
        return null;
    }

    /**
     * 返回map中键值对的数量
     *
     * @return map中键值对的数量
     */
    public int size() {
        return size;
    }

    /**
     * 计算key的哈希索引
     */
    private int hash(Object key) {
        return key.hashCode() & (table.length - 1);
    }

    /**
     * 检查并执行扩容操作
     */
    private void resizeIfNeeded() {
        if (size < table.length * DEFAULT_LOAD_FACTOR) {
            return;
        }

        int newCapacity = table.length << 1;
        Node<K, V>[] newTable = new Node[newCapacity];

        // 重新哈希所有节点
        for (Node<K, V> head : table) {
            while (head != null) {
                Node<K, V> next = head.next;
                int newIndex = head.key.hashCode() & (newCapacity - 1);
                head.next = newTable[newIndex];
                newTable[newIndex] = head;
                head = next;
            }
        }

        table = newTable;
    }

    /**
     * HashMap节点类
     */
    static class Node<K, V> {
        final K key;
        V value;
        Node<K, V> next;

        Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}