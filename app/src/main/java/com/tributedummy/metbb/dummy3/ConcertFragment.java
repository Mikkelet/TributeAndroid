package com.tributedummy.metbb.dummy3;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.tributedummy.metbb.dummy3.adapters.ConcertPhotosRVA;
import com.tributedummy.metbb.dummy3.classes.Concert;
import com.tributedummy.metbb.dummy3.classes.ConcertElement;
import com.tributedummy.metbb.dummy3.classes.Review;
import com.tributedummy.metbb.dummy3.databinding.FragmentConcertBinding;
import com.tributedummy.metbb.dummy3.databinding.LayoutReviewcardBinding;


/**
 * A simple {@link Fragment} subclass.
 */
public class ConcertFragment extends Fragment {

    private Concert concert;
    FragmentConcertBinding concertBinding;
    MainActivity mainActivity;

    public ConcertFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //v = inflater.inflate(R.layout.fragment_concert, container, false);
        mainActivity = (MainActivity) getActivity();
        concertBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_concert, container, false);
        concertBinding.setConcert(concert);
        setupButtonRatingDetails();
        setupButtonReviewConcert();
        setupButtonArtist();
        setupButtonVenue();
        setupButtonBack();
        setupRecyclerviewPhotos();

        setupLinearLayoutReviews(inflater);

        return concertBinding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    // Setups
    private void setupButtonReviewConcert() {
        concertBinding.concertButtonReviewconcert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity.switchFragment(mainActivity.getReviewConcertFragment(concert),true);
            }
        });
    }
    private void setupButtonRatingDetails() {
        concertBinding.concertButtonRatingdetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "Ratingdetails", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void setupButtonArtist() {
        concertBinding.concertImageviewArtist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity.switchFragment(mainActivity.getSolopageFragment(concert.getArtist()),true);
            }
        });
    }
    private void setupButtonVenue() {
        concertBinding.concertImageviewVenue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity.switchFragment(mainActivity.getSolopageFragment(concert.getVenue()),true);
            }
        });
    }
    private void setupButtonBack() {
        concertBinding.concertButtonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity.onBackPressed();
            }
        });
    }
    private void setupRecyclerviewPhotos() {
        concertBinding.concertRecycleviewConcerts.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL,false));
        ConcertPhotosRVA adapter = new ConcertPhotosRVA(getContext(),concert.getPhotos());
        concertBinding.concertRecycleviewConcerts.setAdapter(adapter);
    }
    private void setupLinearLayoutReviews(LayoutInflater inflater) {
        for (int i = 0; i < concert.getReviews().size(); i++) {
            Review currentReview = concert.getReviews().get(i);
            // view to inflate
            LayoutReviewcardBinding reviewcardBinding = LayoutReviewcardBinding.inflate(inflater);
            //View review = inflater.inflate(R.layout.layout_reviewcard, linearLayoutReviews,false);

            reviewcardBinding.setConcert(concert);
            reviewcardBinding.setReview(currentReview);

            reviewcardBinding.reviewTextviewConcert.setVisibility(View.INVISIBLE);
            reviewcardBinding.reviewButtonGotoconcert.setVisibility(View.INVISIBLE);

            concertBinding.concertLinearlayoutReviews.addView(reviewcardBinding.getRoot());
        }
    }

    //getters and setters
    public void setConcert(Concert concert)
    {
        this.concert = concert;
    }

}

