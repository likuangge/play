package zuo.li.play.common.utils;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 * @Description: 文件工具类
 * @Author: zuo.li
 * @Date: 2020/1/6 10:39
 */
@Slf4j
public class FileUtils {

    /**
     * 文件最大值
     */
    public static final int FILEMAXSIZE = 1024 * 1024 * 1024;

    /**
     * 读取文件内容，作为字符串返回
     */
    public static String readFileAsString(String filePath) throws IOException {
        File file = new File(filePath);
        if (!file.exists()) {
            throw new FileNotFoundException(filePath);
        }

        if (file.length() > FILEMAXSIZE) {
            throw new IOException("File is too large");
        }

        StringBuilder stringBuilder = new StringBuilder((int)(file.length()));
        // 创建字节输入流
        FileInputStream fis = new FileInputStream(filePath);
        // 创建一个长度为10240的Buffer
        byte[] buffer = new byte[10240];
        // 用于保存实际读取的字节数
        int hasRead = 0;
        while ( (hasRead = fis.read(buffer)) > 0 ) {
            stringBuilder.append(new String(buffer, 0, hasRead));
        }
        fis.close();
        return stringBuilder.toString();
    }

    /**
     * 根据文件路径读取byte[] 数组
     */
    public static byte[] readFileByBytes(String filePath) throws IOException {
        File file = new File(filePath);
        if (!file.exists()) {
            throw new FileNotFoundException(filePath);
        } else {
            ByteArrayOutputStream bos = new ByteArrayOutputStream((int) file.length());
            BufferedInputStream in = null;

            try {
                in = new BufferedInputStream(new FileInputStream(file));
                short bufSize = 1024;
                byte[] buffer = new byte[bufSize];
                int len1;
                while (-1 != (len1 = in.read(buffer, 0, bufSize))) {
                    bos.write(buffer, 0, len1);
                }

                byte[] var7 = bos.toByteArray();
                return var7;
            } finally {
                try {
                    if (in != null) {
                        in.close();
                    }
                } catch (IOException var14) {
                    var14.printStackTrace();
                }
                bos.close();
            }
        }
    }

    /**
     * 从远程服务器读取文件
     *
     * @param fileUrl 文件路径
     * @return 文件流
     * @throws IOException 异常
     */
    public static byte[] readFileFromRemote(String fileUrl) {
        try {
            URL url = new URL(fileUrl);
            URLConnection urlConnection = url.openConnection();
            HttpURLConnection httpURLConnection = (HttpURLConnection) urlConnection;
            httpURLConnection.setConnectTimeout(5000);
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setRequestProperty("Charset", "UTF-8");
            httpURLConnection.connect();
            int fileLength = httpURLConnection.getContentLength();
            BufferedInputStream bufferedInputStream = new BufferedInputStream(httpURLConnection.getInputStream());
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(fileLength);
            byte[] buffer = new byte[1024];
            int len1;
            while (-1 != (len1 = bufferedInputStream.read(buffer, 0, 1024))) {
                byteArrayOutputStream.write(buffer, 0, len1);
            }
            return byteArrayOutputStream.toByteArray();
        } catch (Exception e) {
            log.error("错误信息:", e);
            throw new RuntimeException("获取远程文件失败");
        }
    }
}
