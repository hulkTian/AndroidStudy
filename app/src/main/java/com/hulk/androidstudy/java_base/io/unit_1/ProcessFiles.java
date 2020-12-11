package com.hulk.androidstudy.java_base.io.unit_1;

import java.io.File;
import java.io.IOException;
import java.util.regex.Pattern;

/**
 * 策略模式
 * Created by tzh on 2020/12/9.
 */
public class ProcessFiles {
    public interface Strategy {
        void process(File file);
    }

    private final Strategy strategy;
    private final String regex;

    public ProcessFiles(Strategy strategy, String regex) {
        this.strategy = strategy;
        this.regex = regex;
    }

    public void start(String[] args) {
        try {
            if (args.length == 0) {
                processDirectoryTree(new File("."));
            } else {
                for (String arg : args) {
                    File fileArg = new File(arg);
                    if (fileArg.isDirectory())
                        processDirectoryTree(fileArg);
                    else {
                        //判断文件是否匹配正则
                        Pattern pattern = Pattern.compile(regex);
                        if (pattern.matcher(arg).matches()) {
                            strategy.process(fileArg.getCanonicalFile());
                        }
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void processDirectoryTree(File root) throws IOException {
        long sum = 0;
        for (File file : Directory.walk(root.getPath(), regex)) {
            sum += file.length();
            strategy.process(file.getCanonicalFile());
        }
        System.out.println("文件总长度：" + sum);
    }

    public static void main(String[] args) {
        new ProcessFiles(new ProcessFiles.Strategy() {

            @Override
            public void process(File file) {
                //查找一小时内被修改的文件
                long data = System.currentTimeMillis() - 1000 * 60 * 60 * 24;
                if (file.lastModified() >= data )
                    System.out.println(file);
            }
        }, "[a-zA-Z]*.java").start(new String[]{"app", "app\\src\\main\\java\\com\\hulk\\androidstudy\\java_base\\io\\unit_1"});

    }

}
