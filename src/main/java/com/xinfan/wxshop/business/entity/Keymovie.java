package com.xinfan.wxshop.business.entity;

public class Keymovie {
    private Integer keymovieId;

    private Integer filmId;

    private String name;

    private String picture;

    private String word;

    public Integer getKeymovieId() {
        return keymovieId;
    }

    public void setKeymovieId(Integer keymovieId) {
        this.keymovieId = keymovieId;
    }

    public Integer getFilmId() {
        return filmId;
    }

    public void setFilmId(Integer filmId) {
        this.filmId = filmId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture == null ? null : picture.trim();
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word == null ? null : word.trim();
    }
}