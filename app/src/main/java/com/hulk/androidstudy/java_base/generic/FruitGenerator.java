package com.hulk.androidstudy.java_base.generic;

import java.util.Random;

/**
 * Created by tzh on 2020/11/29.
 */
public class FruitGenerator implements Generator<Fruit> {
    private Class[] types = {Apple.class, Orange.class, HongFuShi.class};
    private static Random rand = new Random(47);

    public Fruit next() {
        try {
            return (Fruit) types[rand.nextInt(types.length)].newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
//        FruitGenerator fg = new FruitGenerator();
//        for (int i = 0; i < 3; i++) {
//            System.out.println(fg.next());
//        }
//        print(new TestFruit3<>(new Apple()));
//        List<? extends Fruit> list = new ArrayList<Orange>();
//        System.out.println(list.get(0));
//        Number[] numbers = new Integer[10];
//        numbers[0] = 1;
//        List<? extends Number> arrayList= new ArrayList<Integer>();
    }

    public static void print(TestFruit3<? extends Fruit> p) {
        p.fruit.getName();
        System.out.println(p);
    }

    public static void print2(TestFruit3<? super Fruit> p) {
        p.setFruit(new HongFuShi());
        p.fruit = new HongFuShi();
        System.out.println(p);
    }

}
