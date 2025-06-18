package com.zsy;

/**
 * @author zsy
 **/
public class AbortPolicy implements RejectHandle {
    @Override
    public void reject(Runnable rejectCommand, MyThreadLocalPool threadPool) {
        throw new RuntimeException("阻塞队列满了！");
    }
}
