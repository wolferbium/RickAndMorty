package com.makarov.rickandmorty.presenter;

import com.makarov.rickandmorty.app.MainActivity;
import com.makarov.rickandmorty.app.MainView;
import com.makarov.rickandmorty.model.CharacterModel;
import com.makarov.rickandmorty.model.CharactersList;

import java.util.Collection;

import moxy.InjectViewState;
import moxy.MvpPresenter;

@InjectViewState
public class CharacterListPresenter extends MvpPresenter<MainView> {
    private CharactersList model;

    private int currentPage = 0;

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();

        this.model = new CharactersList();
        model.setPresenter(this);
        updateCharacterList();
    }

    public CharacterListPresenter() {

    }

    public void updateCharacterList() {
        ++currentPage;
        model.loadCharacters(currentPage, new CharactersList.afterLoadCharactersCallback() {
            @Override
            public void afterLoad(Collection<CharacterModel> characters){
                getViewState().update(characters);
            }
        });
    }
}
