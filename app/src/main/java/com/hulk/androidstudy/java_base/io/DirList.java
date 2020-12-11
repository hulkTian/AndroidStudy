package com.hulk.androidstudy.java_base.io;

import com.hulk.androidstudy.java_base.io.tools.PPrint;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.regex.Pattern;

/**
 * 目录列表器
 * 这个例子使用了三种方式创建过滤器
 * Created by tzh on 2020/12/7.
 */
public class DirList {
    private final String[] dirArray;

    /**
     * 可以接受文件路径信息，并能构建该路径下所有文件的排序目录列表的构造器
     */
    public DirList(File dir) {
        dirArray = dir.list();
        Arrays.sort(dirArray, String.CASE_INSENSITIVE_ORDER);
    }

    public String[] list() {
        return dirArray;
    }

    public String[] list(String regex) {
        Pattern pattern = Pattern.compile(regex);
        ArrayList<String> temp = new ArrayList<>();
        int count = 0;
        for (String file : dirArray) {
            if (pattern.matcher(file).matches()) {
                count++;
                temp.add(file);
            }
        }
        return temp.toArray(new String[count]);
    }

    /**
     * 计算目录下文件总长度
     */
    public long totalLength(File dir) {
        long sum = 0;
        for (File file : dir.listFiles()) {
            System.out.print(file + ": " + file.length());
            sum += file.length();
        }
        return sum;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(String s : dirArray) sb.append(s).append('\n');
        return sb.toString();
    }

    public static void main(final String[] args) {
        //根目录
        File path = new File(".");
        String[] list;
        if (args.length == 0)
            list = path.list();
        else
//            list = path.list(new DirFilter(args[0]));
//            list = path.list(filter(args[0]));
        list = path.list(new FilenameFilter() {
            private final Pattern pattern = Pattern.compile(args[0]);
            @Override
            public boolean accept(File dir, String name) {
                return pattern.matcher(name).matches();
            }
        });
        Arrays.sort(list, String.CASE_INSENSITIVE_ORDER);
        PPrint.pprint(list);
    }

    //目录过滤器
    private static class DirFilter implements FilenameFilter {
        private final Pattern pattern;

        public DirFilter(String regex) {
            pattern = Pattern.compile(regex);
        }

        //此时没有用到dir，但是可以使用dir做其他的筛选处理
        public boolean accept(File dir, String name) {
            return pattern.matcher(name).matches();
        }
    }

    //过滤器也可以作为匿名内部类，作为工具方便使用
    //注意，regex必须时final的，这样才能被匿名内部类使用
    public static FilenameFilter filter(final String regex) {
        return new FilenameFilter() {
            private final Pattern pattern = Pattern.compile(regex);
            @Override
            public boolean accept(File dir, String name) {
                return pattern.matcher(name).matches();
            }
        };
    }



}
