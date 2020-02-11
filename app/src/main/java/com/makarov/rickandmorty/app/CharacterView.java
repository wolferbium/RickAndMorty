package com.makarov.rickandmorty.app;

import moxy.MvpView;
import moxy.viewstate.strategy.alias.AddToEnd;

public interface CharacterView extends MvpView {
    @AddToEnd
    void setName(String name);

    @AddToEnd
    void setSpecies(String species);

    @AddToEnd
    void setImage(String image);

    @AddToEnd
    void setStatus(String status);

    @AddToEnd
    void setUrl(String url);
}
