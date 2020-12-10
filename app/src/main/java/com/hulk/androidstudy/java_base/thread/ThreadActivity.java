package com.hulk.androidstudy.java_base.thread;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.hulk.androidstudy.R;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 多线程
 * Created by tzh on 2020/11/19.
 */
public class ThreadActivity extends Activity {
    private Boolean a = false;
    private AtomicInteger atomicInteger;
    private volatile int i;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread);
        findViewById(R.id.tv_thread_one).setOnClickListener(v -> {
            atomicInteger = new AtomicInteger(1);
            i = 0;
            ThreadRunnable threadRunnable = new ThreadRunnable();
            Thread thread1 = new Thread(threadRunnable);
            thread1.start();
            Thread thread2 = new ThreadSubClass();
            thread2.start();
        });
        findViewById(R.id.tv_thread_two).setOnClickListener(v -> {
            Thread thread = new ThreadSubClass();
            thread.start();
        });
    }

    private class ThreadRunnable implements Runnable {
        @Override
        public void run() {
//            synchronized (atomicInteger) {
//                try {
//                    Thread.sleep(2000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                atomicInteger.getAndAdd(1);
//                i = i+1;
            while (i < 10) {
                i++;
                System.out.println("通过实现Runnable接口创建线程" + atomicInteger + "i:" + i);
            }
        }
    }

    private class ThreadSubClass extends Thread {
        @Override
        public void run() {
            super.run();
//            synchronized (atomicInteger) {
//            }
//            while (atomicInteger.get() < 10) {
            while (i < 10) {
//                atomicInteger.getAndAdd(1);
//                i = i+1;
                i++;
                System.out.println("通过继承Thread类创建线程" + atomicInteger + "i:" + i);
//                try {
//                    Thread.sleep(500);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
            }
        }
    }
}
