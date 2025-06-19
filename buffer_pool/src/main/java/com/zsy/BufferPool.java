package com.zsy;


import java.nio.ByteBuffer;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 字节缓冲区内存池实现类
 *
 * <p>本类实现了一个线程安全的字节缓冲区内存池，支持固定大小的槽位分配和释放，
 * 并提供带超时机制的缓冲区申请功能。</p>
 *
 * @author gongxuanzhangmelt@gmail.com
 */
public class BufferPool {

    /**
     * 内存池总容量（字节）
     */
    private final int totalSize;

    /**
     * 每个槽位的大小（字节）
     */
    private final int slotSize;

    /**
     * 当前空闲内存大小（字节）
     */
    private int free;

    /**
     * 可用槽位队列（先进先出）
     */
    private final Deque<ByteBuffer> slotQueue = new ArrayDeque<>();

    /**
     * 等待队列（存储等待内存分配的线程条件）
     */
    private final Deque<Condition> waiters = new ArrayDeque<>();

    /**
     * 可重入锁，用于线程同步
     */
    private final Lock lock = new ReentrantLock();

    /**
     * 构造函数
     *
     * @param totalSize 内存池总容量（字节）
     * @param slotSize 每个槽位的大小（字节）
     * @throws IllegalArgumentException 如果参数不合法（小于等于0）
     */
    public BufferPool(int totalSize, int slotSize) {
        if (totalSize <= 0 || slotSize <= 0) {
            throw new IllegalArgumentException("内存池大小和槽位大小必须大于0");
        }
        this.totalSize = totalSize;
        this.slotSize = slotSize;
        this.free = totalSize;
    }

    /**
     * 申请指定大小的字节缓冲区
     *
     * @param size 请求的缓冲区大小（字节）
     * @param timeout 最大等待时间（毫秒）
     * @return 分配到的ByteBuffer对象
     * @throws InterruptedException 如果线程在等待时被中断
     * @throws RuntimeException 如果请求大小不合法或超时未分配到内存
     */
    public ByteBuffer allocate(int size, long timeout) throws InterruptedException {
        // 方法实现...
        if (size > totalSize || size <= 0) {
            throw new RuntimeException("你的申请容量异常" + size);
        }
        lock.lock();
        try {
            if (size == slotSize && !slotQueue.isEmpty()) {
                return slotQueue.pollFirst();
            }
            if ((free + slotQueue.size() * slotSize) >= size) {
                freeUp(size);
                free -= size;
                return ByteBuffer.allocate(size);
            }
            Condition condition = lock.newCondition();
            waiters.addLast(condition);
            long remainTime = timeout;
            try {
                while (true) {
                    long start = System.currentTimeMillis();
                    boolean wakeup = condition.await(remainTime, TimeUnit.MILLISECONDS);
                    if (!wakeup) {
                        throw new RuntimeException("规定时间内不能申请需要的内存");
                    }
                    if (size == slotSize && !slotQueue.isEmpty()) {
                        return slotQueue.pollFirst();
                    }
                    if ((free + slotQueue.size() * slotSize) >= size) {
                        freeUp(size);
                        free -= size;
                        return ByteBuffer.allocate(size);
                    }
                    remainTime -= System.currentTimeMillis() - start;
                }
            } finally {
                waiters.remove(condition);
            }

        } finally {
            if (!waiters.isEmpty() && !(free == 0 && slotQueue.isEmpty())) {
                waiters.peekFirst().signal();
            }
            lock.unlock();
        }
    }

    /**
     * 释放字节缓冲区到内存池
     *
     * @param byteBuffer 要释放的ByteBuffer对象
     * @throws NullPointerException 如果参数为null
     */
    public void deallocate(ByteBuffer byteBuffer) {
        // 方法实现...
        lock.lock();
        try {
            if (byteBuffer.capacity() == this.slotSize) {
                byteBuffer.clear();
                this.slotQueue.addLast(byteBuffer);
            } else {
                free += byteBuffer.capacity();
            }
            if (!waiters.isEmpty()) {
                waiters.peekFirst().signal();
            }
        } finally {
            lock.unlock();
        }
    }

    /**
     * 内部方法：释放足够的内存以满足请求大小
     *
     * @param size 需要的内存大小
     */
    private void freeUp(int size) {
        // 方法实现...
        while (free < size && !slotQueue.isEmpty()) {
            free += slotQueue.pollFirst().capacity();
        }
    }
}