package com.makarov.rickandmorty;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

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
        //characterAdapter.getRequestCharacter();
        characterAdapter.getRequestCharactersPage(1);

    }

    private void loadCharacters(){
        Collection<CharacterModel> characters = getCharacters();
        characterAdapter.setCharacterList(characters);
    }

    private Collection<CharacterModel> getCharacters(){
        return Arrays.asList(
                new CharacterModel("Char1","human","123",1),
                new CharacterModel("Char1","human","123",2),
                new CharacterModel("Char1","human","123",3),
                new CharacterModel("Char2", "nothuman", "456",4));
    }
}
