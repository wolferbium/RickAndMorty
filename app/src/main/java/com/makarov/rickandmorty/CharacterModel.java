package com.makarov.rickandmorty;

public class CharacterModel {
    private String name;
    private String type;
    private int avatar;

    public CharacterModel(String name, String type, int avatar){
        this.name = name;
        this.type = type;
        this.avatar = avatar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getAvatar() {
        return avatar;
    }

    public void setAvatar(int avatar) {
        this.avatar = avatar;
    }
}
