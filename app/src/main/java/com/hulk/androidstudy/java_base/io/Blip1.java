package com.hulk.androidstudy.java_base.io;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * Created by tzh on 2020/12/16.
 */
public class Blip1 implements Externalizable {
    public Blip1() { System.out.println("Blip1 Constructor");}

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        System.out.println("Blip1.writeExternal");
    }

    @Override
    public void readExternal(ObjectInput in) throws ClassNotFoundException, IOException {
        System.out.println("Blip1.readExternal");
    }
}
