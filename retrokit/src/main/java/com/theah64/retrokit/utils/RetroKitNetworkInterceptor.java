package com.theah64.retrokit.utils;

import android.support.annotation.NonNull;
import android.util.Log;

import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.nio.charset.Charset;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.Buffer;

/**
 * Created by theapache64 on 14/2/18.
 */

public class RetroKitNetworkInterceptor implements Interceptor {

    private StringBuilder curlCommandBuilder;
    private final Charset UTF8 = Charset.forName("UTF-8");
    private String tag = null;

    public RetroKitNetworkInterceptor() {
    }

    public RetroKitNetworkInterceptor(String tag) {
        this.tag = tag;
    }


    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {

        Request request = chain.request();

        curlCommandBuilder = new StringBuilder("");
        // add cURL command
        curlCommandBuilder.append("curl ");
        curlCommandBuilder.append("-X ");
        // add method
        curlCommandBuilder.append(request.method().toUpperCase() + " ");
        // adding headers
        for (String headerName : request.headers().names()) {
            addHeader(headerName, request.headers().get(headerName));
        }

        // adding request body
        RequestBody requestBody = request.body();
        if (request.body() != null) {
            Buffer buffer = new Buffer();
            assert requestBody != null;
            requestBody.writeTo(buffer);
            Charset charset;
            MediaType contentType = requestBody.contentType();
            if (contentType != null) {
                addHeader("Content-Type", request.body().contentType().toString());
                charset = contentType.charset(UTF8);
                assert charset != null;
                curlCommandBuilder.append(" -d '").append(buffer.readString(charset)).append("'");
            }
        }

        // add request URL
        curlCommandBuilder.append(" \"").append(request.url().toString()).append("\"");
        curlCommandBuilder.append(" -L | jq '.'");

        CurlPrinter.print(tag, request.url().toString(), curlCommandBuilder.toString());
        return chain.proceed(request);
    }

    private void addHeader(String headerName, String headerValue) {
        curlCommandBuilder.append("-H " + "\"").append(headerName).append(": ").append(headerValue).append("\" ");
    }

    public static class CurlPrinter {

        /**
         * Drawing toolbox
         */
        private static final String SINGLE_DIVIDER = "────────────────────────────────────────────";

        private static String sTag = "CURL";

        static void print(@Nullable String tag, String url, String msg) {
            // setting tag if not null
            if (tag != null)
                sTag = tag;

            String logMsg = "\n" + "\n" +
                    "URL: " + url +
                    "\n" +
                    SINGLE_DIVIDER +
                    "\n" +
                    msg +
                    " " +
                    " \n" +
                    SINGLE_DIVIDER +
                    " \n ";
            log(logMsg);
        }

        private static void log(String msg) {
            Log.d(sTag, msg);
        }
    }
}
