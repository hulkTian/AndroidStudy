package com.hulk.androidstudy.java_base.io;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * 警告信息提示：Externalizable类需要一个public的无参构造方法，应为在反序列化时会先调用无参构造方法
 * 创建一个实例再调用readExternal方法
 * Created by tzh on 2020/12/16.
 */
public class Blip2 implements Externalizable {
    Blip2(){System.out.println("Blip2 Constructor");}

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        System.out.println("Blip2.writeExternal");
    }

    @Override
    public void readExternal(ObjectInput in) throws ClassNotFoundException, IOException {
        System.out.println("Blip2.readExternal");
    }
}
