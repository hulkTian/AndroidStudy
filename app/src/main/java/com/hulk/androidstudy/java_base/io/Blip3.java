package com.hulk.androidstudy.java_base.io;

import java.io.Externalizable;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

/**
 * Created by tzh on 2020/12/16.
 */
public class Blip3 implements Externalizable {
    private int i;
    private String s;

    public Blip3() {
        System.out.println("Blip3 Constructor");
        //s,i没有初始化
    }

    public Blip3(String x, int a) {
        System.out.println("Blip3(String x, int a)");
        s = x;
        i = a;
        //s,i只有在非默认的构造器种初始化
    }

    public String toString() {
        return s + i;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        System.out.println("Blip3.writeExternal");
        //必要步骤
        out.writeObject(s);
        out.writeInt(i);
    }

    @Override
    public void readExternal(ObjectInput in) throws ClassNotFoundException, IOException {
        System.out.println("Blip3.readExternal");
        //必要步骤
        s = (String) in.readObject();
        i = in.readInt();
    }

    public static void main(String[] args) throws Exception {
        System.out.println("Constructing objects:");
        Blip3 b3 = new Blip3("A String ", 47);
        System.out.println(b3);
        ObjectOutputStream o = new ObjectOutputStream(new FileOutputStream("E:/Blip3.out"));
        o.writeObject(b3);
        o.close();
        ObjectInputStream in = new ObjectInputStream(new FileInputStream("E:/Blip3.out"));
        System.out.println("Recovering b3:");
        b3 = (Blip3) in.readObject();
        System.out.println(b3);
    }
}
