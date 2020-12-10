package com.hulk.androidstudy.java_base.thread.wn;

/**
 * 快递实体类
 * Created by tzh on 2020/11/23.
 */
class Express {
    public final static String CITY = "SHANGHAI";
    private int km;//快递运输里程数
    private String site;//快递到达地点

    public Express() {
    }

    public Express(int km, String site) {
        this.km = km;
        this.site = site;
    }

    /**
     * 公里数变化后通知处于wait状态并需要处理公里数的线程进行业务处理
     */
    public synchronized void changeKm() {
        this.km = 101;
        notify();
    }

    /**
     * 地点变化，通知处于wait状态并需要处理地点的线程进行业务处理
     */
    public synchronized void changeSite() {
        this.site = "BEIJING";
        notifyAll();
    }

    /**
     * 等待公里数的变化
     */
    public synchronized void waitKm() {
        while (this.km < 100) {
            try {
                wait();
                System.out.println("Check Km thread[" + Thread.currentThread().getId() + "] is be notified");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("the Km is " + this.km + ",I will change db");
    }

    /**
     * 等待地点的变化
     */
    public synchronized void waitSite() {
        while (this.site.equals(CITY)) {
            try {
                wait();
                System.out.println("Check Site thread[" + Thread.currentThread().getId() + "] is be notified");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("the site is " + this.site + ",I will call user");
    }
}
