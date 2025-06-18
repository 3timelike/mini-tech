package com.zsy;

//TIP 要<b>运行</b>代码，请按 <shortcut actionId="Run"/> 或
// 点击装订区域中的 <icon src="AllIcons.Actions.Execute"/> 图标。

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author zsy
 **/
public class Main {
    public static void main(String[] args) {
        MyThreadLocalPool myThreadPool = new MyThreadLocalPool(2, 4, 1, TimeUnit.SECONDS, new ArrayBlockingQueue<>(2),new DiscardOldestPolicy());
        for (int i = 0; i < 8; i++) {
            final int fi = i;
            myThreadPool.execute(() -> {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(Thread.currentThread().getName() + " " + fi);
            });
        }
        System.out.println("主线程没有被阻塞");

    }
}
