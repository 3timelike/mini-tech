package com.zsy;


/**
 * 由调用者线程直接执行被拒绝的任务
 */
public class CallerRunsPolicy implements RejectHandle {
    @Override
    public void reject(Runnable rejectCommand, MyThreadLocalPool threadPool) {
        if (!threadPool.blockingQueue.offer(rejectCommand)) {
            System.out.println("线程池已满，由调用者线程直接执行任务[" + rejectCommand + "]");
            rejectCommand.run();
        }
    }
}
