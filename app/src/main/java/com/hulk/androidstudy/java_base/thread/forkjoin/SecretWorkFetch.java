package com.hulk.androidstudy.java_base.thread.forkjoin;

import java.util.Random;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * 工作密取
 * Created by tzh on 2020/11/24.
 */
class SecretWorkFetch {
    private static class Work implements Runnable {
        private static Object object = new Object();
        private static int count = 0;
        public final int id;
        private long putThread;

        public Work() {
            synchronized (object) {
                id = count++;
            }
        }

        @Override
        public void run() {
            if (Thread.currentThread().getId() != putThread) {
                System.out.println("========================");
            }
            System.out.println(Thread.currentThread().getId() + ":" + putThread + "// finish job" + id);
        }

        public long getPutThread() {
            return putThread;
        }

        public void setPutThread(long putThread) {
            this.putThread = putThread;
        }
    }

    public static Work generateWork() {
        return new Work();
    }

    private static class ConsumerAndProducer implements Runnable {
        private Random random = new Random();
        private final LinkedBlockingDeque<Work> deque;
        private final LinkedBlockingDeque<Work> otherWork;

        public ConsumerAndProducer(LinkedBlockingDeque<Work> deque, LinkedBlockingDeque<Work> otherWork) {
            this.deque = deque;
            this.otherWork = otherWork;
        }

        @Override
        public void run() {
            while (!Thread.interrupted()) {
                try {
                    Thread.sleep(200);
                    if (random.nextBoolean()) {
                        int count = random.nextInt(5);
                        for (int i = 0; i < count; i++) {
                            Work w = generateWork();
                            w.setPutThread(Thread.currentThread().getId());
                            deque.putLast(w);
                        }
                    }
                    if (deque.isEmpty()) {
                        if (!otherWork.isEmpty()) {
                            System.out.println("otherWork is run:");
                            otherWork.takeLast().run();
                        }
                    } else {
                        deque.takeFirst().run();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        LinkedBlockingDeque<Work> deque = new LinkedBlockingDeque<>();
        LinkedBlockingDeque<Work> other = new LinkedBlockingDeque<>();
        new Thread(new ConsumerAndProducer(deque,other)).start();
        new Thread(new ConsumerAndProducer(deque,other)).start();
    }
}
