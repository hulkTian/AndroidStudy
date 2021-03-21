package com.hulk.androidstudy.java_base.jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * 运行时常量池在方法区中，所以限制方法区的大小就能限制常量池的大小
 * 运行时常量池OOM
 * VM Args: -XX:PermSize=10M -X:MaxPermSize=10M
 */
public class RuntimeConstantPoolOOM {

    public static void main(String[] args) {
        //保持着常量池引用，避免Full GC回收常量池行为
        List<String> list = new ArrayList<>();
        //10M的PermSize在integer范围内足够参数OOM了
        int i = 0;
        while (true) {
            //intern方法会先检查字符串常量池中是否有相同的字符串有就返回字符串的String对象，没有就会在
            // 字符串常量池中创建字符串并返回字符串的引用
            list.add(String.valueOf(i++).intern());
        }
    }
}
