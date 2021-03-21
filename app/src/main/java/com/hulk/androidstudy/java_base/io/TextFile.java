package com.hulk.androidstudy.java_base.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

/**
 * Created by tzh on 2020/12/11.
 */
public class TextFile extends ArrayList<String> {

    /**
     * @param fileName   文件名
     * @param characters 需要记录的字符数组
     */
    public static Map<Character, Integer> computeCharCount(String fileName, Character... characters) throws IOException {
        Map<Character, Integer> map = new HashMap<>();
        String file = read(fileName);
        for (Character character : file.toCharArray()) {
            for (Character character1 : characters) {
                if (character == character1) {
                    if (map.get(character) != null) {
                        map.put(character, map.get(character) + 1);
                    } else {
                        map.put(character, 1);
                    }
                }
            }
        }
        return map;
    }

    /**
     * 读取文件转为字符串
     */
    public static String read(String fileName) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader in = new BufferedReader(new FileReader(
                new File(fileName).getAbsoluteFile()
        ));
        try {
            String s;
            while ((s = in.readLine()) != null) {
                sb.append(s);
                sb.append("\n");
            }
        } finally {
            in.close();
        }
        return sb.toString();
    }

    /**
     * 字符串写入文件
     */
    public static void write(String fileName, String text) throws IOException {
        PrintWriter out = new PrintWriter(
                new File(fileName).getAbsoluteFile()
        );
        try {
            out.print(text);
        } finally {
            out.close();
        }
    }

    /**
     * 读取文件，按照特定规则分割
     *
     * @param fileName 文件名称
     * @param splitter 分割方式
     */
    public TextFile(String fileName, String splitter) throws IOException {
        super(Arrays.asList(read(fileName).split(splitter)));
        //split方法通常会留一个空的字符串在第一个位置
        if (get(0).equals("")) remove(0);
    }

    //按行分割
    public TextFile(String fileName) throws IOException {
        this(fileName, "\n");
    }

    public void write(String fileName) throws IOException {
        PrintWriter out = new PrintWriter(new File(fileName).getAbsoluteFile());
        try {
            for (String item : this)
                out.println(item);
        } finally {
            out.close();
        }
    }

    public static void main(String[] args) throws IOException {
        String file = read("E:/TextFile.java");
        write("E:/test.txt", file);
        TextFile text = new TextFile("E:/test.txt");
        text.write("E:/test2.txt");
        TreeSet<String> words = new TreeSet<>(new TextFile("E:/TextFile.java", "\\W+"));
        System.out.println(words.headSet("a"));
        Map<Character, Integer> map = computeCharCount("E:/TextFile.java", 'A', 'C', '1', 'f');
        for (Character character : map.keySet()) {
            System.out.println(character + ": " + map.get(character));
        }
    }

}
