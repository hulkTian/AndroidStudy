package com.hulk.androidstudy.java_base.jvm;

/**
 * Created by tzh on 2021/1/5.
 */
class Example {
    private static final int _1MB = 1024 * 1024;

    public static void main(String[] args) {
//        Example.testAllocation();
//        testPretenureSizeThreshold();
        testTenuringThreshold();
    }

    /**
     * VM Args : -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8
     * 测试对象分配首先在Eden分配，如果Eden空间不够，会出发一次Minor GC
     */
    public static void testAllocation() {
        byte[] allocation1, allocation2, allocation3, allocation4;
        allocation1 = new byte[2 * _1MB];
        allocation2 = new byte[2 * _1MB];
        allocation3 = new byte[2 * _1MB];
        allocation4 = new byte[4 * _1MB];//出现一次Minor GC
    }

    /**
     * VM Args:-verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8
     * -XX:+PretenureSizeThreshold=3145728
     */
    public static void testPretenureSizeThreshold() {
        byte[] allocation;
        allocation = new byte[4 * _1MB];//直接分配在老年代
    }

    /**
     * 测试年龄阈值
     * VM Args:-verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8
     * -XX:MaxTenuringThreshold=1 -XX:+PrintTenuringDistribution
     */
    public static void testTenuringThreshold() {
        byte[] allocation1, allocation2, allocation3;
        allocation1 = new byte[_1MB / 4];
        allocation2 = new byte[4 * _1MB];
        allocation3 = new byte[4 * _1MB];
        allocation3 = null;
        allocation3 = new byte[4 * _1MB];
    }
}
