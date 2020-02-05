package com.makarov.rickandmorty;

import retrofit2.http.GET;
import retrofit2.http.Path;

public interface JSONPlaceholderApi {
    @GET("/api/character/{id}")
    public retrofit2.Call<CharacterModel> getCharacterWithID(@Path("id") long id);
}
