package com.hulunbuir.admin.worktest;

import cn.hutool.core.io.IoUtil;
import lombok.extern.slf4j.Slf4j;

import java.io.*;

/**
 * <p>
 * explain:
 * </p>
 *
 * @author wangjunming
 * @since 2020/12/16 14:28
 */
@Slf4j
public class JavaTest {

    public static void main(String[] args) throws IOException {
        //页码的数量
        int pageNo = 2;
        pageNo = (pageNo - 1) < 0 ? 0 : pageNo - 1;
        System.out.println(pageNo);
        int pageNum = 2;
        pageNum = Math.max((pageNum - 1), 0);
        log.info("pageNum:{}",pageNum);
        String path = "/jpg";
        String fileName = "fileTest.txt";
        String filePath = path + File.separator + fileName;
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }
        final File fileTmp = new File(filePath);
        log.info("fileTmp:{}",fileTmp.getAbsoluteFile());
        File sqlFile = new File("F:\\202010311629备份的博客数据库halodb.sql");
        InputStream input = new FileInputStream(sqlFile);
        OutputStream out = new FileOutputStream(fileTmp);
        IoUtil.copy(input,out);
        log.info("out:{}",out);
        out.flush();
        if(input!=null){
            input.close();
        }
        if(out!=null){
            out.close();
        }
        try {
            fileTmp.delete();
        } catch (Exception e) {
            log.error("文件删除失败");
        }
    }











}
