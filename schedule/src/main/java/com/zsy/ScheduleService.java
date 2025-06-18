package com.zsy;




import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.locks.LockSupport;

/**
 * @author zsy
 **/
public class ScheduleService {


    Trigger trigger = new Trigger();

    ExecutorService executorService = Executors.newFixedThreadPool(6);

    void schedule(Runnable task, long delay) {
        Job job = new Job();
        job.setTask(task);
        job.setStartTime(System.currentTimeMillis() + delay);
        job.setDelay(delay);
        trigger.queue.offer(job);
        trigger.wakeUp();
    }

    //  等待合适的时间，把对应的任务扔到线程池中
    class Trigger {

        PriorityBlockingQueue<Job> queue = new PriorityBlockingQueue<>();

        Thread thread = new Thread(() -> {
            while (true) {
                while (queue.isEmpty()) {
                    LockSupport.park();
                }
                Job latelyJob = queue.peek();
                if (latelyJob.getStartTime() < System.currentTimeMillis()) {
                    latelyJob = queue.poll();
                    executorService.execute(latelyJob.getTask());
                    Job nextJob = new Job();
                    nextJob.setTask(latelyJob.getTask());
                    nextJob.setDelay(latelyJob.getDelay());
                    nextJob.setStartTime(System.currentTimeMillis() + latelyJob.getDelay());
                    queue.offer(nextJob);
                } else {
                    LockSupport.parkUntil(latelyJob.getStartTime());
                }
            }
        });

        {
            thread.start();
            System.out.println("触发器启动了!");
        }

        void wakeUp() {
            LockSupport.unpark(thread);
        }
    }


}

