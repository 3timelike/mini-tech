package com.zsy.suanfa;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {
    static class DLinkedNode {
        int key;
        int value;
        DLinkedNode prev;
        DLinkedNode next;

        public DLinkedNode() {}

        public DLinkedNode(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    public static class LRUCache2 {
        private final Map<Integer, DLinkedNode> cache;
        private final DLinkedNode head;
        private final DLinkedNode tail;
        private final int capacity;
        private int size;

        public LRUCache2(int capacity) {
            this.cache = new HashMap<>();
            this.capacity = capacity;
            this.size = 0;
            head = new DLinkedNode();
            tail = new DLinkedNode();
            head.next = tail;
            tail.prev = head;
        }

        private void moveToHead(DLinkedNode node) {
            removeNode(node);
            addToHead(node);
        }

        private void removeNode(DLinkedNode node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }

        private void addToHead(DLinkedNode node) {
            node.next = head.next;
            node.prev = head;
            head.next.prev = node;
            head.next = node;
        }

        private DLinkedNode removeTail() {
            DLinkedNode node = tail.prev;
            removeNode(node);
            return node;
        }

        public int get(int key) {
            DLinkedNode node = cache.get(key);
            if (node == null) {
                return -1;
            }
            moveToHead(node);
            return node.value;
        }

        public void put(int key, int value) {
            DLinkedNode node = cache.get(key);
            if (node == null) {
                DLinkedNode newNode = new DLinkedNode(key, value);
                cache.put(key, newNode);
                addToHead(newNode);
                size++;
                if (size > capacity) {
                    DLinkedNode removed = removeTail();
                    cache.remove(removed.key);
                    size--;
                }
            } else {
                node.value = value;
                moveToHead(node);
            }
        }
    }
}
