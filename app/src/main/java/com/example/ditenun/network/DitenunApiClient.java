package com.example.ditenun.network;

import android.text.TextUtils;

import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.Credentials;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DitenunApiClient {

    public static final String ENDPOINT = "http://mobile.ditenun.com/api/";
    public static final String BASE_URL = "http://mobile.ditenun.com";
    public static final String ACCESS_TOKEN = "Jt3nuN_20161130";
    private static Retrofit retrofit = null;

    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

    private static Retrofit.Builder builder =
            new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create());

    public static <S> S createService(
            Class<S> serviceClass) {
        try {
            TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
                public X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[0];
                }

                @Override
                public void checkClientTrusted(X509Certificate[] certs, String authType) {
                }

                @Override
                public void checkServerTrusted(X509Certificate[] certs, String authType) {
                }
            }};
            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new SecureRandom());

            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

            if (!httpClient.interceptors().contains(loggingInterceptor)) {
                httpClient.addInterceptor(loggingInterceptor)
                        .connectTimeout(600, TimeUnit.SECONDS)
                        .readTimeout(600, TimeUnit.SECONDS)
                        .writeTimeout(600, TimeUnit.SECONDS)
                        .hostnameVerifier((hostname, session) -> true)
                        .sslSocketFactory(sc.getSocketFactory(), new X509TrustManager() {
                            @Override
                            public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {

                            }

                            @Override
                            public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {

                            }

                            @Override
                            public X509Certificate[] getAcceptedIssuers() {
                                return new X509Certificate[0];
                            }
                        })
                        .build();

                builder.client(httpClient.build());
                retrofit = builder.build();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return retrofit.create(serviceClass);
    }
}
