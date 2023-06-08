package com.cc.file.zip;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class ZIpTest {
    public static void main(String[] args) throws IOException {
//        String str = "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fhbimg.b0.upaiyun.com%2Fec93a13ee1dc1fdfb615cd24fae116d318809a6037347-6URJvT_fw658&refer=http%3A%2F%2Fhbimg.b0.upaiyun.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1642258837&t=096e9ef119eb1fb2d8cfcf33d0f8198d";
        String str = "http://map.foiadaas.cn:18080//downloadAllAttachment/1675334502383downloadAllAttachment.zip";


        try {
            ZipInputStream zipI=ZipUtils.getZip(str);
            ZipEntry entry;
            while((entry=zipI.getNextEntry())!=null) {
                System.out.println("filename :" + entry.getName());
//                byte[] data = IOUtils.getByte1(zipI); // 获取当前条目的字节数组
//                InputStream is = new ByteArrayInputStream(data);  //获取到zip内容的InputStream 之后就可以爱干嘛干嘛了
//                IOUtils.filter(is, "pdf");
            }
    }catch (Exception e){
            e.printStackTrace();
        }
    }
}
