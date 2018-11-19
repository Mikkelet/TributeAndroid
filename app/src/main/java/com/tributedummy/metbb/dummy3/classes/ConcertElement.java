package com.tributedummy.metbb.dummy3.classes;

import com.tributedummy.metbb.dummy3.MainActivity;

import java.util.ArrayList;

public abstract class ConcertElement {

    protected String name;
    protected int image;
    protected float rating = 5;
    protected ArrayList<Review> reviews = new ArrayList<>();
    private ArrayList<Integer> photos;

    public ConcertElement(String name, int image) {
        this.name = name;
        this.image = image;

    }
    // methods
    public void addReview(Review review) {
        reviews.add(review);
    }
    public ArrayList<Concert> getConcerts() {
        ArrayList<Concert> concerts = new ArrayList<>();
        for (Concert c: MainActivity.concerts) {
            if(c.getArtist().getName().equals(this.getName()))
                concerts.add(c);
        }
        return concerts;
    }

    // getters
    public String getName() {
        return name;
    }
    public int getImage() {
        return image;
    }
    public float getRating() {
        return rating;
    }
    public ArrayList<Review> getReviews() {
        return reviews;
    }
    public ArrayList<Integer> getPhotos() {
        return photos;
    }

    // databinding
    public String getReviewsStrings()
    {
        return reviews.size()+" reviews";
    }
    public String getConcertsString(){
        return "200 concerts";
    }
}
