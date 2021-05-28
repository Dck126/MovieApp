package com.example.movieapp.models.movie;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NowPlaying {
    private int id;

    @SerializedName("poster_path")
    @Expose
    private String posterImage;

    private String title;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPosterImage() {
        return posterImage;
    }

    public void setPosterImage(String posterImage) {
        this.posterImage = posterImage;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
