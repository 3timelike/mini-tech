package com.zsy;
/**
 * @author zsy
 **/
public class DiscardOldestPolicy implements RejectHandle {
    @Override
    public void reject(Runnable rejectCommand, MyThreadLocalPool threadPool) {
        threadPool.blockingQueue.poll();
        threadPool.execute(rejectCommand);
    }
}
