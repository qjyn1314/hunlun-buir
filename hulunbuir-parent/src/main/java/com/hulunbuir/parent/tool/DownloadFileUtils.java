package com.hulunbuir.parent.tool;

import com.hulunbuir.parent.exception.HulunBuirException;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * <p>
 * explain:
 * </p>
 *
 * @author wangjunming
 * @since 2020/5/7 10:45
 */
@Slf4j
public class DownloadFileUtils {


    private static final int BUFFER_SIZE = 4096;

    /**
     * 浏览器下载服务器上的文件
     *
     * @param fileURL  服务器上的文件路径
     * @param response 返回
     * @param fileName 文件名称
     * @author wangjunming
     * @since 2020/4/29 12:46
     */
    private static void downloadFile(String fileURL, HttpServletResponse response, String fileName) throws HulunBuirException {
        HttpURLConnection httpConn = null;
        OutputStream outputStream = null;
        InputStream inputStream = null;
        try {
            URL url = new URL(fileURL);
            httpConn = (HttpURLConnection) url.openConnection();
            int responseCode = httpConn.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                String contentType = httpConn.getContentType();
                int contentLength = httpConn.getContentLength();
                log.info("Content-Type = " + contentType);
                log.info("Content-Length = " + contentLength);
                log.info("fileName = " + fileName);
                inputStream = httpConn.getInputStream();
                response.reset();
                response.addHeader("Content-Disposition", "attachment;filename=" + java.net.URLEncoder.encode(fileName, "utf-8"));
                outputStream = new BufferedOutputStream(response.getOutputStream());
                response.setContentType("application/octet-stream");
                response.setCharacterEncoding("utf-8");
                int bytesRead = -1;
                byte[] buffer = new byte[BUFFER_SIZE];
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }
                outputStream.flush();
                log.info("File downloaded!!!");
            } else {
                log.error("访问资源失败!!!资源链接是：{}", fileURL);
                log.error("No file to download. Server replied HTTP code: " + responseCode);
            }
        } catch (Exception e) {
            log.error("访问资源失败!!!资源链接是：{}", fileURL);
            log.error("下载文件异常：", e);
            HulunBuirException.build(e.getMessage());
        } finally {
            if (httpConn != null) {
                httpConn.disconnect();
            }
            try {
                if (outputStream != null) {
                    outputStream.close();
                }
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (Exception e) {
                log.error("关闭文件流异常", e);
            }
        }
    }

}
