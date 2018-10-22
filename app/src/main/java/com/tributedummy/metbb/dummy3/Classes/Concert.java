package com.tributedummy.metbb.dummy3.Classes;

import com.tributedummy.metbb.dummy3.R;

import java.util.ArrayList;


public class Concert {
    private Artist artist;
    private Venue venue;
    private String date;
    private double rating;
    private ArrayList<Review> reviews;
    private ArrayList<Integer> photos;
    private ConcertStatus status = ConcertStatus.UPCOMING;

    public Concert(Artist artist, Venue venue) {
        this.artist = artist;
        this.venue = venue;
        this.rating = 5;
        this.date = "Sep. 2, 2018";
        reviews = new ArrayList<>();
        photos = new ArrayList<>();

        // TODO remove
        photos.add(R.mipmap.warondrugs);
        photos.add(R.mipmap.warondrugs);
        photos.add(R.mipmap.warondrugs);
    }



    public void addReview(String review, double rating)
    {
        Review r = new Review(new User("Mikkel"), review, rating);
        reviews.add(r);
    }

    // getters & setters
    public Artist getArtist() {
        return artist;
    }

    public Venue getVenue() {
        return venue;
    }

    public String getDate() {
        return date;
    }

    public double getRating() {
        return rating;
    }

    public ArrayList<Review> getReviews() {
        return reviews;
    }

    public ArrayList<Integer> getPhotos() {
        return photos;
    }

    public ConcertStatus getStatus() {
        return status;
    }

    public void setStatus(ConcertStatus status) {
        this.status = status;
    }
}
