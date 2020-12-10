package com.hulk.androidstudy.java_base.io.unit_1;

import com.hulk.androidstudy.java_base.io.tools.PPrint;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

/**
 * 目录工具
 * Created by tzh on 2020/12/7.
 */
public class Directory {

    //根据指定的目录和正则，筛选出需要的文件对象数组
    public static File[] local(File dir, final String regex) {
        if (dir.exists()) {
            return dir.listFiles(new FilenameFilter() {
                private final Pattern pattern = Pattern.compile(regex);

                @Override
                public boolean accept(File dir, String name) {
                    return pattern.matcher(new File(name).getName()).matches();
                }
            });
        } else {
            return new File[0];
        }
    }

    public static File[] local(String path, final String regex) {
        return local(new File(path), regex);
    }

    /**
     * 文件树
     */
    public static class TreeInfo implements Iterable<File> {
        //文件集合
        public List<File> files = new ArrayList<>();
        //路径集合
        public List<File> dirs = new ArrayList<>();

        public Iterator<File> iterator() {
            return files.iterator();
        }

        void addAll(TreeInfo other) {
            files.addAll(other.files);
            dirs.addAll(other.dirs);
        }

        public String toString() {
            return "dirs: " + PPrint.pformat(dirs) + "\n\nfiles: " + PPrint.pformat(files);
        }
    }

    public static TreeInfo walk(String start, String regex) {
        return recurseDirs(new File(start), regex);
    }

    //根据文件对象获取目录下指定的文件树
    public static TreeInfo walk(File start) {
        return recurseDirs(start, ".*");
    }

    //根据目录名获取目录下指定的文件树
    public static TreeInfo walk(String start) {
        return walk(new File(start));
    }

    static TreeInfo recurseDirs(File startDir, String regex) {
        TreeInfo result = new TreeInfo();
        if (startDir.exists()) {
            for (File item : startDir.listFiles()) {
                if (item.isDirectory()) {
                    result.dirs.add(item);
                } else if (item.getName().matches(regex)){
                    result.files.add(item);
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        if (args.length == 0)
//            System.out.println(walk("."));
            System.out.println(walk("app", "build.gradle"));
        else
            for (String arg : args)
                System.out.println(walk(arg));
    }
}
