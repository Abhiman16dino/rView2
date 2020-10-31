package com.example.rview1;

public class model {

    String imageURL;
    String username;
    int likes;

    public model(String imageURL, String username, int likes) {
        this.imageURL = imageURL;
        this.username = username;
        this.likes = likes;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }
}
