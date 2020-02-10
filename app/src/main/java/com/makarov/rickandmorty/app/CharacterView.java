package com.makarov.rickandmorty.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.makarov.rickandmorty.R;
import com.makarov.rickandmorty.model.CharacterModel;
import com.makarov.rickandmorty.model.CharactersList;
import com.makarov.rickandmorty.presenter.CharacterListPresenter;
import com.makarov.rickandmorty.presenter.CharacterPresenter;

public class CharacterView extends AppCompatActivity {

    private CharacterPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_info);

        initPresenter();

        ImageView image = findViewById(R.id.character_info_character_image);
        Glide.with(image).load(presenter.getImage()).into(image);

        TextView name = findViewById(R.id.character_info_character_name);
        name.setText(presenter.getName());

        TextView species = findViewById(R.id.character_info_character_species);
        species.setText(presenter.getSpecies());

        TextView status = findViewById(R.id.character_info_character_status);
        status.setText(presenter.getStatus());

        TextView URL = findViewById(R.id.character_info_character_url);
        URL.setText(presenter.getUrl());
    }


    private void initPresenter() {
        presenter = new CharacterPresenter((CharacterModel) getIntent().getSerializableExtra(MainActivity.EXTRA_CHARACTER));
    }
}
