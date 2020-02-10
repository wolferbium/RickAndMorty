package com.makarov.rickandmorty.presenter;

import com.makarov.rickandmorty.app.MainActivity;
import com.makarov.rickandmorty.model.CharacterModel;
import com.makarov.rickandmorty.model.CharactersList;

import java.util.Collection;

public class CharacterListPresenter {
    private MainActivity mainActivity;
    private final CharactersList model;

    private int currentPage = 0;

    public CharacterListPresenter(CharactersList model) {
        this.model = model;
        model.setPresenter(this);
    }

    public void attachView(MainActivity activity) {
        mainActivity = activity;
    }

    public void updateCharacterList() {
        ++currentPage;
        model.loadCharacters(currentPage, new CharactersList.afterLoadCharactersCallback() {
            @Override
            public void afterLoad(Collection<CharacterModel> characters){
                mainActivity.update(characters);
            }
        });
    }
}
