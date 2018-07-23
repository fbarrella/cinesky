package com.example.bbrothers.cinesky;

import java.util.ArrayList;

public class Filme {
    private String title;
    private String overview;
    private String duration;
    private String release_year;
    private String cover_url;
    private ArrayList<String> backdrops_url;
    private String id;

    public Filme(String title,
                 String overview,
                 String duration,
                 String release_year,
                 String cover_url,
                 ArrayList<String> backdrops_url,
                 String id){
        this.title = title;
        this.overview = overview;
        this.duration = duration;
        this.release_year = release_year;
        this.cover_url = cover_url;
        this.backdrops_url = backdrops_url;
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getRelease_year() {
        return release_year;
    }

    public void setRelease_year(String release_year) {
        this.release_year = release_year;
    }

    public String getCover_url() {
        return cover_url;
    }

    public void setCover_url(String cover_url) {
        this.cover_url = cover_url;
    }

    public ArrayList<String> getBackdrops_url() {
        return backdrops_url;
    }

    public void setBackdrops_url(ArrayList<String> backdrops_url) {
        this.backdrops_url = backdrops_url;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}