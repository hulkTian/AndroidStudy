package com.hulk.androidstudy.java_base.generic;

/**
 * Created by tzh on 2020/11/29.
 */
public class Fruit {
    private String name;

    public Fruit() {
        this.name = "";
    }

    public Fruit(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
