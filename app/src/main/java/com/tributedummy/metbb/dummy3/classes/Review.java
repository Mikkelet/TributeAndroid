package com.tributedummy.metbb.dummy3.classes;

import java.time.LocalDate;


public class Review {
    private User submittedBy;
    private String review;
    private double rating;
    private LocalDate localDate;
    private int favourites;

    public Review(User submittedBy, String review, double rating) {
        this.submittedBy = submittedBy;
        this.review = review;
        this.rating = rating;
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

    public double getRating() {
        return rating;
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
}
