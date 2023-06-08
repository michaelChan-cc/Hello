package com.cc.file.zip;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;


public class IOUtils {
    static Logger logger = LoggerFactory.getLogger(IOUtils.class);

    private static String checkType(String xxxx) {
        switch (xxxx) {
            case "FFD8FF":
                logger.debug("类型是：JPEG" );
                return "photo";
            case "89504E":
                logger.debug("类型是：PNG");
                return "photo";
            case "255044":
                logger.debug("类型是：PDF");
                return "pdf";
            default:
                logger.debug("文件头："+xxxx);
                return null;
        }
    }
    public static boolean filter(InputStream in,String type) throws IOException {
        byte[] b = new byte[3];
        try {
            in.read(b, 0, b.length);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        String xxx = byteArrToHex(b);
        logger.error("文件类型++++=="+xxx);
        xxx = xxx.toUpperCase();
        if(type.equals(checkType(xxx))) {
            return true;
        }
        return false;
    }

    private final static char[] hexArray = "0123456789ABCDEF".toCharArray();
    public static String byteArrToHex(byte... bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }

    public static byte[] getByte1(ZipInputStream zipI) {
        try {
            ByteArrayOutputStream bout = new ByteArrayOutputStream();
            byte[] temp = new byte[1024];
            byte[] buf = null;
            int length = 0;
            while ((length = zipI.read(temp, 0, 1024)) != -1) {
                bout.write(temp, 0, length);
            }
            buf = bout.toByteArray();
            bout.close();
            return buf;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /*public static Map<String, byte[]> getByte(List<String> urlPath) {
        Map<String, byte[]> stringMap = new HashMap<String, byte[]>();
        byte[] data = new byte[1024];
        ByteArrayOutputStream outputStream = null;
        InputStream inputStream = null;
        try {
            for (String string : urlPath) {
                //根据文件访问地址获取输入流
                URL url = new URL(string);
                URLConnection urlConnection = url.openConnection();
                inputStream = urlConnection.getInputStream();

                ZipInputStream zin = new ZipInputStream(inputStream, Charset.forName("gbk"));
                ZipEntry ze;
                while((ze = zin.getNextEntry()) != null){
                    if(ze.toString().endsWith("jpg")){
                        System.out.println(ze.getName());
                        BufferedReader br = new BufferedReader(
                                new InputStreamReader(zf.getInputStream(ze)));
                        String line;
                        while((line = br.readLine()) != null){
//                    System.out.println(line.toString());
                        }
                        br.close();
                    }
                    System.out.println();
                }

                outputStream = new ByteArrayOutputStream();
                while (inputStream.read(data) != -1) {
                    outputStream.write(data);
                }
                //当前时间字符创
                SimpleDateFormat timeName = new SimpleDateFormat("yyyyMMddhhmmss");
                //UUID
                String uuidName = UUID.randomUUID().toString().replace("-", "");
                stringMap.put(timeName.format(new Date()) + uuidName + ".jpg", outputStream.toByteArray());
            }
            return stringMap;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }*/
}
