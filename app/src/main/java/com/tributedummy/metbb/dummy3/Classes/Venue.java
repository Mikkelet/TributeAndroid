package com.tributedummy.metbb.dummy3.Classes;

import android.media.Image;

import java.util.ArrayList;

public class Venue {
    private String name;
    private double rating;
    private int image;
    private ArrayList<Review> reviews;

    public Venue(String name, int image) {
        this.name = name;
        this.image = image;
        reviews = new ArrayList<>();
    }

    public void addReview(String review, double rating)
    {
        Review r = new Review(new User("Mikkel"), review, rating);
        reviews.add(r);
    }

    // getters
    public String getName() {
        return name;
    }

    public double getRating() {
        return rating;
    }

    public int getImage() {
        return image;
    }

    public ArrayList<Review> getReviews() {
        return reviews;
    }
}
