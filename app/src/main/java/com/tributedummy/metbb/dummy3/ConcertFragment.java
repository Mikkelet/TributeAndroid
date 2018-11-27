package com.tributedummy.metbb.dummy3;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.Toast;

import com.tributedummy.metbb.dummy3.adapters.ConcertPhotosAdapter;
import com.tributedummy.metbb.dummy3.adapters.ReviewAdapter;
import com.tributedummy.metbb.dummy3.classes.Concert;
import com.tributedummy.metbb.dummy3.databinding.FragmentConcertBinding;


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
        //concertBinding.concertTextviewArtist.setText(StringUtils.);
        setupButtonRatingDetails();
        setupButtonReviewConcert();
        setupButtonArtist();
        setupButtonVenue();
        setupButtonBack();
        setupRecyclerviewPhotos();
        setupLinearLayoutReviews();

        return concertBinding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
       mainActivity.setActionBarVisibility(false);
       //scrolls up
       concertBinding.concertsScrollview.fullScroll(ScrollView.FOCUS_UP);
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
                Toast.makeText(mainActivity, "view rating deets", Toast.LENGTH_SHORT).show();
                concertBinding.concertExpandableLayout.toggle();
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
        ConcertPhotosAdapter adapter = new ConcertPhotosAdapter(getContext(),concert.getPhotos());
        concertBinding.concertRecycleviewConcerts.setAdapter(adapter);
    }
    private void setupLinearLayoutReviews() {
        ReviewAdapter reviewAdapter = new ReviewAdapter(getContext(),concert);
        concertBinding.concertRecycleViewReviews.setLayoutManager(new LinearLayoutManager(getContext()));
        concertBinding.concertRecycleViewReviews.setAdapter(reviewAdapter);
    }

    //getters and setters
    public void setConcert(Concert concert)
    {
        this.concert = concert;
    }

}

