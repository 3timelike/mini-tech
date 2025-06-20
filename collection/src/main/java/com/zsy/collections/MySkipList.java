package com.zsy.collections;

import java.util.Random;

/**
 * 自定义跳表(SkipList)实现
 *
 * <p>跳表是一种概率平衡数据结构，可替代平衡树，提供O(log n)的查询/插入/删除复杂度</p>
 *
 * <p><b>实现特性：</b></p>
 * <ul>
 *   <li>最大层级：16</li>
 *   <li>晋升概率P：0.5</li>
 *   <li>支持整型数据存储</li>
 *   <li>提供查找、插入、删除基本操作</li>
 * </ul>
 *
 * <p><b>时间复杂度：</b></p>
 * <ul>
 *   <li>查找：平均O(log n)，最坏O(n)</li>
 *   <li>插入：平均O(log n)，最坏O(n)</li>
 *   <li>删除：平均O(log n)，最坏O(n)</li>
 * </ul>
 */
public class MySkipList {
    private static final int MAX_LEVEL = 16;
    private static final double P = 0.5;
    private int level;
    private SkipListNode header;
    private Random random;

    /**
     * 构造一个空跳表
     */
    public MySkipList() {
        this.level = 1;
        this.header = new SkipListNode(Integer.MIN_VALUE, MAX_LEVEL);
        this.random = new Random();
    }

    /**
     * 查找元素是否存在
     * @param value 要查找的值
     * @return 如果存在返回true，否则false
     */
    public boolean search(int value) {
        SkipListNode current = header;
        // 从最高层开始查找
        for (int i = level - 1; i >= 0; i--) {
            while (current.forward[i] != null && current.forward[i].value < value) {
                current = current.forward[i];
            }
        }
        current = current.forward[0];
        return current != null && current.value == value;
    }

    /**
     * 插入元素到跳表
     * @param value 要插入的值
     */
    public void insert(int value) {
        SkipListNode[] update = new SkipListNode[MAX_LEVEL];
        SkipListNode current = header;

        // 查找各层的插入位置
        for (int i = level - 1; i >= 0; i--) {
            while (current.forward[i] != null && current.forward[i].value < value) {
                current = current.forward[i];
            }
            update[i] = current;
        }

        current = current.forward[0];

        // 如果值不存在则插入
        if (current == null || current.value != value) {
            int newLevel = randomLevel();
            // 如果新节点层级高于当前层级，更新高层级指针
            if (newLevel > level) {
                for (int i = level; i < newLevel; i++) {
                    update[i] = header;
                }
                level = newLevel;
            }

            // 创建新节点并更新指针
            current = new SkipListNode(value, newLevel);
            for (int i = 0; i < newLevel; i++) {
                current.forward[i] = update[i].forward[i];
                update[i].forward[i] = current;
            }
        }
    }

    /**
     * 从跳表删除元素
     * @param value 要删除的值
     */
    public void delete(int value) {
        SkipListNode[] update = new SkipListNode[MAX_LEVEL];
        SkipListNode current = header;

        // 查找各层需要更新的节点
        for (int i = level - 1; i >= 0; i--) {
            while (current.forward[i] != null && current.forward[i].value < value) {
                current = current.forward[i];
            }
            update[i] = current;
        }

        current = current.forward[0];

        // 如果找到则删除
        if (current != null && current.value == value) {
            for (int i = 0; i < level; i++) {
                if (update[i].forward[i] != current) {
                    break;
                }
                update[i].forward[i] = current.forward[i];
            }

            // 降低跳表层级
            while (level > 1 && header.forward[level - 1] == null) {
                level--;
            }
        }
    }

    /**
     * 随机生成节点层级
     * @return 新节点的层级
     */
    private int randomLevel() {
        int level = 1;
        while (random.nextDouble() < P && level < MAX_LEVEL) {
            level++;
        }
        return level;
    }

    /**
     * 跳表节点类
     */
    private static class SkipListNode {
        final int value;
        final SkipListNode[] forward;

        SkipListNode(int value, int level) {
            this.value = value;
            this.forward = new SkipListNode[level];
        }
    }
}