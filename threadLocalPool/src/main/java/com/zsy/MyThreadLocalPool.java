package com.zsy;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 自定义线程池（增加shutdown方法）
 */
public class MyThreadLocalPool {
    private final int corePoolSize;
    private final int maxSize;
    private final int timeout;
    private final TimeUnit timeUnit;
    public final BlockingQueue<Runnable> blockingQueue;
    private final RejectHandle rejectHandle;

    private final List<Thread> coreList = new ArrayList<>();
    private final List<Thread> supportList = new ArrayList<>();
    private final AtomicBoolean isShutdown = new AtomicBoolean(false);

    public MyThreadLocalPool(int corePoolSize, int maxSize, int timeout, TimeUnit timeUnit,
                             BlockingQueue<Runnable> blockingQueue, RejectHandle rejectHandle) {
        this.corePoolSize = corePoolSize;
        this.maxSize = maxSize;
        this.timeout = timeout;
        this.timeUnit = timeUnit;
        this.blockingQueue = blockingQueue;
        this.rejectHandle = rejectHandle;
    }

    void execute(Runnable command) {
        if (isShutdown.get()) {
            throw new IllegalStateException("ThreadPool already shutdown");
        }

        if (coreList.size() < corePoolSize) {
            Thread thread = new CoreThread(command);
            coreList.add(thread);
            thread.start();
            return;
        }
        if (blockingQueue.offer(command)) {
            return;
        }
        if (coreList.size() + supportList.size() < maxSize) {
            Thread thread = new SupportThread(command);
            supportList.add(thread);
            thread.start();
            return;
        }
        rejectHandle.reject(command, this);
    }

    /**
     * 优雅关闭线程池：
     * - 不再接受新任务
     * - 已提交的任务会继续执行
     * - 不会强制中断正在执行的任务
     */
    public void shutdown() {
        isShutdown.set(true);
        // 仅中断空闲的核心线程（非核心线程会自动退出）
        for (Thread t : coreList) {
            if (t.getState() == Thread.State.WAITING) {
                t.interrupt();
            }
        }
    }

    /**
     * 立即关闭线程池：
     * - 不再接受新任务
     * - 尝试中断所有线程
     * - 返回未执行的任务队列
     */
    public List<Runnable> shutdownNow() {
        isShutdown.set(true);

        // 中断所有线程
        for (Thread t : coreList) {
            t.interrupt();
        }
        for (Thread t : supportList) {
            t.interrupt();
        }

        // 返回未处理的任务
        List<Runnable> remainingTasks = new ArrayList<>();
        blockingQueue.drainTo(remainingTasks);
        return remainingTasks;
    }

    class CoreThread extends Thread {
        private final Runnable firstTask;

        public CoreThread(Runnable firstTask) {
            this.firstTask = firstTask;
        }

        @Override
        public void run() {
            try {
                firstTask.run();
                while (!isShutdown.get() || !blockingQueue.isEmpty()) {
                    Runnable command = blockingQueue.take();
                    command.run();
                }
            } catch (InterruptedException e) {
                // 响应关闭请求
            } finally {
                coreList.remove(this);
            }
        }
    }

    class SupportThread extends Thread {
        private final Runnable firstTask;

        public SupportThread(Runnable firstTask) {
            this.firstTask = firstTask;
        }

        @Override
        public void run() {
            try {
                firstTask.run();
                while (!isShutdown.get()) {
                    Runnable command = blockingQueue.poll(timeout, timeUnit);
                    if (command == null) {
                        break; // 超时自动退出
                    }
                    command.run();
                }
            } catch (InterruptedException e) {
                // 响应立即关闭请求
            } finally {
                supportList.remove(this);
                System.out.println(Thread.currentThread().getName() + "线程结束");
            }
        }
    }
}