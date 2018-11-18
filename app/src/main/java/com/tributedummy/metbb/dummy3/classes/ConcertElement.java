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
    public void addReview(String review, double rating) {
        Review r = new Review(new User("Mikkel"), review, rating);
        reviews.add(r);

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
}
