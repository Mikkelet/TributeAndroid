package com.tributedummy.metbb.dummy3;


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

import com.tributedummy.metbb.dummy3.Adapters.ConcertPhotosRVA;
import com.tributedummy.metbb.dummy3.Classes.Concert;
import com.tributedummy.metbb.dummy3.Classes.Review;


/**
 * A simple {@link Fragment} subclass.
 */
public class ConcertFragment extends Fragment {

    private ImageView imageViewCover;
    private ImageView imageViewArtist;
    private ImageView imageViewVenue;

    private RatingBar ratingBar;
    private TextView textViewRating;

    private TextView textViewArtist;
    private TextView textViewVenue;
    private TextView textViewDate;

    private Button buttonRatingdetails;
    private Button buttonReviewConcert;

    private RecyclerView recyclerViewPhotos;
    private LinearLayout linearLayoutReviews;

    private Button buttonBack;

    private Concert concert;

    private View v;
    private MainActivity mainActivity;

    public ConcertFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_concert, container, false);
        mainActivity = ((MainActivity)getActivity());
        initLayouts();
        applyData();
        setupLinearLayoutReviews(inflater);
        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private void initLayouts(){
        imageViewArtist = v.findViewById(R.id.concertImageviewArtist);
        imageViewCover = v.findViewById(R.id.concertImageviewCover);
        imageViewVenue = v.findViewById(R.id.concertImageviewVenue);

        ratingBar = v.findViewById(R.id.concertRatingbar);
        textViewRating = v.findViewById(R.id.concertTextviewRating);

        textViewArtist = v.findViewById(R.id.concertTextviewArtist);
        textViewVenue = v.findViewById(R.id.concertTextviewVenue);
        textViewDate = v.findViewById(R.id.concertTextviewReviews);

        buttonRatingdetails = v.findViewById(R.id.concertButtonRatingdetails);
        buttonReviewConcert = v.findViewById(R.id.concertButtonReviewconcert);

        recyclerViewPhotos = v.findViewById(R.id.concertRecycleviewConcerts);
        linearLayoutReviews = v.findViewById(R.id.concertLinearlayoutReviews);

        buttonBack = v.findViewById(R.id.concertButtonBack);
    }
    private void applyData() {
        imageViewVenue.setImageResource(concert.getVenue().getImage());
        // TODO add concert image in class
        imageViewCover.setImageResource(concert.getVenue().getImage());
        imageViewArtist.setImageResource(concert.getArtist().getImage());

        ratingBar.setRating((float)concert.getRating());
        textViewRating.setText(""+concert.getRating());

        textViewArtist.setText(concert.getArtist().getName());
        textViewVenue.setText(concert.getVenue().getName());
        textViewDate.setText(concert.getDate());

        setupButtonRatingDetails();
        setupButtonReviewConcert();
        setupButtonArtist();
        setupButtonVenue();
        setupButtonBack();
        setupRecyclerviewPhotos();
    }

    private void setupButtonReviewConcert()
    {
        buttonReviewConcert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity.SwitchFragment(mainActivity.getReviewConcertFragment(concert));
            }
        });
    }
    private void setupButtonRatingDetails()
    {
        buttonRatingdetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "Ratingdetails", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void setupButtonArtist()
    {
        imageViewArtist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity.SwitchFragment(mainActivity.getSolopageFragment(concert.getArtist(), mainActivity.getConcertFragment(concert)));
            }
        });
    }
    private void setupButtonVenue()
    {
        imageViewVenue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity.SwitchFragment(mainActivity.getSolopageFragment(concert.getVenue(), mainActivity.getConcertFragment(concert)));
            }
        });
    }
    private void setupButtonBack()
    {
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity.SwitchFragment(mainActivity.getDiscoverFragment());
            }
        });
    }
    private void setupRecyclerviewPhotos()
    {
        recyclerViewPhotos = v.findViewById(R.id.concertRecycleviewConcerts);
        recyclerViewPhotos.setLayoutManager(new LinearLayoutManager(v.getContext(), LinearLayoutManager.HORIZONTAL,false));
        ConcertPhotosRVA adapter = new ConcertPhotosRVA(v.getContext(),concert.getPhotos());
        recyclerViewPhotos.setAdapter(adapter);
    }
    private void setupLinearLayoutReviews(LayoutInflater inflater)
    {
        linearLayoutReviews = v.findViewById(R.id.concertLinearlayoutReviews);
        for (int i = 0; i < concert.getReviews().size(); i++) {
            Review currentReview = concert.getReviews().get(i);
            View review = inflater.inflate(R.layout.layout_reviewcard, linearLayoutReviews,false);
            ImageView imageViewUser = review.findViewById(R.id.reviewImageviewUser);
            TextView textViewUser = review.findViewById(R.id.reviewTextviewUser);
            TextView textViewDate = review.findViewById(R.id.reviewTextviewDate);
            TextView textViewRating = review.findViewById(R.id.reviewTextviewRating);
            TextView textViewReview = review.findViewById(R.id.reviewTextviewReview);
            TextView textViewFavourites = review.findViewById(R.id.reviewTextviewFavorites);
            TextView textViewConcert = review.findViewById(R.id.reviewTextviewConcert);
            ImageButton buttonGotoConcert = review.findViewById(R.id.reviewButtonGotoconcert);

            imageViewUser.setImageResource(currentReview.getSubmittedBy().getProfilePic());
            textViewUser.setText(currentReview.getSubmittedBy().getName());
            textViewDate.setText(currentReview.getDate());
            textViewRating.setText(""+currentReview.getRating());
            textViewReview.setText(currentReview.getReview());
            textViewFavourites.setText(""+currentReview.getFavourites());
            textViewConcert.setVisibility(View.INVISIBLE);
            buttonGotoConcert.setVisibility(View.INVISIBLE);
            linearLayoutReviews.addView(review);
        }
    }
    public void setConcert(Concert concert)
    {
        this.concert = concert;
    }

}