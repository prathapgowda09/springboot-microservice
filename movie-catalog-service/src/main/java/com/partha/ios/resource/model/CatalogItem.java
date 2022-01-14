package com.partha.ios.resource.model;

public class CatalogItem {

    private String name;
    private String movieDesc;
    private int rating;

    public CatalogItem() {
    }
    public CatalogItem(String name, String movieDesc, int rating) {
        this.name = name;
        this.movieDesc = movieDesc;
        this.rating = rating;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMovieDesc() {
        return movieDesc;
    }

    public void setMovieDesc(String movieDesc) {
        this.movieDesc = movieDesc;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
