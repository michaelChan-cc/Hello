package com.cc.file.zip;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class ZipUtils {

    public static ZipInputStream getZip(String urlPath) throws IOException {
        File file = null;
        BufferedInputStream bin = null;
        OutputStream out = null;
        HttpURLConnection httpURLConnection = null;
        // 统一资源
        URL url = new URL(urlPath);

        // 连接类的父类，抽象类
        URLConnection urlConnection = url.openConnection();
        // http的连接类
        httpURLConnection = (HttpURLConnection) urlConnection;

        // 设置字符编码
        httpURLConnection.setRequestProperty("Charset", "UTF-8");

        httpURLConnection.setDoOutput(true);// http正文内，因此需要设为true, 默认情况下是false;
        httpURLConnection.setDoInput(true);
        int responseCode = httpURLConnection.getResponseCode();//查看请求状态
        System.out.println(responseCode);
//           // 设定请求的方法，默认是GET
//           httpURLConnection.setRequestMethod("GET");

        // 打开到此 URL 引用的资源的通信链接（如果尚未建立这样的连接）。
        httpURLConnection.connect();

        // 文件大小
        int fileLength = httpURLConnection.getContentLength();
        System.out.println("filesize： " + fileLength);
        //后面参数要字符编码要设置，不然可能会读不到
        ZipInputStream zipI = new ZipInputStream(httpURLConnection.getInputStream(), Charset.forName("GBK"));
        return zipI;
    }

    public static void getZipFile(Map<String, byte[]> stringByte, OutputStream outputStream) throws IOException {
        //将文件打包成压缩文件
        ZipOutputStream zipout = new ZipOutputStream(outputStream);
        Set<Map.Entry<String, byte[]>> dataEntrys = stringByte.entrySet();
        for (Map.Entry<String, byte[]> data : dataEntrys) {
            InputStream bufferIn = new BufferedInputStream(new ByteArrayInputStream(data.getValue()));
            byte[] bs = new byte[1024];
            Arrays.fill(bs, (byte) 0);
            //创建压缩文件内的文件
            zipout.putNextEntry(new ZipEntry(data.getKey()));
            int len = -1;
            while ((len = bufferIn.read(bs)) > 0) {
                zipout.write(bs, 0, len);
            }
            bufferIn.close();
        }
        try {
            zipout.close();
        } catch (IOException e) {
        }
    }

    public static void getZipFile(Map<String, byte[]> stringByte) throws IOException {
        //将文件打包成压缩文件
        Set<Map.Entry<String, byte[]>> dataEntrys = stringByte.entrySet();
        for (Map.Entry<String, byte[]> data : dataEntrys) {
            InputStream bufferIn = new BufferedInputStream(new ByteArrayInputStream(data.getValue()));
            byte[] bs = new byte[1024];
            Arrays.fill(bs, (byte) 0);
            int len = -1;
            while ((len = bufferIn.read(bs)) > 0) {
            }
            bufferIn.close();
        }
    }
}