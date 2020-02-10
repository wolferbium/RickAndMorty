package com.makarov.rickandmorty.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.makarov.rickandmorty.R;
import com.makarov.rickandmorty.app.MainActivity;
import com.makarov.rickandmorty.model.CharacterModel;
import com.makarov.rickandmorty.ui.CharacterAdapter;

public class CharacterInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_info);

        Intent intent = getIntent();
        CharacterModel character = CharacterAdapter.getCharacter(intent.getIntExtra(MainActivity.EXTRA_CHARACTER_ID, -1));

        ImageView image = findViewById(R.id.character_info_character_image);
        Glide.with(image).load(character.getImage()).into(image);

        TextView name = findViewById(R.id.character_info_character_name);
        name.setText(character.getName());

        TextView species = findViewById(R.id.character_info_character_species);
        species.setText(character.getSpecies());

        TextView status = findViewById(R.id.character_info_character_status);
        status.setText(character.getStatus());

        TextView URL = findViewById(R.id.character_info_character_url);
        URL.setText(character.getUrl());
    }
}
