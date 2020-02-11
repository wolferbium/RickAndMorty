package com.makarov.rickandmorty.app;

import androidx.appcompat.graphics.drawable.DrawerArrowDrawable;

import com.makarov.rickandmorty.model.CharacterModel;

import java.util.Collection;

import moxy.MvpView;
import moxy.viewstate.strategy.alias.AddToEnd;

public interface MainView extends MvpView {
    @AddToEnd
    void onCharacterClick(int characterId);

    @AddToEnd
    void update(Collection<CharacterModel> characters);
}
