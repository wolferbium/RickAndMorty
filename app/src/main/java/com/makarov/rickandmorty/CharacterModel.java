package com.makarov.rickandmorty;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CharacterModel {

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("species")
    @Expose
    private String species;

    @SerializedName("avatar")
    @Expose
    private int avatar;

    @SerializedName("id")
    @Expose
    private long id;

    public CharacterModel(String name, String species, int avatar){
        this.name = name;
        this.species = species;
        this.avatar = avatar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public int getAvatar() {
        return avatar;
    }

    public void setAvatar(int avatar) {
        this.avatar = avatar;
    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
