package com.hulk.androidstudy.java_base.thread.threadpool;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * Created by tzh on 2020/11/27.
 */
class UseThreadPool {

    //工作线程
    static class Worker implements Runnable {
        private final String taskName;
        private final Random r = new Random();

        public Worker(String taskName) {
            this.taskName = taskName;
        }

        public String getName() {
            return taskName;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " process the task: " + taskName);
            try {
                Thread.sleep(r.nextInt(100) * 5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class CallWorker implements Callable<String> {
        private final String taskName;
        private final Random r = new Random();

        public CallWorker(String taskName) {
            this.taskName = taskName;
        }

        public String getName() {
            return taskName;
        }

        @Override
        public String call() throws InterruptedException {
            Thread.sleep(r.nextInt(1000) * 5);
            System.out.println(Thread.currentThread().getName() + " process the task: " + taskName);
            return Thread.currentThread().getName() + ":" + r.nextInt(100) * 5;
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
        ExecutorService pool = new ThreadPoolExecutor(2, 16,
                3, TimeUnit.SECONDS, new ArrayBlockingQueue<>(10),
                new ThreadPoolExecutor.DiscardOldestPolicy());
        for (int i = 0; i < 6; i++) {
            Worker worker = new Worker("worker_" + i);
            pool.execute(worker);
        }
        for (int i = 0; i < 100; i++) {
            CallWorker callWorker = new CallWorker("worker_" + i);
            Future<String> result = pool.submit(callWorker);
//            System.out.println(result.get(10,TimeUnit.SECONDS));
        }
        pool.shutdown();
    }
}
