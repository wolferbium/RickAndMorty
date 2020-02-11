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

import moxy.MvpAppCompatActivity;
import moxy.presenter.InjectPresenter;

public class CharacterActivity extends MvpAppCompatActivity implements CharacterView {

    @InjectPresenter
    CharacterPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_info);

        initPresenter();

        //initPresenter();

        /*

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
         */
    }

    @Override
    public void setName(String name) {
        TextView nameView = findViewById(R.id.character_info_character_name);
        nameView.setText(name);
    }

    @Override
    public void setSpecies(String species) {
        TextView speciesView = findViewById(R.id.character_info_character_species);
        speciesView.setText(species);
    }

    @Override
    public void setImage(String image) {
        ImageView imageView = findViewById(R.id.character_info_character_image);
        Glide.with(imageView).load(image).into(imageView);
    }

    @Override
    public void setStatus(String status) {
        TextView statusView = findViewById(R.id.character_info_character_status);
        statusView.setText(status);
    }

    @Override
    public void setUrl(String URL) {
        TextView URLView = findViewById(R.id.character_info_character_url);
        URLView.setText(URL);
    }

    private void initPresenter() {
        presenter.setId(getIntent().getIntExtra(MainActivity.EXTRA_CHARACTER_ID, 0));
    }
}
