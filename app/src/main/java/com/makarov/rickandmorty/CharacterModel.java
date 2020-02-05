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

    @SerializedName("image")
    @Expose
    private String image;

    @SerializedName("id")
    @Expose
    private long id;

    public CharacterModel(String name, String species, String image, long id){
        this.name = name;
        this.species = species;
        //this.image = image;
        this.id = id;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
