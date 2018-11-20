package com.tributedummy.metbb.dummy3.classes;

import java.time.LocalDate;


public class Review {
    private User submittedBy;
    private String review = "";
    private float ratingArtist;
    private float ratingVenue;
    private LocalDate localDate;
    private int favourites;
    private Concert concert;

    public Review(User submittedBy,Concert concert, String review, float ratingArtist, float ratingVenue) {
        this.submittedBy = submittedBy;
        this.review = review;
        this.ratingArtist = ratingArtist;
        this.ratingVenue = ratingVenue;
        this.concert = concert;
        localDate = LocalDate.now();
        //TODO implement date
        favourites = 3;
    }

    public User getSubmittedBy() {
        return submittedBy;
    }

    public String getReview() {
        return review;
    }

    public float getRatingTotal() {
        return (ratingArtist+ratingVenue)/2;
    }

    public float getRatingArtist() {
        return ratingArtist;
    }

    public float getRatingVenue() {
        return ratingVenue;
    }

    public String getDate() {
        String date;
        String month = localDate.getMonth().name().charAt(0)+""+localDate.getMonth().name().substring(1,3).toLowerCase();
        date = month+". "+localDate.getDayOfMonth()+", "+localDate.getYear();
        return date;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public int getFavourites() {
        return favourites;
    }

    public boolean hasText() {
        // return true if review has actual text
        return !review.equals("");
    }

}
