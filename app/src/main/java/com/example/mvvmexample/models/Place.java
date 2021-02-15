package com.example.mvvmexample.models;

public class Place {
    private String imageName;
    private String imageURL;

    public Place(String imageURL, String imageName) {
        this.imageURL = imageURL;
        this.imageName = imageName;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
}
