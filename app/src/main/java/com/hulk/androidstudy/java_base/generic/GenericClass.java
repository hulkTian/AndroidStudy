package com.hulk.androidstudy.java_base.generic;

/**
 * Created by tzh on 2020/11/29.
 */
public class GenericClass<A, B extends Integer> {
    private A first;
    private B second;

    public GenericClass(A first, B second) {
        this.first = first;
        this.second = second;
    }

    public static void main(String[] args) {
        GenericClass<String, Integer> twoGeneric = new GenericClass<>("first", 1);
        System.out.println("first = " + twoGeneric.first + "\nsecond=" + twoGeneric.second);
    }

    private <T,K> T genericMethod(T t, K k) {
        System.out.println("泛型方法传入的值 t = " + t);
        System.out.println("泛型方法传入的值 k = " + k);
        return t;
    }

    private A generalMethod(A t) {
        System.out.println("普通方法传入的值 t = " + t);
        return t;
    }

}
