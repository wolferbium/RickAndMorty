package com.makarov.rickandmorty.presenter;

import com.makarov.rickandmorty.app.CharacterActivity;
import com.makarov.rickandmorty.app.CharacterView;
import com.makarov.rickandmorty.model.CharacterModel;
import com.makarov.rickandmorty.model.CharactersList;
import com.makarov.rickandmorty.network.NetworkService;

import moxy.InjectViewState;
import moxy.MvpPresenter;
import moxy.ViewStateProvider;

@InjectViewState
public class CharacterPresenter extends MvpPresenter<CharacterView> {

    public CharacterPresenter() {
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
    }

    public void setId(int characterId) {
        NetworkService.getCharacterById(characterId, new NetworkService.loadCharacterCallback() {
            @Override
            public void onLoad(CharacterModel character) {
                getViewState().setName(character.getName());
                getViewState().setImage(character.getImage());
                getViewState().setSpecies(character.getSpecies());
                getViewState().setStatus(character.getSpecies());
                getViewState().setUrl(character.getUrl());
            }
        });
    }
}
