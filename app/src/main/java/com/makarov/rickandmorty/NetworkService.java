package com.makarov.rickandmorty;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkService {
    private static final NetworkService INSTANCE = new NetworkService();
    private static final String BASE_URL = "https://rickandmortyapi.com";
    private Retrofit retrofit;

    private NetworkService(){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder client = new OkHttpClient.Builder()
                .addInterceptor(interceptor);

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client.build())
                .build();
    }

    public static NetworkService getInstance() {
        return INSTANCE;
    }

    public JSONPlaceholderApi getJSONApi() {
        return retrofit.create(JSONPlaceholderApi.class);
    }
}
