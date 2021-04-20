package com.movie.moviecatalogservice.models;

public class MovieInfo {
    private int movieId;
    private String name;
    private String desc;

    public MovieInfo() {
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public MovieInfo(int movieId, String name) {
        this.movieId = movieId;
        this.name = name;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
