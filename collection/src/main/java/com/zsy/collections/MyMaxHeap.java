package com.zsy.collections;

/**
 * 自定义最大堆实现
 *
 * <p>本实现提供基于数组的最大堆数据结构，支持以下操作：</p>
 * <ul>
 *   <li>插入元素：O(log n)</li>
 *   <li>获取最大值：O(1)</li>
 *   <li>删除最大值：O(log n)</li>
 *   <li>动态扩容：当堆满时自动扩容</li>
 * </ul>
 *
 * <p><b>堆性质：</b>每个节点的值都大于或等于其子节点的值</p>
 *
 * <p><b>实现说明：</b></p>
 * <ul>
 *   <li>使用数组存储完全二叉树</li>
 *   <li>默认初始容量：10</li>
 *   <li>自动扩容策略：容量不足时抛出异常（可改进为自动扩容）</li>
 *   <li>非线程安全</li>
 * </ul>
 */
public class MyMaxHeap {
    /**
     * 存储堆元素的数组
     */
    private int[] heap;

    /**
     * 堆中当前元素数量
     */
    private int size;

    /**
     * 堆的最大容量
     */
    private int capacity;

    /**
     * 默认初始容量
     */
    private static final int DEFAULT_CAPACITY = 10;

    /**
     * 构造一个初始容量为10的空堆
     */
    public MyMaxHeap() {
        this(DEFAULT_CAPACITY);
    }

    /**
     * 构造指定初始容量的空堆
     * @param initialCapacity 初始容量（必须大于0）
     * @throws IllegalArgumentException 如果初始容量不合法
     */
    public MyMaxHeap(int initialCapacity) {
        if (initialCapacity <= 0) {
            throw new IllegalArgumentException("Initial capacity must be positive");
        }
        this.capacity = initialCapacity;
        this.heap = new int[capacity];
        this.size = 0;
    }

    /**
     * 获取堆中元素数量
     * @return 堆大小
     */
    public int getSize() {
        return size;
    }

    /**
     * 向堆中插入新元素
     * @param element 要插入的元素
     * @throws IndexOutOfBoundsException 如果堆已满
     */
    public void insert(int element) {
        if (size == capacity) {
            throw new IndexOutOfBoundsException("Heap is full");
        }
        heap[size] = element;
        heapifyUp(size);
        size++;
    }

    /**
     * 获取堆中最大值（不删除）
     * @return 堆顶元素（最大值）
     * @throws IndexOutOfBoundsException 如果堆为空
     */
    public int getMax() {
        if (size == 0) {
            throw new IndexOutOfBoundsException("Heap is empty");
        }
        return heap[0];
    }

    /**
     * 删除并返回堆中最大值
     * @return 堆顶元素（最大值）
     * @throws IndexOutOfBoundsException 如果堆为空
     */
    public int deleteMax() {
        if (size == 0) {
            throw new IndexOutOfBoundsException("Heap is empty");
        }
        int max = heap[0];
        heap[0] = heap[size - 1];
        size--;
        heapifyDown(0);
        return max;
    }

    // ========== 私有辅助方法 ==========

    /**
     * 获取父节点索引
     * @param index 当前节点索引
     * @return 父节点索引
     */
    private int parent(int index) {
        return (index - 1) / 2;
    }

    /**
     * 获取左子节点索引
     * @param index 当前节点索引
     * @return 左子节点索引
     */
    private int leftChild(int index) {
        return 2 * index + 1;
    }

    /**
     * 获取右子节点索引
     * @param index 当前节点索引
     * @return 右子节点索引
     */
    private int rightChild(int index) {
        return 2 * index + 2;
    }

    /**
     * 交换堆中两个元素
     * @param i 第一个元素索引
     * @param j 第二个元素索引
     */
    private void swap(int i, int j) {
        int temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    /**
     * 从指定位置开始向上调整堆
     * @param index 开始调整的位置
     */
    private void heapifyUp(int index) {
        while (index > 0 && heap[parent(index)] < heap[index]) {
            swap(parent(index), index);
            index = parent(index);
        }
    }

    /**
     * 从指定位置开始向下调整堆
     * @param index 开始调整的位置
     */
    private void heapifyDown(int index) {
        int largest = index;
        int left = leftChild(index);
        int right = rightChild(index);

        if (left < size && heap[left] > heap[largest]) {
            largest = left;
        }

        if (right < size && heap[right] > heap[largest]) {
            largest = right;
        }

        if (largest != index) {
            swap(index, largest);
            heapifyDown(largest);
        }
    }
}