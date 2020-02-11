package com.makarov.rickandmorty.network;

import com.makarov.rickandmorty.model.CharacterModel;
import com.makarov.rickandmorty.model.PageModel;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface JSONPlaceholderApi {
    @GET("/api/character/{id}")
    public retrofit2.Call<CharacterModel> getCharacterByID(@Path("id") long id);

    @GET("/api/character")
    public retrofit2.Call<PageModel> getCharactersPage(@Query("page") int page);
}
