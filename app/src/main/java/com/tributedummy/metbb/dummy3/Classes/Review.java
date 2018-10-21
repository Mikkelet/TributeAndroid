package com.tributedummy.metbb.dummy3.Classes;

public class Review {
    private User submittedBy;
    private String review;
    private double rating;
    private String date;
    private int favourites;

    public Review(User submittedBy, String review, double rating) {
        this.submittedBy = submittedBy;
        this.review = review;
        this.rating = rating;
        //TODO implement date
        date = "September 2, 2018";
        favourites = 3;
    }

    public User getSubmittedBy() {
        return submittedBy;
    }

    public String getReview() {
        return review;
    }

    public double getRating() {
        return rating;
    }

    public String getDate() {
        return date;
    }

    public int getFavourites() {
        return favourites;
    }
}
