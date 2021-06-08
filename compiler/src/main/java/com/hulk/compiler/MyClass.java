package com.hulk.compiler;

import java.util.HashSet;

public class MyClass {
    public static void main(String[] args) {
        HashSet<Person> set = new HashSet<Person>();
        Person p1 = new Person("AA",1001);
        Person p2 = new Person("BB",1002);

        set.add(p1);
        set.add(p2);
        System.out.println(set);

        p1.name = "CC";//第一处 [Person [name=CC, id=1001], Person [name=BB, id=1002]]
        set.remove(p1);// 第二处
        System.out.println(set);
        System.out.println(p1);

        set.add(new Person("CC",1001));// 第三处
        System.out.println(set);
        set.add(new Person("AA",1001));// 第四处
        System.out.println(set);
    }

    private static class Person{
        String name;
        int id;

        public Person(String name, int id) {
            this.name = name;
            this.id = id;
        }

        @Override
        public String toString() {
            return "Person [name=" + name + ", id=" + id + "]";
        }
    }
}