package com.hulk.androidstudy.java_base.thread.tools;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.Exchanger;


/**
 * 演示CyclicExchange用法
 * Created by tzh on 2020/11/25.
 */
class UseExchange {
    private static final Exchanger<Set<String>> exchange = new Exchanger<>();

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Set<String> setA = new HashSet<>();
                try {
                    setA.add("2");
                    setA = exchange.exchange(setA);
                    Iterator<String> iterator = setA.iterator();
                    while (iterator.hasNext()) {
                        System.out.println("setA " + iterator.next());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                Set<String> setB = new HashSet<>();
                try {
                    setB.add("1");
                    setB.add("3");
                    setB = exchange.exchange(setB);
                    Iterator<String> iterator = setB.iterator();
                    while (iterator.hasNext()) {
                        System.out.println("setB " + iterator.next());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
