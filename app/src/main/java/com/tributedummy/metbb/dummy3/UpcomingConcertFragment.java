package com.tributedummy.metbb.dummy3;



import android.databinding.DataBindingUtil;
import com.tributedummy.metbb.dummy3.databinding.LayoutReviewcardBinding;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.tributedummy.metbb.dummy3.adapters.LinearLayoutReviewsAdapter;
import com.tributedummy.metbb.dummy3.classes.Concert;
import com.tributedummy.metbb.dummy3.classes.Review;
import com.tributedummy.metbb.dummy3.databinding.FragmentUpcomingConcertBinding;


/**
 * A simple {@link Fragment} subclass.
 */
public class UpcomingConcertFragment extends Fragment {

    // fields
    Concert concert;
    LinearLayoutReviewsAdapter linearLayoutReviewsAdapter;
    FragmentUpcomingConcertBinding upcomingConcertBinding;
    MainActivity mainActivity;

    public UpcomingConcertFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        upcomingConcertBinding = DataBindingUtil.bind(inflater.inflate(R.layout.fragment_upcoming_concert, container, false));
        upcomingConcertBinding.setConcert(concert);
        mainActivity = (MainActivity) getActivity();

        // setups
        setupButtonArtist();
        setupButtonBack();
        setupButtonVenue();
        setupButtonBuyTicket();
        setupLinearLayoutReviews(inflater);

        return upcomingConcertBinding.getRoot();
    }

    private void setupButtonArtist() {
        upcomingConcertBinding.upcomingImageViewArtist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity.switchFragment(mainActivity.getSolopageFragment(concert.getArtist()),true);
            }
        });
    }
    private void setupButtonVenue() {
        upcomingConcertBinding.upcomingImageViewVenue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity.switchFragment(mainActivity.getSolopageFragment(concert.getVenue()),true);
            }
        });
    }
    private void setupButtonBack() {
        upcomingConcertBinding.upcomingconcertImageButtonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity.onBackPressed();
            }
        });
    }

    private void setupButtonBuyTicket()
    {

    }
    private void setupLinearLayoutReviews(LayoutInflater inflater)
    {
        for (int i = 0; i < concert.getReviews().size(); i++) {
            Review currentReview = concert.getReviews().get(i);
            // view to inflate
            LayoutReviewcardBinding reviewcardBinding = LayoutReviewcardBinding.inflate(inflater);

            reviewcardBinding.setConcert(concert);
            reviewcardBinding.setReview(currentReview);
            reviewcardBinding.reviewTextviewConcert.setVisibility(View.INVISIBLE);
            reviewcardBinding.reviewButtonGotoconcert.setVisibility(View.INVISIBLE);

            upcomingConcertBinding.upcomingLinearLayout.addView(reviewcardBinding.getRoot());
        }
    }

    public void setConcert(Concert concert) {
        this.concert = concert;
    }
}
