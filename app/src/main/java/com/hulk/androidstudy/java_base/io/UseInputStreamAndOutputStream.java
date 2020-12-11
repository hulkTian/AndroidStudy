package com.hulk.androidstudy.java_base.io;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.io.SequenceInputStream;
import java.io.StringBufferInputStream;
import java.io.StringReader;

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
//        UseInputStreamAndOutputStream.readFileByFileReader("E:/read me.txt");
//        UseInputStreamAndOutputStream.readMemoryInput("E:/read me.txt");
//        UseInputStreamAndOutputStream.readByDataInputStream(
//                "E:/UseInputStreamAndOutputStream.java");
//        UseInputStreamAndOutputStream.writerFile();
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

    /**
     * RandomAccessFile除了实现DataInput和DataOutput接口外，有效地于I/O继承结构的其它部分实现了分离。
     * 因此它不支持装饰，不能将其与InputStream及Outputstream子类的任何部分组合起来。
     * 我们必须假定它已经被正确缓冲，因为我们不能为它添加这样的功能
     * 第二个构造参数：我们可以指定以“只读”（r）或“读写”（rw）方式打开一个RandomAccessFile文件
     */
    private static void useRandomAccessFile() throws IOException {
        RandomAccessFile rf = new RandomAccessFile("E:/a.txt", "rw");
        for (int i = 0; i < 7; i++)
            rf.writeDouble(i * 1.1414);
        rf.writeUTF("The end of the file");
        rf.close();

        RandomAccessFile raf = new RandomAccessFile("E:/a.txt", "rwd");
        for (int i = 0; i < 7; i++)
            System.out.println("value " + i + ": " + raf.readDouble());
        System.out.println(raf.readUTF());
        raf.close();

        rf = new RandomAccessFile("E:/a.txt","rw");
        rf.seek(5*8);
        rf.writeDouble(47.0001);
        rf.close();

        raf = new RandomAccessFile("E:/a.txt", "rwd");
        for (int i = 0; i < 7; i++)
            System.out.println("value " + i + ": " + raf.readDouble());
        System.out.println(raf.readUTF());
        raf.close();
    }

    /**
     * 典型使用方式-缓冲输入文件
     * 打开一个文件用于字符输入，可以使用FileInputStream读入
     * 然后转成字符方式InputStreamReader
     * 为了提高速度，对文件进行缓冲，可以将产生的应用传递给一个 BufferedReader
     * 由于BufferedReader也提供readLine()方法，所以可以按行读取字符流
     */
    private static String readFileByFileReader(String fileName) throws IOException {
        //先以流的方式读入文件，然后转成“GBK”格式的字符流
        InputStreamReader isr = new InputStreamReader(new FileInputStream(fileName),
                "GBK");
        //对文件进行缓冲
        BufferedReader in = new BufferedReader(isr);
        String s;
        StringBuilder sb = new StringBuilder();
        while ((s = in.readLine()) != null) {
            System.out.println(s);
            sb.append(s).append("\n");
        }
        in.close();
        isr.close();
        return sb.toString();
    }

    /**
     * 从内存输入
     */
    private static void readMemoryInput(String fileName) throws IOException {
        StringReader in = new StringReader(readFileByFileReader(fileName));
        int c;
        while ((c = in.read()) != -1) {
            System.out.print((char) c);
        }
    }

    /**
     * 格式化的内存输入
     */
    private static void readByDataInputStream(String fileName) {
        try {
            DataInputStream in = new DataInputStream(
                    new ByteArrayInputStream(readFileByFileReader(fileName).getBytes()));
            while (in.available() != 0)
                System.out.print((char) in.readByte());
        } catch (IOException e) {
            System.err.println("End of stream");
        }
    }

    private static void writerFile() throws IOException {
        BufferedReader in = new BufferedReader(new StringReader(readFileByFileReader(
                "E:/read me.txt")));
        File file = new File("E:/a");
        if (!file.exists()) {
            file.mkdirs();
        }
//        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("E:/a/read.txt")));
        //整个构造器可以简化流的写出操作，但实际上任然是在进行缓存，只是不同自己区实现。
        PrintWriter out = new PrintWriter("E:/a/read.txt");
//        int lineCount = 1;
        String s;
//        while ((s = in.readLine()) != null) {
//            out.println(lineCount++ + ": " + s);
//        }
//        out.close();
//        System.out.println(readFileByFileReader("E:/a/read.txt"));

        //使用LineNumberReader进行行数记录
        LineNumberReader lineNumberReader = new LineNumberReader(in);
        while ((s = lineNumberReader.readLine()) != null) {
            out.println(lineNumberReader.getLineNumber() + ": " + s);
        }
        out.close();
        System.out.println(readFileByFileReader("E:/a/read.txt"));
    }

}
