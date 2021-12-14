package com.cc.chr;

import okhttp3.Request;
import okhttp3.RequestBody;

public class OkHttpHelper {

    public static Request.Builder builder = new Request.Builder();

    /**
     * 自动组装REQ
     * @param url
     * @param requestBody
     * @return
     */
    public static Request getReq(String url, RequestBody requestBody){
        return builder
                .addHeader("Content-Type", "json")
                .url(url)
                .post(requestBody)
                .build();
    }
}
