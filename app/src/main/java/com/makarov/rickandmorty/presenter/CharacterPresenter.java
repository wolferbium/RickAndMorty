package com.makarov.rickandmorty.presenter;

import com.makarov.rickandmorty.app.CharacterView;
import com.makarov.rickandmorty.app.MainActivity;
import com.makarov.rickandmorty.model.CharacterModel;

public class CharacterPresenter {
    private CharacterView view;
    private final CharacterModel model;

    public CharacterPresenter(CharacterModel model) {
        this.model = model;
    }

    public void attachView(CharacterView view) {
        this.view = view;
    }

    public String getImage() {
        return model.getImage();
    }

    public String getName() {
        return model.getName();
    }

    public String getSpecies() {
        return model.getSpecies();
    }

    public String getStatus() {
        return model.getStatus();
    }

    public String getUrl() {
        return model.getUrl();
    }

}
