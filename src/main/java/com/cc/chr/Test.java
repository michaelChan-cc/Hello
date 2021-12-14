package com.cc.chr;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.BufferedSink;

import javax.annotation.Nullable;
import java.io.IOException;

public class Test {
    public static void main(String[] args) {
        OkHttpHelper.getReq("www.baidu.com", new RequestBody() {
            @Nullable
            @Override
            public MediaType contentType() {
                return null;
            }

            @Override
            public void writeTo(BufferedSink bufferedSink) throws IOException {

            }
        });
    }
}
