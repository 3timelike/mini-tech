package com.zsy.suanfa;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

public class LFUCache<K,V> {
    // 存储键值对
    private final Map<K, V> cache;
    // 存储键的使用频率
    private final Map<K, Integer> keyToFreq;
    // 存储相同频率的键集合（按插入顺序）
    private final Map<Integer, Set<K>> freqToKeys;
    // 缓存容量
    private final int capacity;
    // 当前最小频率
    private int minFreq;

    public LFUCache(int capacity) {
        this.capacity = capacity;
        this.cache = new HashMap<>();
        this.keyToFreq = new HashMap<>();
        this.freqToKeys = new HashMap<>();
        this.minFreq = 0;
    }

    public V get(K key) {
        if (!cache.containsKey(key)) {
            return null;
        }
        // 增加键的使用频率
        increaseFreq(key);
        return cache.get(key);
    }

    public void put(K key, V value) {
        if (capacity <= 0) {
            return;
        }

        // 如果键已存在，更新值并增加频率
        if (cache.containsKey(key)) {
            cache.put(key, value);
            increaseFreq(key);
            return;
        }

        // 如果缓存已满，移除最不经常使用的项
        if (cache.size() >= capacity) {
            removeMinFreqKey();
        }

        // 添加新键值对
        cache.put(key, value);
        keyToFreq.put(key, 1);
        // 将键添加到频率为1的集合中
        freqToKeys.putIfAbsent(1, new LinkedHashSet<>());
        freqToKeys.get(1).add(key);
        // 更新最小频率为新插入的键的频率（1）
        minFreq = 1;
    }

    private void increaseFreq(K key) {
        int freq = keyToFreq.get(key);
        // 更新keyToFreq
        keyToFreq.put(key, freq + 1);
        // 从原频率集合中移除key
        freqToKeys.get(freq).remove(key);
        // 如果原频率集合为空且是最小频率，更新minFreq
        if (freqToKeys.get(freq).isEmpty()) {
            freqToKeys.remove(freq);
            if (freq == minFreq) {
                minFreq++;
            }
        }
        // 将key添加到新频率集合中
        freqToKeys.putIfAbsent(freq + 1, new LinkedHashSet<>());
        freqToKeys.get(freq + 1).add(key);
    }

    private void removeMinFreqKey() {
        // 获取最小频率的键集合
        Set<K> keySet = freqToKeys.get(minFreq);
        // 获取第一个插入的键（LRU策略）
        K deletedKey = keySet.iterator().next();
        // 从各个结构中移除该键
        keySet.remove(deletedKey);
        if (keySet.isEmpty()) {
            freqToKeys.remove(minFreq);
        }
        cache.remove(deletedKey);
        keyToFreq.remove(deletedKey);
    }
}
