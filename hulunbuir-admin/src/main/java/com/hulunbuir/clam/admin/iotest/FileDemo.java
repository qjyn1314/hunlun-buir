package com.hulunbuir.clam.admin.iotest;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;

/**
 * <p>
 * explain:
 * </p>
 *
 * @author wangjunming
 * @since 2020/5/8 16:56
 */
public class FileDemo {

    public void FileDemoMethods001() throws IOException {
        File file1 = new File("F:\\IDEA_entertainment\\hunlun-buir\\hulunbuir-admin\\src\\main\\java\\com\\hulunbuir\\clam\\admin\\iotest\\IoTest.java");
        //目录分隔符
        final String pathSeparator = File.pathSeparator;

        File file2 = new File("file2.txt");
        //在指定的目录下创建文件，如果给文件已经存在，则不创建，返回false
        //和输出流不一样，输出刘对象已建立创建文件，而文件已经存在，会覆盖
        final boolean newFile = file2.createNewFile();

        //删除文件，返回值主要是用于判断
        final boolean delete = file2.delete();
        //如果代码发生异常，出现了垃圾文件，则使用，运行结束的时候删除文件  deleteOnExit
//        file2.deleteOnExit();
        //判断是否存在
        final boolean exists = file2.exists();

        System.out.println("file2"+file2);
        System.out.println("newFile"+newFile);
        System.out.println("delete"+delete);
        System.out.println("exists"+exists);

        System.out.println("-------------判断信息-------------");
        //创建文件夹测试
        File file3 = new File("F:\\IDEA_entertainment\\hunlun-buir\\hulunbuir-admin\\src\\main\\java\\com\\hulunbuir\\clam\\admin\\iotest\\FileDemo.java");

        //首先需要判断封装的文件对象是否存在
        final boolean exists1 = file3.exists();
        System.out.println("exists1:"+exists1);
        //创建文件
//        final boolean newFile1 = file3.createNewFile();

        //创建文件夹
        final boolean mkdir = file3.mkdir();
        System.out.println("mkdir:"+mkdir);

        //判断是否是文件
        final boolean isFile = file3.isFile();
        System.out.println("isFile:"+isFile);

        //判断是否是文件夹
        final boolean directory = file3.isDirectory();
        System.out.println("directory:"+exists1);

        //判断是否是绝对路径
        final boolean isAbsolute = file3.isAbsolute();
        System.out.println("isAbsolute:"+isAbsolute);

        System.out.println("-------------获取信息-------------");

        final String path = file3.getPath();
        System.out.println("path:"+path);
        final String absolutePath = file3.getAbsolutePath();
        System.out.println("absolutePath:"+absolutePath);
        final String parent = file3.getParent();
        System.out.println("parent:"+parent);
String price = "0.4";

        final BigDecimal bigDecimal = new BigDecimal(price);
        System.out.println(bigDecimal);


    }


}
