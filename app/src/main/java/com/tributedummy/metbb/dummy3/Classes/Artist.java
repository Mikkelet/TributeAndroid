package com.tributedummy.metbb.dummy3.Classes;

import java.util.ArrayList;

public class Artist {

    private String name;
    private int image;
    private double rating;
    private ArrayList<Review> reviews;

    public Artist(String name, int image) {
        this.name = name;
        this.image = image;
        this.rating = 5;
        reviews = new ArrayList<>();
    }


    public String getName() {
        return name;
    }

    public int getImage() {
        return image;
    }

    public double getRating() {
        return rating;
    }

    public ArrayList<Review> getReviews() {
        return reviews;
    }

    public void addReview(String review, double rating)
    {
        Review r = new Review(new User("Mikkel"), review, rating);
        reviews.add(r);

    }
}
