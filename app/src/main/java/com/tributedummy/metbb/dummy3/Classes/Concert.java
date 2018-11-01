package com.tributedummy.metbb.dummy3.Classes;

import com.tributedummy.metbb.dummy3.R;

import java.util.ArrayList;
import java.time.*;
import java.util.Random;


public class Concert {
    private Artist artist;
    private Venue venue;
    private double rating;
    private ArrayList<Review> reviews;
    private ArrayList<Integer> photos;
    private ConcertStatus status;
    private LocalDate localDate;

    public Concert(Artist artist, Venue venue) {
        this.artist = artist;
        this.venue = venue;
        this.rating = 5;
        reviews = new ArrayList<>();
        photos = new ArrayList<>();
        status = ConcertStatus.TODAY;
        localDate = LocalDate.now();

        // TODO remove
        // subtract or add days to test upcoming concerts
        int rndOne = new Random().nextInt(3);
        int rndTwo = new Random().nextInt(3);
        long newDays = rndOne - rndTwo;
        if(newDays < 0)
        {
            localDate = localDate.minusDays(newDays);
            status = ConcertStatus.UPCOMING;
        }else if(newDays > 0){
            localDate = localDate.plusDays(newDays);
            status = ConcertStatus.DONE;
        }

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
        String returnDate;
        String month = localDate.getMonth().name().charAt(0)+""+localDate.getMonth().name().substring(1,3).toLowerCase();
        returnDate = month+". "+localDate.getDayOfMonth()+", "+localDate.getYear();
        return returnDate;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    private void updateStatus(LocalDate now)
    {
        if(localDate.isAfter(now))
        {
            status = ConcertStatus.DONE;
        }else if(localDate == now)
        {
            status = ConcertStatus.TODAY;
        }else if(localDate.isBefore(now))
        {
            status = ConcertStatus.UPCOMING;
        }
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
}
