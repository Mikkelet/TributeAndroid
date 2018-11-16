package com.tributedummy.metbb.dummy3;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TableLayout;
import android.widget.TextView;

import com.tributedummy.metbb.dummy3.classes.Concert;
import com.tributedummy.metbb.dummy3.classes.User;


/**
 * A simple {@link Fragment} subclass.
 */
public class ReviewConcertFragment extends Fragment {

    // layout
    private RatingBar reviewconcertRatingBarArtist;
    private RatingBar reviewconcertRatingBarVenue;
    private EditText reviewconcertEditTextReview;
    private TableLayout reviewconcertTableLayout;

    // fields
    View v;
    Concert concert;
    User user;

    public ReviewConcertFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_review_concert, container, false);
        setupData();

        return v;
    }

    private void setupData() {
        reviewconcertRatingBarArtist = v.findViewById(R.id.reviewconcertRatingBarArtist);
        reviewconcertRatingBarVenue = v.findViewById(R.id.reviewconcertRatingBarVenue);
        reviewconcertEditTextReview = v.findViewById(R.id.reviewconcertEditTextReview);

        ((TextView)(v.findViewById(R.id.reviewconcertTextViewArtist))).setText(concert.getArtist().getName());
        ((ImageView)(v.findViewById(R.id.reviewconcertImageViewArtist))).setImageResource(concert.getArtist().getImage());;
        ((TextView)(v.findViewById(R.id.reviewconcertTextViewVenue))).setText(concert.getVenue().getName());
        ((ImageView)(v.findViewById(R.id.reviewconcertImageViewVenue))).setImageResource(concert.getVenue().getImage());

        //todo add functionality to add photos
        reviewconcertTableLayout = v.findViewById(R.id.reviewconcertTableLayout);

        //todo delete
        v.findViewById(R.id.reviewconcertTextViewAddphotos).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addReview();
            }
        });

    }
    public void setConcert(Concert concert) {
        this.concert = concert;
    }
    private void addReview() {
        concert.addReview(reviewconcertEditTextReview.getText().toString(),calculateRating());
    }
    private double calculateRating() {
        return (reviewconcertRatingBarVenue.getRating() + reviewconcertRatingBarArtist.getRating())/2;
    }

}
