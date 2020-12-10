package com.hulk.androidstudy.java_base.generic;

/**
 * Created by tzh on 2020/11/29.
 */
public class TestFruit1 {
    private Fruit fruit;

    public TestFruit1(Fruit fruit) {
        this.fruit = fruit;
    }

    public static void main(String[] args) {
        Apple apple = new Apple("apple");
        TestFruit1 testFruit = new TestFruit1(apple);
        System.out.println(testFruit.fruit.getName());
    }
}
