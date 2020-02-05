package com.makarov.rickandmorty;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView charactersRecycleView;
    private CharacterAdapter characterAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        charactersRecycleView = findViewById(R.id.list_characters);
        charactersRecycleView.setLayoutManager(new LinearLayoutManager(this));
        characterAdapter = new CharacterAdapter();
        charactersRecycleView.setAdapter(characterAdapter);

        //loadCharacters();
        getRequestCharacters();

    }

    private void loadCharacters(){
        Collection<CharacterModel> characters = getCharacters();
        characterAdapter.setCharacterList(characters);
    }

    private void getRequestCharacters() {

        NetworkService.getInstance()
                .getJSONApi()
                .getCharacterWithID(1)
                .enqueue(new Callback<CharacterModel>() {
                    @Override
                    public void onResponse(Call<CharacterModel> call, Response<CharacterModel> response){
                        CharacterModel character = response.body();

                        characterAdapter.addCharacter(character);
                    }

                    @Override
                    public void onFailure(Call<CharacterModel> call, Throwable t) {
                        t.printStackTrace();
                    }
                });
    }

    private Collection<CharacterModel> getCharacters(){
        return Arrays.asList(
                new CharacterModel("Char1","human",123),
                new CharacterModel("Char1","human",123),
                new CharacterModel("Char1","human",123),
                new CharacterModel("Char2", "nothuman", 456));
    }
}
