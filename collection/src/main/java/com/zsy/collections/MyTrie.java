package com.zsy.collections;

/**
 * 自定义Trie（前缀树）实现
 *
 * <p>本实现提供标准的Trie树功能，支持：</p>
 * <ul>
 *   <li>单词插入</li>
 *   <li>单词完整匹配查找</li>
 *   <li>前缀匹配查找</li>
 * </ul>
 *
 * <p><b>特性说明：</b></p>
 * <ul>
 *   <li>仅支持小写字母a-z</li>
 *   <li>空间复杂度：O(26^L)，L为最长单词长度</li>
 *   <li>时间复杂度：
 *     <ul>
 *       <li>插入：O(L)</li>
 *       <li>查找：O(L)</li>
 *       <li>前缀匹配：O(P)，P为前缀长度</li>
 *     </ul>
 *   </li>
 *   <li>非线程安全</li>
 * </ul>
 */
public class MyTrie {
    /**
     * 子节点数组（每个元素对应一个字母）
     */
    private MyTrie[] children;

    /**
     * 标记当前节点是否为单词结尾
     */
    private boolean isWord;

    /**
     * 构造一个空的Trie树
     */
    public MyTrie() {
        this.children = new MyTrie[26]; // 26个小写字母
        this.isWord = false;
    }

    /**
     * 向Trie树中插入一个单词
     * @param word 要插入的单词（只包含小写字母）
     * @throws IllegalArgumentException 如果单词包含非小写字母
     */
    public void insert(String word) {
        MyTrie node = this;
        for (char c : word.toCharArray()) {
            if (c < 'a' || c > 'z') {
                throw new IllegalArgumentException("Only lowercase letters a-z are supported");
            }
            if (node.children[c - 'a'] == null) {
                node.children[c - 'a'] = new MyTrie();
            }
            node = node.children[c - 'a'];
        }
        node.isWord = true;
    }

    /**
     * 检查Trie树中是否包含指定单词
     * @param word 要查找的单词
     * @return 如果单词存在且完整匹配返回true，否则false
     */
    public boolean search(String word) {
        MyTrie node = traverse(word);
        return node != null && node.isWord;
    }

    /**
     * 检查Trie树中是否有以指定前缀开头的单词
     * @param prefix 要检查的前缀
     * @return 如果存在以该前缀开头的单词返回true，否则false
     */
    public boolean startsWith(String prefix) {
        return traverse(prefix) != null;
    }

    /**
     * 遍历Trie树到指定字符串的最后一个字符节点
     * @param s 要遍历的字符串
     * @return 最后一个字符对应的节点，如果路径不存在返回null
     */
    private MyTrie traverse(String s) {
        MyTrie node = this;
        for (char c : s.toCharArray()) {
            if (node.children[c - 'a'] == null) {
                return null;
            }
            node = node.children[c - 'a'];
        }
        return node;
    }
}