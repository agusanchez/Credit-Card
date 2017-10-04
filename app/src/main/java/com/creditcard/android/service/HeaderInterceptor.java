package com.creditcard.android.service;

import com.creditcard.android.BuildConfig;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Agustin on 3/10/2017.
 */

public class HeaderInterceptor implements Interceptor {

    private static final String PUBLIC_KEY  = "public_key";

    public HeaderInterceptor() {
    }

    @Override
    public Response intercept(Chain chain) throws IOException {

        Request original = chain.request();
        HttpUrl originalHttpUrl = original.url();

        HttpUrl url = originalHttpUrl.newBuilder()
                .setQueryParameter(PUBLIC_KEY, BuildConfig.PUBLIC_KEY)
                .build();

        Request.Builder requestBuilder = original.newBuilder()
                .url(url);

        Request request = requestBuilder.build();
        return chain.proceed(request);
    }
}
