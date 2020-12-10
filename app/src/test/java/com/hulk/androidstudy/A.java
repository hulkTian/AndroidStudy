package com.hulk.androidstudy;

import org.junit.Test;
import org.junit.runners.model.TestClass;

/**
 * Created by tzh on 2020/11/16.
 */
class A<T> extends TestClass {
    T t;

    /**
     * Creates a {@code TestClass} wrapping {@code clazz}. Each time this
     * constructor executes, the class is scanned for annotations, which can be
     * an expensive process (we hope in future JDK's it will not be.) Therefore,
     * try to share instances of {@code TestClass} where possible.
     *
     * @param clazz
     */
    public A(Class<?> clazz) {
        super(clazz);
    }

    @Test
    public void test() {
        A[] a = new A[10];
        a[0] = new A<Double>(A.class);
        a[0].t = 1;
    }

}
