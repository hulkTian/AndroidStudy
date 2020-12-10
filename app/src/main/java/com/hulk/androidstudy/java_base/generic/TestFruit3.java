package com.hulk.androidstudy.java_base.generic;

/**
 * Created by tzh on 2020/11/30.
 */
public class TestFruit3<T> {
    public T fruit;

    public TestFruit3(T fruit) {
        this.fruit = fruit;
    }

    public void setFruit(T fruit) {
        this.fruit = fruit;
    }

    public static void main(String[] args) {
//        TestFruit3<Exception> testFruit3 = new TestFruit3<>(new RuntimeException());
//        //编译报错，不能抛出泛型类
//        try {
//            throw (RuntimeException)testFruit3.fruit;
//        } catch (Exception exception) {
//            exception.printStackTrace();
//        }
//        testFruit3.e = new RuntimeException();
//        throw testFruit3.e;
//        List<Fruit> fruits = new ArrayList<Apple>();
        Fruit[] fruitsArray = new Apple[10];
        fruitsArray[0] = new Fruit();
        fruitsArray[1] = new Orange();
    }

    public void a() {

    }
}
