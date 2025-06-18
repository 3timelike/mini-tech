package com.zsy;

public interface RejectHandle {
    void reject(Runnable rejectCommand, MyThreadLocalPool threadPool);
}
