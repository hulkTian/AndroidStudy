package com.hulk.androidstudy.java_base.thread.wn;

/**
 * 测试 wait/notify/notifyAll
 * Created by tzh on 2020/11/23.
 */
class TestWN {
    private static Express express = new Express(0, Express.CITY);

    private static class CheckKm extends Thread {
        @Override
        public void run() {
            express.waitKm();
        }
    }

    private static class CheckSite extends Thread {
        @Override
        public void run() {
            express.waitSite();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 3; i++) {
            new CheckSite().start();
        }
        for (int i = 0; i < 3; i++) {
            new CheckKm().start();
        }
        Thread.sleep(1000);
        express.changeSite();
    }
}
