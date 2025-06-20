import com.zsy.collections.*;
import org.junit.Test;
import static org.junit.Assert.*;

public class TestCollection {

    @Test
    public void testMyArrayList() {
        // 初始化测试
        MyArrayList<Integer> list = new MyArrayList<>();
        assertEquals(0, list.size());

        // 测试添加和获取
        list.add(10);
        list.add(20);
        list.add(30, 1); // 在中间插入

        assertEquals(3, list.size());
        assertEquals(Integer.valueOf(10), list.get(0));
        assertEquals(Integer.valueOf(30), list.get(1));
        assertEquals(Integer.valueOf(20), list.get(2));

        // 测试修改
        assertEquals(Integer.valueOf(30), list.set(1, 40));
        assertEquals(Integer.valueOf(40), list.get(1));

        // 测试迭代器
        int sum = 0;
        for (Integer num : list) {
            sum += num;
        }
        assertEquals(70, sum);

        // 测试删除
        assertEquals(Integer.valueOf(40), list.remove(1));
        assertEquals(2, list.size());

        // 测试边界条件
        try {
            list.get(5);
            fail("Expected IndexOutOfBoundsException");
        } catch (IndexOutOfBoundsException e) {
            // 预期异常
        }

        // 测试扩容
        for (int i = 0; i < 100; i++) {
            list.add(i);
        }
        assertEquals(102, list.size());
    }

    @Test
    public void testMyLinkedList() {
        // 初始化测试
        MyLinkedList<String> list = new MyLinkedList<>();
        assertEquals(0, list.size());

        // 测试添加和获取
        list.add("first");
        list.add("third");
        list.add("second", 1); // 在中间插入

        assertEquals(3, list.size());
        assertEquals("first", list.get(0));
        assertEquals("second", list.get(1));
        assertEquals("third", list.get(2));

        // 测试修改
        assertEquals("second", list.set(1, "middle"));
        assertEquals("middle", list.get(1));

        // 测试迭代器
        StringBuilder sb = new StringBuilder();
        for (String str : list) {
            sb.append(str).append("-");
        }
        assertEquals("first-middle-third-", sb.toString());

        // 测试删除
        assertTrue(list.remove("middle"));
        assertEquals(2, list.size());
        assertEquals("third", list.remove(1));
        assertEquals(1, list.size());

        // 测试边界条件
        try {
            list.get(5);
            fail("Expected IndexOutOfBoundsException");
        } catch (IndexOutOfBoundsException e) {
            // 预期异常
        }

        // 测试头尾操作
        list.add("newFirst", 0);
        list.add("newLast");
        assertEquals("newFirst", list.get(0));
        assertEquals("newLast", list.get(2));
    }

    @Test
    public void testMyHashMap() {
        // 初始化测试
        MyHashMap<String, Integer> map = new MyHashMap<>();
        assertEquals(0, map.size());

        // 测试基本put/get
        map.put("one", 1);
        map.put("two", 2);
        map.put("three", 3);

        assertEquals(3, map.size());
        assertEquals(Integer.valueOf(1), map.get("one"));
        assertEquals(Integer.valueOf(2), map.get("two"));

        // 测试更新值
        assertEquals(Integer.valueOf(3), map.put("three", 33));
        assertEquals(Integer.valueOf(33), map.get("three"));

        // 测试删除
        assertEquals(Integer.valueOf(2), map.remove("two"));
        assertNull(map.get("two"));
        assertEquals(2, map.size());

        // 测试不存在的key
        assertNull(map.get("nonexistent"));
        assertNull(map.remove("nonexistent"));

        // 测试扩容
        for (int i = 0; i < 20; i++) {
            map.put("key" + i, i);
        }
        assertEquals(22, map.size());
        assertEquals(Integer.valueOf(15), map.get("key15"));

        // 测试哈希冲突
        // 创建两个hashCode相同但equals不同的对象
        class SameHash {
            final int value;
            SameHash(int v) { this.value = v; }
            @Override public int hashCode() { return 42; }
            @Override public boolean equals(Object o) {
                return (o instanceof SameHash) && ((SameHash)o).value == this.value;
            }
        }

        MyHashMap<SameHash, String> collisionMap = new MyHashMap<>();
        collisionMap.put(new SameHash(1), "first");
        collisionMap.put(new SameHash(2), "second");
        assertEquals("first", collisionMap.get(new SameHash(1)));
        assertEquals("second", collisionMap.get(new SameHash(2)));
    }
    @Test
    public void testMaxHeapOperations() {
        MyMaxHeap heap = new MyMaxHeap();

        // 测试插入和获取最大值
        heap.insert(10);
        heap.insert(30);
        heap.insert(20);
        assertEquals(30, heap.getMax());

        // 测试删除最大值
        assertEquals(30, heap.deleteMax());
        assertEquals(20, heap.getMax());

        // 测试堆大小
        assertEquals(2, heap.getSize());

        // 测试连续删除
        assertEquals(20, heap.deleteMax());
        assertEquals(10, heap.deleteMax());
        assertEquals(0, heap.getSize());

        // 测试空堆异常
        try {
            heap.getMax();
            fail("Expected IndexOutOfBoundsException");
        } catch (IndexOutOfBoundsException e) {
            // 预期异常
        }
    }

//    @Test
//    public void testHeapOrderProperty() {
//        MyMaxHeap heap = new MyMaxHeap();
//        int[] elements = {73, 18, 4, 56, 35, 9, 88, 2, 64, 13, 41, 92, 57, 61, 25, 47, 5};
//
//        // 插入所有元素
//        for (int num : elements) {
//            heap.insert(num);
//        }
//
//        // 验证堆顺序性质
//        int prev = Integer.MAX_VALUE;
//        while (heap.getSize() > 0) {
//            int current = heap.deleteMax();
//            assertTrue(current <= prev);
//            prev = current;
//        }
//    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testHeapFullException() {
        MyMaxHeap heap = new MyMaxHeap(3);
        heap.insert(1);
        heap.insert(2);
        heap.insert(3);
        heap.insert(4); // 应该抛出异常
    }
    @Test
    public void testTrieOperations() {
        MyTrie trie = new MyTrie();

        // 测试插入和查找
        trie.insert("apple");
        assertTrue(trie.search("apple"));
        assertFalse(trie.search("app"));

        // 测试前缀匹配
        assertTrue(trie.startsWith("app"));
        trie.insert("app");
        assertTrue(trie.search("app"));

        // 测试不存在的单词
        assertFalse(trie.search("orange"));
        assertFalse(trie.startsWith("ora"));

        // 测试空字符串
        assertFalse(trie.search(""));
        assertTrue(trie.startsWith("")); // 空前缀应该返回true

        // 测试多个单词
        trie.insert("banana");
        trie.insert("band");
        assertTrue(trie.search("banana"));
        assertTrue(trie.startsWith("ban"));
        assertFalse(trie.search("bandit"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidInput() {
        MyTrie trie = new MyTrie();
        trie.insert("Apple"); // 大写字母应该抛出异常
    }

    @Test
    public void testEdgeCases() {
        MyTrie trie = new MyTrie();

        // 测试单字母单词
        trie.insert("a");
        assertTrue(trie.search("a"));
        assertTrue(trie.startsWith("a"));

        // 测试长单词
        String longWord = "abcdefghijklmnopqrstuvwxyz";
        trie.insert(longWord);
        assertTrue(trie.search(longWord));
        assertTrue(trie.startsWith("abcdefg"));
    }
}