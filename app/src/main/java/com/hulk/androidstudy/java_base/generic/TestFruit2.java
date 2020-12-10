package com.hulk.androidstudy.java_base.generic;

/**
 * Created by tzh on 2020/11/29.
 */
public class TestFruit2 {
    private Object fruit;

    public TestFruit2(Object fruit) {
        this.fruit = fruit;
    }

    public static void main(String[] args) {
        Apple apple = new Apple("apple");
        TestFruit2 testFruit = new TestFruit2(apple);
        System.out.println(((Fruit)testFruit.fruit).getName());
    }
}
