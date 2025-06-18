package com.zsy;

public class DiscardPolicy implements  RejectHandle {

    @Override
    public void reject(Runnable rejectCommand, MyThreadLocalPool threadPool) {
        System.out.println("该任务被直接丢弃");
    }
}
