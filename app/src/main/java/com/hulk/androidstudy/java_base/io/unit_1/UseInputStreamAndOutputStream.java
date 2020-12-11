package com.hulk.androidstudy.java_base.io.unit_1;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.PrintStream;
import java.io.RandomAccessFile;
import java.io.SequenceInputStream;
import java.io.StringBufferInputStream;

/**
 * Created by tzh on 2020/12/10.
 */
public class UseInputStreamAndOutputStream {

    public static void main(String[] args) throws IOException {
//        UseInputStreamAndOutputStream.useByteArrayInputStream();
//        UseInputStreamAndOutputStream.useStringBufferInputStream();
//        UseInputStreamAndOutputStream.useFileInputStream();
//        UseInputStreamAndOutputStream.usePipeInputStream();
//        UseInputStreamAndOutputStream.useSequenceInputStream();
//        UseInputStreamAndOutputStream.useDataInputStream();
//        UseInputStreamAndOutputStream.useBufferedInputStream();
        UseInputStreamAndOutputStream.useRandomAccessFile();
    }

    private static void useByteArrayInputStream() throws IOException {
        //字节数组输出流，12byte的缓存区
        ByteArrayOutputStream bOutput = new ByteArrayOutputStream(12);
        while (bOutput.size() != 10) {
            // 获取用户输入值
            bOutput.write(System.in.read());
        }
        //用戶输入转成字节数组
        byte[] b = bOutput.toByteArray();
        System.out.println("Print the content");
        for (byte value : b) {
            // 打印字符
            System.out.print((char) value + "   ");
        }
        System.out.println("   ");
        int c;
        //接受用户输入内容，转为字节数组输入流
        ByteArrayInputStream bInput = new ByteArrayInputStream(b);
        System.out.println("Converting characters to Upper case ");
        for (int y = 0; y < 1; y++) {
            //从字节数组输入流中每次读取一个字节的数据，直到读完
            while ((c = bInput.read()) != -1) {
                //将读取的字节转为大写的字符
                System.out.println(Character.toUpperCase((char) c));
            }
            //重置输入流标记位
            bInput.reset();
        }
    }

    private static void useStringBufferInputStream() throws IOException {
        StringBufferInputStream sis = new StringBufferInputStream("abc");
        int c;
        while ((c = sis.read()) != -1) {
            System.out.println(Character.toUpperCase((char) c));
        }
        sis.close();
    }

    private static void useFileInputStream() throws IOException {
        File file = new File("C:/Users/Administrator/Desktop/", "test.txt");
        file.createNewFile();
        //获取指定文件的流对象，可以将数据写到输入流对象
        FileOutputStream fos = new FileOutputStream(file);
        byte[] bWrite = {1, 2, 3, 4, 5};
        //写的数据会被保存到磁盘文件中
        for (byte b : bWrite) {
            fos.write(b);
        }
        fos.close();

        //开始读取文件
        FileInputStream fis = new FileInputStream(file);
        //获取文件可读长度
        int size = fis.available();
        for (int i = 0; i < size; i++) {
            System.out.print(fis.read() + "  ");
        }
        fis.close();
    }

    public static void usePipeInputStream() throws IOException {
        File file1 = new File("E:/", "read me.txt");
        FileInputStream fis = new FileInputStream(file1);
        PipedOutputStream pos = new PipedOutputStream();
        PipedInputStream pis = new PipedInputStream();
        pis.connect(pos);

        byte[] b = new byte[1024];
        while (fis.read(b) != -1) {
            pos.write(b);
            System.out.println("正在读取中。。。");
        }

        byte[] b1 = new byte[1024];
        File File2 = new File("E:/", "read.txt");
        File2.createNewFile();
        FileOutputStream fos = new FileOutputStream(File2);
        while (pis.read(b1) != -1) {
            System.out.println("正在写入中。。。");
            fos.write(b1);
        }
        fos.flush();
        fos.close();
        pis.close();
        pos.close();
        System.out.println("完成");
    }

    private static void useSequenceInputStream() throws IOException {
        File file1 = new File("E:/", "read me.txt");
        FileInputStream fis1 = new FileInputStream(file1);
        FileInputStream fis2 = new FileInputStream(file1);
        SequenceInputStream sio = new SequenceInputStream(fis1, fis2);
        File file2 = new File("E:/", "read1.txt");
        file2.createNewFile();
        FileOutputStream fos = new FileOutputStream(file2);
        int c;
        while ((c = sio.read()) != -1) {
            fos.write(c);
        }
        fis1.close();
        fis2.close();
        fos.flush();
        fos.close();
    }

    private static void useDataInputStream() throws IOException {
        DataInputStream in = new DataInputStream(new FileInputStream("E:/read.txt"));
        DataOutputStream out = new DataOutputStream(new FileOutputStream("E:/read1.txt"));
        BufferedReader d = new BufferedReader(new InputStreamReader(in));

        String count;
        while ((count = d.readLine()) != null) {
            String u = count.toUpperCase();
            System.out.println(u);
//            out.writeBytes(u + "  ,");
            out.writeChars(u + "\n");
        }
        d.close();
        out.close();
    }

    private static void useBufferedInputStream() throws IOException {
        InputStream is = new FileInputStream("E:/read.txt");
        BufferedInputStream bis = new BufferedInputStream(is);

        final StringBuilder s = new StringBuilder();
        int b;
        while ((b = bis.read()) != -1) {
            System.out.print((char) b);
            s.append((char) b);
        }
        System.out.println("\n=======================");
        System.out.println(s.toString());
    }

    private static void useRandomAccessFile() throws IOException{
        RandomAccessFile raf = new RandomAccessFile("E:/read.txt", "rwd");

        raf.seek(11);
        raf.writeBoolean(true);
        raf.writeDouble(1);
        System.out.print(raf.getFilePointer());

    }

    private static void readFileByFileReader() throws IOException{
        BufferedReader in = new BufferedReader(new FileReader("E:/read me.txt"));
        String s;
        StringBuilder sb = new StringBuilder();
        while ((s = in.readLine()) != null) {
            sb.append(s).append("\n");
            in.close();
        }

    }
}
