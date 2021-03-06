package com.makarov.rickandmorty.network;

import android.util.Log;

import com.makarov.rickandmorty.model.CharacterModel;
import com.makarov.rickandmorty.model.PageModel;

import java.util.Collection;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
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

    public static void getCharacterById(int characterId, loadCharacterCallback callback) {
        Log.w("RequestCharactersPage", "Start");
        getInstance()
                .getJSONApi()
                .getCharacterByID(characterId)
                .enqueue(new Callback<CharacterModel>() {
                    @Override
                    public void onResponse(Call<CharacterModel> call, Response<CharacterModel> response) {
                        CharacterModel character = response.body();
                        callback.onLoad(character);
                    }

                    @Override
                    public void onFailure(Call<CharacterModel> call, Throwable t) {
                        t.printStackTrace();
                    }
                });
    }

    public static void getCharactersPage(int page, loadPageCallback callback) {
        Log.w("RequestCharactersPage", "Start");
        getInstance()
                .getJSONApi()
                .getCharactersPage(page)
                .enqueue(new Callback<PageModel>() {
                    @Override
                    public void onResponse(Call<PageModel> call, Response<PageModel> response) {
                        PageModel page = response.body();
                        callback.onLoad(page.getResults());
                    }

                    @Override
                    public void onFailure(Call<PageModel> call, Throwable t) {
                        t.printStackTrace();
                    }
                });
    }

    public interface loadPageCallback {
        void onLoad(Collection<CharacterModel> characters);
    }

    public interface loadCharacterCallback {
        void onLoad(CharacterModel character);
    }

    public static NetworkService getInstance() {
        return INSTANCE;
    }

    public JSONPlaceholderApi getJSONApi() {
        return retrofit.create(JSONPlaceholderApi.class);
    }
}
