package com.makarov.rickandmorty.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.makarov.rickandmorty.model.CharacterModel;
import com.makarov.rickandmorty.model.InfoModel;

import java.util.ArrayList;

public class PageModel {

    @SerializedName("info")
    @Expose
    private InfoModel info;

    @SerializedName("results")
    @Expose
    private ArrayList<CharacterModel> results;

    public InfoModel getInfo() {
        return info;
    }

    public void setInfo(InfoModel info) {
        this.info = info;
    }

    public ArrayList<CharacterModel> getResults() {
        return results;
    }

    public void setResults(ArrayList<CharacterModel> results) {
        this.results = results;
    }
}
