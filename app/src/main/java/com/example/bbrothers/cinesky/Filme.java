package com.example.bbrothers.cinesky;

public class Filme {
    private String title;
    private String cover_url;


    public Filme(String title, String cover_url){
        this.title = title;
        this.cover_url = cover_url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCover_url() {
        return cover_url;
    }

    public void setCover_url(String cover_url) {
        this.cover_url = cover_url;
    }
}
