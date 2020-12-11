package com.hulk.androidstudy.java_base.io;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 读取二进制文件
 * Created by tzh on 2020/12/11.
 */
public class BinaryFile {

    public static Map<Byte, Integer> computeByteCount(final String bFile) throws IOException {
        Map<Byte, Integer> map = new HashMap<>();
        byte[] bytes = BinaryFile.read(bFile);
        for (Byte b : bytes) {
            if (map.get(b) == null) {
                map.put(b, 1);
            } else {
                map.put(b, map.get(b) + 1);
            }
        }
        return map;
    }

    public static byte[] read(File bFile) throws IOException {
        BufferedInputStream bf = new BufferedInputStream(new FileInputStream(bFile));
        try {
            byte[] data = new byte[bf.available()];
            bf.read(data);
            return data;
        } finally {
            bf.close();
        }
    }

    public static byte[] read(String bFile) throws IOException {
        return read(new File(bFile).getAbsoluteFile());
    }

    public static void main(String[] args) throws IOException {
        Map<Byte, Integer> map = computeByteCount("E:/TextFile.java");
        for (Byte b : map.keySet()) {
            System.out.println(((char)b.byteValue()) + ": " + map.get(b));
        }
    }
}
