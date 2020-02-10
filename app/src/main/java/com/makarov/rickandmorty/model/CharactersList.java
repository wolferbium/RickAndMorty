package com.makarov.rickandmorty.model;

import com.makarov.rickandmorty.network.NetworkService;
import com.makarov.rickandmorty.presenter.CharacterListPresenter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CharactersList {
    private List<CharacterModel> characterList = new ArrayList<>();
    private CharacterListPresenter presenter;

    public CharactersList() {
    }

    public void setPresenter(CharacterListPresenter presenter) {
        this.presenter = presenter;
    }


    public void loadCharacters(int page, afterLoadCharactersCallback callback) {
        NetworkService.getRequestCharactersPage(page, new NetworkService.loadCharactersCallback() {
            @Override
            public void onLoad(Collection<CharacterModel> characterList) {
                addCharacters(characterList);
                callback.afterLoad(characterList);
            }
        });
    }

    public void addCharacters(Collection<CharacterModel> characters) {
        characterList.addAll(characters);
    }

    public interface afterLoadCharactersCallback {
        void afterLoad(Collection<CharacterModel> characters);
    }

    public CharacterModel getCharacter(int position) {
        return characterList.get(position);
    }

    public int getSize() {
        return characterList.size();
    }

    public boolean isEmpty() {
        return characterList.isEmpty();
    }
}
