package com.hulk.androidstudy.java_base.io.unit_1;

import com.hulk.androidstudy.java_base.io.tools.PPrint;

import java.io.File;

/**
 * 目录检索例子
 * Created by tzh on 2020/12/7.
 */
public class DirectoryDemo {
    public static void main(String[] args) {
        String path = "app\\src\\main\\java\\com\\hulk\\androidstudy\\java_base\\io\\unit_1";
//        String path = "app\\src";
        //所有目录
        PPrint.pprint(Directory.walk(path).dirs);
        System.out.println("------------------");
        //所有以‘T’开始的文件
        for (File file : Directory.local(path, "D[a-zA-Z]*.java"))
            System.out.println(file);
        System.out.println("------------------");
        //所有以‘T.’开始的Java文件
        for (File file : Directory.walk(path, "[a-zA-Z]*.java"))
            System.out.println(file);
        System.out.println("------------------");
        //包含'Z'和'z'的类文件
        for (File file : Directory.walk(path, ".class"))
            System.out.println(file);
    }
}
