package com.hulk.androidstudy.java_base.jvm;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.channels.FileChannel;

/**
 * 本机直接内存溢出
 * VM Args:-Xmx20M -XX:MaxDirectMemorySize=10M
 */
public class DirectMemoryOOM {

    private static final int _1MB = 1024*1024;

    public static void main(String[] args) throws FileNotFoundException {
        FileInputStream fis = new FileInputStream("D:/dd");
        FileChannel fc =fis.getChannel();
    }
}
