package com.hulk.androidstudy.java_base.io;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * 序列化控制
 * Created by tzh on 2020/12/16.
 */
public class Blips {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        System.out.println("Constructing objects:");
        Blip1 b1 = new Blip1();
        Blip2 b2 = new Blip2();
        ObjectOutputStream o = new ObjectOutputStream(new FileOutputStream("E:/Blips.out"));
        System.out.println("Saving objects:");
        o.writeObject(b1);
        o.writeObject(b2);
        o.close();
        ObjectInputStream in = new ObjectInputStream(new FileInputStream("E:/Blips.out"));
        System.out.println("Recovering b1:");
        b1 = (Blip1) in.readObject();
        //抛出异常，缺少public的无参构造方法，无法反序列化
        System.out.println("Recovering b2:");
        b2 = (Blip2) in.readObject();
    }
}
