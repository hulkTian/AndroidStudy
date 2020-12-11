package com.hulk.androidstudy.java_base.io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;

/**
 * Created by tzh on 2020/12/11.
 */
public class Echo {
    public static void main(String[] args) throws IOException {
//        useStandIn();
//        changeSystemOut();
        redirecting();
    }

    /**
     * 将字节输入流转为标准输入流
     */
    public static void useStandIn() throws IOException {
        File file = new File("E:/TextFile.java");
        BufferedInputStream inFile = new BufferedInputStream(new FileInputStream(file));
        System.setIn(inFile);
        BufferedReader stdin = new BufferedReader(
                new InputStreamReader(System.in));
        String s;
        while ((s = stdin.readLine()) != null)
            System.out.println(s.toUpperCase());
    }

    /**
     * 将System.out转换成PrintWriter
     */
    public static void changeSystemOut() {
        //PrintWriter的构造器可以接受一个OutputStream和一个Boolean值，
        // true表示自动开启清空功能，否则可能看不到输出
        PrintWriter out = new PrintWriter(System.out, true);
        out.println("Hello, world");
    }

    /**
     * 标准I/O重定向
     */
    public static void redirecting() throws IOException {
        PrintStream console = System.out;
        BufferedInputStream in = new BufferedInputStream(new FileInputStream("E:/TextFile.java"));
        PrintStream out = new PrintStream(new BufferedOutputStream(new FileOutputStream("E:/TextFile.java")));
        System.setIn(in);
        System.setOut(out);
        System.setErr(out);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s;
        while ((s = br.readLine()) != null)
            System.out.println(s);
        out.close();
        System.setOut(console);
    }
}
