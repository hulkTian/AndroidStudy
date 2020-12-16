package com.hulk.androidstudy.java_base.io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.OutputStreamWriter;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.io.SequenceInputStream;
import java.io.StringBufferInputStream;
import java.io.StringReader;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.CharBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.LongBuffer;
import java.nio.MappedByteBuffer;
import java.nio.ShortBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.SortedMap;
import java.util.concurrent.TimeUnit;
import java.util.zip.Adler32;
import java.util.zip.CheckedInputStream;
import java.util.zip.CheckedOutputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 * Created by tzh on 2020/12/10.
 */
public class UseIO {

    public static void main(String[] args) throws Exception {
//        UseInputStreamAndOutputStream.useByteArrayInputStream();
//        UseInputStreamAndOutputStream.useStringBufferInputStream();
//        UseInputStreamAndOutputStream.useFileInputStream();
//        UseInputStreamAndOutputStream.usePipeInputStream();
//        UseInputStreamAndOutputStream.useSequenceInputStream();
//        UseInputStreamAndOutputStream.useDataInputStream();
//        UseInputStreamAndOutputStream.useBufferedInputStream();
//        UseIO.useRandomAccessFile();
//        UseInputStreamAndOutputStream.readFileByFileReader("E:/read me.txt");
//        UseInputStreamAndOutputStream.readMemoryInput("E:/read me.txt");
//        UseInputStreamAndOutputStream.readByDataInputStream(
//                "E:/UseInputStreamAndOutputStream.java");
//        UseInputStreamAndOutputStream.writerFile();
//        getChannel();
//        bufferToText();
//        availableCharSets();
//        getData();
//        intBuffer();
//        viewBuffers();
//        endian();
//        usingBuffers();
//        largeMappedFile();
//        fileLocking();
//        GZIPCompress();
        ZipCompress(new String[]{"E:/mappedFile.txt","E:/temp.tmp"});
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

        rf = new RandomAccessFile("E:/a.txt", "rw");
        rf.seek(5 * 8);
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

    /**
     * 1.JKD 1.4的java.nio.*包中引入了新的JavaI/O类，目的在于提高速度。实际上，旧的I/O包已经使用nio重新实现过，
     * 以便充分利用这种速度提高，因此即使不显示地用nio编写代码，也能从中受益。
     * 2.速度的提高在文件I/O和网络I/O中都有可能
     * 3.速度的提高来治愈所使用的结构更接近于操作系统执行I/O的方式：通道和缓冲器
     * 4.通道是数据源它既可以输出数据也可以接受数据，而缓冲器是我们与管道交互的工具，所以我们不与通道交互，
     * 而直接和缓冲器交互。
     * 5.ByteBuffer是一个基础的类，通过告知分配多少存储控件来创建ByteBuffer对象，并且还有一个方法选择集，用于
     * 以原始的字节形式或基本数据类型输入和读取数据，但没办法输出或读取对象，即使是字符串对象也不行。
     * 6.旧I/O有三个类被修改了用以产生FileChannel：FileInputStream、FileOutputStream、RandomAccessFile。
     * 这些都是字节流，Reader和Writer这种字符模式类不能产生通道；但是java.nio.channels.Channels类提供了实
     * 用方法，用以在通道中产生Reader和Writer。
     * 7.nio的目标是快速移动大量数据，因此ByteBuffer的大小就显得尤为重要，必须通过实际运行找到最佳尺寸。
     * 8.使用方法allocateDirect可能比allocate创建的ByteBuffer更快速，因为它会产生一个与操作系统更耦合的“直接”
     * 缓冲器。但这种分配的开销会更大，并且具体实现也随操作系统的不同而不同也需要实际运行来测试是否更高效。
     */
    private static void getChannel() throws IOException {
        //通过FileOutputStream的管道向文件写数据
        FileChannel fc = new FileOutputStream("E:/channel.txt").getChannel();
        //可以使用warp方法将已存在的字节数组“包装”到ByteBuffer中，也可以使用put方法直接填充。
        fc.write(ByteBuffer.wrap("Some text ".getBytes()));
        fc.close();
        //通过RandomAccessFile的管道写数据
        fc = new RandomAccessFile("E:/channel.txt", "rw").getChannel();
        //可以随处移动FileChannel
        fc.position(fc.size());//移动到文件的末尾
        fc.write(ByteBuffer.wrap("Some more".getBytes()));
        fc.close();
        //通过管道读文件
        fc = new FileInputStream("E:/channel.txt").getChannel();
        //对于只读访问，必须显示使用静态的allocate方法来分配缓冲。
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        fc.read(buffer);
        //重置标记为，方便后续对缓冲器数据的读取
        buffer.flip();
        //判断当前位置是否已经到达末位
        while (buffer.hasRemaining()) {
            //读取当前位置的数据，读完后位置会自增
            System.out.print((char) buffer.get());
        }
        fc.close();
        //演示flip和clear
        FileChannel in = new FileInputStream("E:/channel.txt").getChannel(),
                out = new FileOutputStream("E:/channel_write.txt").getChannel();
        ByteBuffer buffer1 = ByteBuffer.allocate(1024);
        while (in.read(buffer1) != -1) {
            //重置标记位，准备写
            buffer.flip();
            out.write(buffer);
            //重置标记位，准备读
            buffer.clear();
        }
        in.close();
        out.close();
        //连接两个通道,transferTo和transferFrom
        in = new FileInputStream("E:/channel.txt").getChannel();
        out = new FileOutputStream("E:/channel_write_2.txt").getChannel();
        in.transferTo(0, in.size(), out);
//        out.transferFrom(in, 0, in.size());
    }

    /**
     * 数据转换
     */
    private static void bufferToText() throws IOException {
        //通过FileOutputStream的管道向文件写数据
        FileChannel fc = new FileOutputStream("E:/channel.txt").getChannel();
        //可以使用warp方法将已存在的字节数组“包装”到ByteBuffer中，也可以使用put方法直接填充。
        fc.write(ByteBuffer.wrap("Some text ".getBytes()));
        fc.close();
        //
        fc = new FileInputStream("E:/channel.txt").getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        fc.read(buffer);
        buffer.flip();
        //CharBuffer的toString方法会返回缓存器中所有字符的字符串
        //ByteBuffer的asCharBuffer方法获取到一个新的字符缓冲器
        //但是输出却有问题
        System.out.println(buffer.asCharBuffer());
        buffer.rewind();
        //解决方法1：在输入时进行编码
        //系统编码
        String encoding = System.getProperty("file.encoding");
        //在缓冲器输出时对它们进行解码，java.nio.charset.Charset类实现了这些功能
        //该类提供了把不同数据编码成多种不同类型的字符集的工具
        System.out.println("Decoded using " + encoding + ": " + Charset.forName(encoding).decode(buffer));
        //解决方法2：在输出时进行编码
        fc = new FileOutputStream("E:/channel.txt").getChannel();
        fc.write(ByteBuffer.wrap("Some text".getBytes("UTF-16BE")));
        fc.close();
        //输入时就不需要解码，正常显示
        fc = new FileInputStream("E:/channel.txt").getChannel();
        buffer.clear();
        fc.read(buffer);
        buffer.flip();
        System.out.println(buffer.asCharBuffer());
        fc.close();
    }

    /**
     * 查询系统字符集
     */
    private static void availableCharSets() {
        SortedMap<String, Charset> charSets = Charset.availableCharsets();
        for (String csName : charSets.keySet()) {
            System.out.print(csName);
            Iterator aliases = charSets.get(csName).aliases().iterator();
            if (aliases.hasNext())
                System.out.print(": ");
            while (aliases.hasNext()) {
                System.out.print(aliases.next());
                if (aliases.hasNext()) {
                    System.out.print(", ");
                }
            }
            System.out.println();
        }
    }

    /**
     * 获取基本类型
     * 1.ByteBuffer初始化时会被自动分配0作为内容填充
     * 2.ByteBuffer插入基本数据类型的方法是：利用asCharBuffer()、asShortBuffer()等获得缓冲器上的视图，然后
     * 使用视图的put()方法。这里有个例外，即使用ShortBuffer的put()方法时，需要进行类型转换（类型转换会截取或改变结果）。
     */
    private static void getData() {
        ByteBuffer bb = ByteBuffer.allocate(1024);
        //ByteBuffer被自动分配0作为数组内容
        int i = 0;
        //一共检测了1024个值，并且所有值都是零
        while (i++ < bb.limit())
            if (bb.get() != 0)
                System.out.print("nonzero");
        System.out.println("i = " + i);
        bb.rewind();
        bb.asCharBuffer().put("Howdy!");
        char c;
        while ((c = bb.getChar()) != 0)
            System.out.print(c + " ");
        bb.rewind();
        System.out.println();
        bb.asShortBuffer().put((short) 471141);
        System.out.println(bb.getShort());
        bb.rewind();
        bb.asIntBuffer().put(99471142);
        System.out.println(bb.getInt());
        bb.rewind();
        bb.asLongBuffer().put(99471142);
        System.out.println(bb.getLong());
        bb.asFloatBuffer().put(99471142);
        System.out.println(bb.getFloat());
        bb.rewind();
        bb.asDoubleBuffer().put(99471142);
        System.out.println(bb.getDouble());
        bb.rewind();
    }

    /**
     * 视图缓冲器（view buffer）
     * 1.可以让我们通过某个特定的基本数据类型的视图查看其底层的Byte Buffer。
     * 2.视图地任何修改都会映射成对ByteBuffer中数据地修改，这种方式很方便地向ByteBuffer插入数据。
     * 3.还可以一次一个或成批地（放入数组中）读取基本数据类型值
     * 下面例子，通过IntBuffer操纵ByteBuffer中的int型数据：
     */
    private static void intBuffer() {
        ByteBuffer bb = ByteBuffer.allocate(1024);
        //获取ByteBuffer的IntBuffer视图
        IntBuffer ib = bb.asIntBuffer();
        //批量放入数据
        ib.put(new int[]{11, 42, 47, 99, 143, 811, 1016});
        //打印指定位置的数据
        System.out.println(ib.get(3));
        //设置指定位置的数据
        ib.put(3, 1811);
        //重置标记位
        ib.flip();
        //检测是否到达末尾
        while (ib.hasRemaining()) {
            //逐个读取数据
            int i = ib.get();
            System.out.println(i);
        }
    }

    /**
     * 通过同一个ByteBuffer上建立不同的视图缓冲器，将同一字节翻译成不同的数据类型
     * 1个char = 2个字节= 16位二进制
     * 1个Float = 4个字节=32位二进制
     * 1个Int = 4个字节= 32位二进制
     * 1个Long = 8个字节= 64位二进制
     * 1个Short = 2个字节= 16位二进制
     * 1个Double = 8个字节= 32位二进制
     * 1.不同机器会使用不同的字节排序方法来存储数据。
     * 高位优先（“big endian”）：将重要的字节存放在地址最低的存储器单元
     * 低位优先（“little endian”）：将重要的字节存放在地址最高的存储器单元
     * 2.ByteBuffer是以高位优先的形式存储数据的，并且数据在网络传送时也常常使用高位优先形式。
     * 3.可以使用带参数ByteOrder.BIG_ENDIAN 或ByteOrder.LITTLE_ENDIAN的order()方法改变ByteBuffer的字节排序方式。
     */
    private static void viewBuffers() {
        //被包装的8字节数组
        ByteBuffer bb = ByteBuffer.wrap(new byte[]{0, 0, 0, 0, 0, 0, 0, 'a'});
        bb.rewind();
        System.out.print("Byte Buffer ");
        while (bb.hasRemaining()) {
            System.out.print(bb.position() + " -> " + bb.get() + ", ");
        }
        System.out.println();

        CharBuffer cb = ((ByteBuffer) bb.rewind()).asCharBuffer();
        System.out.print("Char Buffer ");
        while (cb.hasRemaining()) {
            System.out.print(cb.position() + " -> " + cb.get() + ", ");
        }
        System.out.println();

        FloatBuffer fb = ((ByteBuffer) bb.rewind()).asFloatBuffer();
        System.out.print("Float Buffer ");
        while (fb.hasRemaining()) {
            System.out.print(fb.position() + " -> " + fb.get() + ", ");
        }
        System.out.println();

        IntBuffer ib = ((ByteBuffer) bb.rewind()).asIntBuffer();
        System.out.print("Int Buffer ");
        while (ib.hasRemaining()) {
            System.out.print(ib.position() + " -> " + ib.get() + ", ");
        }
        System.out.println();

        LongBuffer lb = ((ByteBuffer) bb.rewind()).asLongBuffer();
        System.out.print("Long Buffer ");
        while (lb.hasRemaining()) {
            System.out.print(lb.position() + " -> " + lb.get() + ", ");
        }
        System.out.println();

        ShortBuffer sb = ((ByteBuffer) bb.rewind()).asShortBuffer();
        System.out.print("Short Buffer ");
        while (sb.hasRemaining()) {
            System.out.print(sb.position() + " -> " + sb.get() + ", ");
        }
        System.out.println();

        DoubleBuffer db = ((ByteBuffer) bb.rewind()).asDoubleBuffer();
        System.out.print("Double Buffer ");
        while (db.hasRemaining()) {
            System.out.print(db.position() + " -> " + db.get() + ", ");
        }
        System.out.println();
    }

    /**
     * 展示通过字节存放模式设置改变字符中的字节次序：
     */
    private static void endian() {
        ByteBuffer bb = ByteBuffer.wrap(new byte[12]);
        bb.asCharBuffer().put("abcdef");
        //array方法只有对数组支持的缓冲器才能调用次方法
        System.out.print(Arrays.toString(bb.array()));
        bb.rewind();
        System.out.println();

        bb.order(ByteOrder.BIG_ENDIAN);
        bb.asCharBuffer().put("abcdef");
        System.out.print(Arrays.toString(bb.array()));
        bb.rewind();
        System.out.println();

        bb.order(ByteOrder.LITTLE_ENDIAN);
        bb.asCharBuffer().put("abcdef");
        System.out.print(Arrays.toString(bb.array()));
        System.out.println();
    }

    /**
     * 用缓冲器操纵数据
     * 1.缓冲器的细节，Buffer由数据和可以高效访问及操纵这些数据的四个索引组成，这四个索引是：mark（标记），
     * position（位置），limit（界限）和capacity（容量）
     * capacity()：返回缓冲区容量
     * clear()：清空缓冲区，position设置位0，limit设置位容量。用于覆写缓冲区
     * flip()：将limit设置位position，position设置为0.准备从缓冲区读取以写入的数据
     * limit()：返回limit值
     * limit(int lim)：设置limit值
     * mark()：设置mark值为position
     * position()：返回position值
     * position(int pos)：设置position值
     * remaining()：返回（limit - position）
     * hasRemaining()：判断position是否到达缓冲区末尾
     */
    private static void usingBuffers() {
        char[] data = "UsingBuffers".toCharArray();
        //capacity = data.length * 2
        //limit = capacity
        //position = 0
        //mark = -1
        ByteBuffer bb = ByteBuffer.allocate(data.length * 2);
        CharBuffer cb = bb.asCharBuffer();
        cb.put(data);
        //position = 0
        System.out.println(cb.rewind());
        //进行字符编码
        //position < limit
        while (cb.hasRemaining()) {
            //mark = position
            cb.mark();
            char c1 = cb.get();
            char c2 = cb.get();
            //position = mark
            cb.reset();
            //position位置改变
            cb.put(c2).put(c1);
        }
        System.out.println(cb.rewind());
        //进行字符解码
        while (cb.hasRemaining()) {
            cb.mark();
            char c1 = cb.get();
            char c2 = cb.get();
            cb.reset();
            cb.put(c2).put(c1);
        }
        System.out.println(cb.rewind());
    }

    /**
     * 内存映射文件允许创建和修改因为太大而不能放入内存的文件。
     * 可以假定整个文件都放在内存中，而且可以完全把它当作非常大的数组来访问。
     */
    private static void largeMappedFile() throws IOException {
        int length = 0x8FFFFFF;//128MB

        //指定文件的初始位置和映射区域的长度
        MappedByteBuffer out = new RandomAccessFile("E:/mappedFile.txt", "rw").getChannel()
                .map(FileChannel.MapMode.READ_WRITE, 0, length);
        for (int i = 0; i < length; i++) {
            out.put((byte) 'x');
        }
        System.out.println("Finished writing");
        //从中间开始读六个字符
        for (int i = length / 2; i < length / 2 + 6; i++) {
            System.out.print((char) out.get(i));
        }
        System.out.println();
    }

    /**
     * 尽管旧I/O流在用nio实现后性能有所提高，但是映射文件访问往往更加显著的加快速度
     */
    private static class MappedIO {
        private static int numOfInts = 4000000;
        private static int numOfUbuffInts = 200000;

        //测试类，打印测试方法执行消耗的时间
        private abstract static class Tester {
            private String name;

            public Tester(String name) {
                this.name = name;
            }

            public void runTest() {
                System.out.print(name + ": ");
                try {
                    long start = System.nanoTime();
                    test();
                    double duration = System.nanoTime() - start;
                    System.out.format("%.2f\n", duration / 1.0e9);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            public abstract void test() throws IOException;
        }

        //测试各种流操作的性能
        private static Tester[] tests = {
                //用流的方式写
                new Tester("Stream Write") {

                    @Override
                    public void test() throws IOException {
                        DataOutputStream dos = new DataOutputStream(
                                new BufferedOutputStream(
                                        new FileOutputStream(
                                                new File("E:/temp.tmp"))));
                        //写入大量int值
                        for (int i = 0; i < numOfInts; i++) {
                            dos.writeInt(i);
                        }
                        dos.close();
                    }
                },
                //用内存映射的方式写
                new Tester("Mapped Write") {
                    @Override
                    public void test() throws IOException {
                        FileChannel fc = new RandomAccessFile("E:/temp.tmp", "rw")
                                .getChannel();
                        //MappedByteBuffer继承至ByteBuffer所以它具有ByteBuffer的所有方法
                        //获取到视图缓冲器IntBuffer
                        IntBuffer ib = fc.map(FileChannel.MapMode.READ_WRITE, 0, fc.size())
                                .asIntBuffer();
                        //又把temp.tmp文件按一样的方式重新写了一次
                        for (int i = 0; i < numOfInts; i++) {
                            ib.put(i);
                        }
                        fc.close();
                    }
                },
                new Tester("Stream Read") {
                    @Override
                    public void test() throws IOException {
                        DataInputStream dis = new DataInputStream(new BufferedInputStream(
                                new FileInputStream(new File("E:/temp.tmp"))));
                        for (int i = 0; i < numOfInts; i++)
                            dis.readInt();
                        dis.close();
                    }
                },
                new Tester("Mapped Read") {
                    @Override
                    public void test() throws IOException {
                        //尽管映射写用到了FileOutputStream，但是映射文件中的所有输出必须使用RandomAccessFile。
                        FileChannel fc = new FileInputStream(new File("E:/temp.tmp"))
                                .getChannel();
                        IntBuffer ib = fc.map(FileChannel.MapMode.READ_ONLY, 0, fc.size())
                                .asIntBuffer();
                        for (int i = 1; i < numOfUbuffInts; i++)
                            ib.get();
                        fc.close();
                    }
                },
                new Tester("Stream Read/Write") {
                    @Override
                    public void test() throws IOException {
                        RandomAccessFile raf = new RandomAccessFile(
                                new File("E:/temp.tmp"), "rw");
                        raf.writeInt(1);
                        for (int i = 0; i < numOfUbuffInts; i++) {
                            raf.seek(raf.length() - 4);
                            raf.writeInt(raf.readInt());
                        }
                        raf.close();
                    }
                },
                new Tester("Mapped Read/Write") {
                    @Override
                    public void test() throws IOException {
                        FileChannel fc = new RandomAccessFile(new File("E:/temp.tmp"), "rw")
                                .getChannel();
                        IntBuffer ib = fc.map(FileChannel.MapMode.READ_WRITE, 0, fc.size())
                                .asIntBuffer();
                        ib.put(0);
                        for (int i = 1; i < numOfUbuffInts; i++)
                            ib.put(ib.get(i - 1));
                        fc.close();
                    }
                }
        };

        public static void main(String[] args) {
            for (Tester test : tests)
                test.runTest();
        }
    }

    /**
     * 文件加锁
     * 1.JDK 1.4引入了文件加锁机制，它允许同步访问某个作为共享资源的文件
     * 2.竞争同一个文件的两个线程可能在不同的虚拟机上；或者一个是Java线程，另一个是操作系统中其它的某个本地线程。
     * 3.文件锁对操作系统进程是可见的，因为Java文件加锁直接映射到了本地操作系统的加锁工具
     * 4.FileLock加锁方式：
     * tryLock():非阻塞式，尝试获取锁，如果不能获得（锁被其它进程持有且不是共享锁），直接从方法调用返回
     * tryLock(long position, long size, boolean shared)：尝试获取文件某一区域（position到position+size）上的锁。
     * 当文件增大超出position+size时，超出部分不会被锁定。或者说position到position+size之外的部分不会被锁。
     * lock()：阻塞式，如果获取不到锁会阻塞线程直到获得，或调用lock的线程中断，或通道关闭。
     * lock(long position, long size, boolean shared)：阻塞式，和lock()d的区别是对某一区域加锁。
     * 5.FileLock释放锁方式：release()
     * 6.独占锁和共享锁的支持必须由底层操作系统提供。可通过FileLock.isShared()查询锁的类型。
     */
    private static void fileLocking() throws Exception {
        FileOutputStream fos = new FileOutputStream("E:/file.txt");
        //通过对FileChannel调用tryLock或lock，可以获得整个文件的FileLock
        FileLock fl = fos.getChannel().tryLock();
        if (fl != null) {
            System.out.println("Locked File");
            TimeUnit.MILLISECONDS.sleep(100);
            //释放锁
            fl.release();
            System.out.println("Released Lock");
        }
        fos.close();
    }

    /**
     * 对映射文件的部分加锁
     */
    private static class lockingMappedFiles {
        static final int LENGTH = 0X8FFFFFF;
        static FileChannel fc;

        public static void main(String[] args) throws Exception {
            //映射写
            fc = new RandomAccessFile("E:/test.dat", "rw").getChannel();
            MappedByteBuffer out = fc.map(FileChannel.MapMode.READ_WRITE, 0, LENGTH);
            for (int i = 0; i < LENGTH; i++)
                out.put((byte) 'x');
            //多个线程同时从不同位置修改文件
            new LockAndModify(fc, out, 0, LENGTH / 3);
//            同一个通道的锁的位置不能重叠，否则会抛出OverlappingFileLockException异常
//            new LockAndModify(out, 0, LENGTH / 2 + LENGTH / 4);
            new LockAndModify(fc, out, LENGTH / 2, LENGTH / 2 + LENGTH / 4);
        }

        private static class LockAndModify extends Thread {
            private final ByteBuffer buff;
            private final int start, end;
            private final FileChannel fc;

            LockAndModify(FileChannel fc, ByteBuffer mbb, int start, int end) {
                this.fc = fc;
                this.start = start;
                this.end = end;
                mbb.limit(end);
                mbb.position(start);
                //创建一个新的缓冲区，其内容是mbb的共享子序列，初始位置是mbb的当前位置
                buff = mbb.slice();
                start();
            }

            @Override
            public void run() {
                try {
                    //获取独占锁
                    FileLock fl = fc.lock(start, end, false);
                    System.out.println("Locked: " + start + " to " + end);
                    //读取子缓冲区的内容并修改
                    while (buff.position() < buff.limit() - 1)
                        buff.put((byte) (buff.get() + 1));
                    fl.release();
                    System.out.println("Released：" + start + " to " + end);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    /**
     * 压缩
     * 1.Java I/O类库中的类支持读写压缩格式的数据流
     * 2.这些类属于InputStream和OutputStream层次结构的一部分。因为压缩类库是按字节方式而不是按字符方式处理。
     * 如果要使用字符数据流可以通过装饰器类进行流类型的转换。
     * 压缩类：
     * CheckedInputStream：装饰器类，记录任何InputStream的校验和
     * CheckedOutputStream: 装饰器类，记录任何OutputStream的校验和
     * DeflaterOutputStream:压缩类的基类
     * ZipOutputStream：用于将数据压缩成Zip文件格式
     * GZIPOutputStream：用于将数据压缩成GZIP文件格式
     * InflaterInputStream：解压缩类的基类
     * ZipInputStream：用于解压缩Zip格式的数据
     * GZIPInputStream：用于解压缩GZIP格式的数据
     */
    private static void GZIPCompress() throws IOException {
        //字符流输入
        BufferedReader in = new BufferedReader(new FileReader("E:/test.java"));
        String s;
        //字符流输出成GZIP格式文件
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new GZIPOutputStream(
                new FileOutputStream("E:/test.gz"))));
        System.out.println("Writing File");
        while ((s = in.readLine()) != null) {
            out.write(s);
            out.write("\n");
        }
//        //使用字节流输出时中文会乱码
//        BufferedOutputStream out = new BufferedOutputStream(new GZIPOutputStream(
//                new FileOutputStream("E:/test.gz")));
//        System.out.println("Writing File");
//        int c;
//        while ((c = in.read()) != -1) {
//            out.write(c);
//        }
        in.close();
        out.close();
        System.out.println("Reading file");
        //字节流读入后使用装饰器转换成字符流
        BufferedReader in2 = new BufferedReader(new InputStreamReader(new GZIPInputStream(
                new FileInputStream("E:/test.gz"))));
        while ((s = in2.readLine()) != null)
            System.out.println(s);
    }

    /**
     * 用Zip进行多文件保存
     * 1.使用Zip格式的Java库更加全面。利用该库可以方便保存多个文件，该库甚至还提供了一个独立类，是的操作Zip文件
     * 更加方便
     * 2.类库使用的是标准Zip格式，可以与网络下载的压缩工具很好地协作
     */
    private static void ZipCompress(String[] args) throws IOException {
        //创建test.zip文件输出流
        FileOutputStream f = new FileOutputStream("E:/test.zip");
        //文件输出流包装成以Adler32算法来计算和校验文件的校验和的输出流
        CheckedOutputStream csum = new CheckedOutputStream(f, new Adler32());
        //文件以zip格式写出
        ZipOutputStream zos = new ZipOutputStream(csum);
        //文件输出流包装缓冲功能
        BufferedOutputStream out = new BufferedOutputStream(zos);
        //加入文件注释
        zos.setComment("A test of Java Zipping");
        //接受命令行输入（文件路径）
        for (String arg : args) {
            System.out.println("Writing file " + arg);
            //文件以字符流读入
            BufferedReader in = new BufferedReader(new FileReader(arg));
            //以文件名创建一个zip实体放入ZipOutputStream
            zos.putNextEntry(new ZipEntry(arg));
            //逐字节读取文件写入缓冲输出流
            int c;
            while ((c = in.read()) != -1)
                out.write(c);
            //关闭字符输入流
            in.close();
            //清空缓冲输入流，准备再次写入
            out.flush();
        }
        out.close();
        //打印校验和的值
        System.out.println("CheckSum: " + csum.getChecksum().getValue());
        System.out.println("Reading file");
        FileInputStream fi = new FileInputStream("E:/test.zip");
        CheckedInputStream csumi = new CheckedInputStream(fi, new Adler32());
        ZipInputStream in2 = new ZipInputStream(csumi);
        BufferedInputStream bis = new BufferedInputStream(in2);
        ZipEntry ze;
        //解压文件方法1：通过getNextEntry方法遍历每个ZipEntry
        while ((ze = in2.getNextEntry()) != null) {
            System.out.println("Reading file " + ze);
            int x;
            while ((x = bis.read()) != -1)
                System.out.write(x);
        }
        if (args.length == 1)
            System.out.println("Checksum: " + csumi.getChecksum().getValue());
        bis.close();
        //解压文件方法2：通过ZipEntryIterator遍历每个ZipEntry
        ZipFile zf = new ZipFile("E:/test.zip");
        Enumeration<?> e = zf.entries();
        while (e.hasMoreElements()) {
            ZipEntry ze2 = (ZipEntry) e.nextElement();
            System.out.println("File: " + ze2);
        }
    }




}
