package com.example.ditenun.network;

import java.io.IOException;

import okhttp3.Credentials;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class BasicAuthInterceptor implements Interceptor {

    private String credential;

    public BasicAuthInterceptor(String consumerKey, String consumerSecret) {
        this.credential = Credentials.basic(consumerKey, consumerSecret);
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Request authenticateRequest = request.newBuilder()
                .header("Authorization", credential).build();
        return chain.proceed(authenticateRequest);
    }

}
