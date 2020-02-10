package com.makarov.rickandmorty.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CharacterModel implements Serializable {

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
    private int id;

    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("url")
    @Expose
    private String url;

    public CharacterModel(String name, String species, String image, int id){
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
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
