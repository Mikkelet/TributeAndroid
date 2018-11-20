package com.tributedummy.metbb.dummy3;


import android.databinding.DataBindingUtil;
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
import android.widget.Toast;

import com.tributedummy.metbb.dummy3.classes.Concert;
import com.tributedummy.metbb.dummy3.classes.Review;
import com.tributedummy.metbb.dummy3.classes.User;
import com.tributedummy.metbb.dummy3.databinding.FragmentReviewConcertBinding;


/**
 * A simple {@link Fragment} subclass.
 */
public class ReviewConcertFragment extends Fragment {

    // layout
    private FragmentReviewConcertBinding reviewConcertBinding;

    // fields
    private Concert concert;
    private User user;

    public ReviewConcertFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        reviewConcertBinding = DataBindingUtil.bind(inflater.inflate(R.layout.fragment_review_concert, container, false));
        // data binds the concert data
        reviewConcertBinding.setConcert(concert);

        // adds OCL to a button
        reviewConcertBinding.reviewconcertTextViewAddphotos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addReview();
            }
        });

        return reviewConcertBinding.getRoot();
    }

    public void setConcert(Concert concert) {
        this.concert = concert;
    }
    private void addReview() {
        Review review = new Review(new User("mikkel"),
                concert,
                reviewConcertBinding.reviewconcertEditTextReview.getText().toString(),
                reviewConcertBinding.reviewconcertRatingBarArtist.getRating(),
                reviewConcertBinding.reviewconcertRatingBarVenue.getRating());
        concert.addReview(review);
    }
    private double calculateRating() {
        float rating = (reviewConcertBinding.reviewconcertRatingBarVenue.getRating() +
                reviewConcertBinding.reviewconcertRatingBarArtist.getRating())/2;
        Toast.makeText(getActivity(), "", Toast.LENGTH_SHORT).show();
        return rating;

    }

}
