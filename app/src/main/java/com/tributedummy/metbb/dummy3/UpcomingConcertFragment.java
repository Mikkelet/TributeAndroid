package com.tributedummy.metbb.dummy3;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tributedummy.metbb.dummy3.Adapters.LinearLayoutReviewsAdapter;
import com.tributedummy.metbb.dummy3.Classes.Concert;


/**
 * A simple {@link Fragment} subclass.
 */
public class UpcomingConcertFragment extends Fragment {

    // fields
    View v;
    Concert concert;
    LinearLayoutReviewsAdapter linearLayoutReviewsAdapter;

    // Layout
    private Button upcomingButtonBack;
    private LinearLayout upcomingLinearLayout;
    private ImageView upcomingImageViewCover;
    private ImageView upcomingImageViewArtist;
    private ImageView upcomingImageViewVenue;
    private TextView upcomingTextViewBuytickets;
    private TextView upcomingTextViewArtist;
    private TextView upcomingTextViewVenue;
    private TextView upcomingTextViewDate;

    public UpcomingConcertFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_upcoming_concert, container, false);

        // setups
        setupLayout();

        // assign values
        upcomingImageViewVenue.setImageResource(concert.getVenue().getImage());
        upcomingImageViewCover.setImageResource(concert.getVenue().getImage());
        upcomingImageViewArtist.setImageResource(concert.getArtist().getImage());
        upcomingTextViewArtist.setText(concert.getArtist().getName());
        upcomingTextViewVenue.setText(concert.getVenue().getName());
        upcomingTextViewDate.setText(concert.getDate());
        setupBackButton();
        setupButtonBuyTicket();
        setupLinearLayout();

        return v;
    }

    private void setupLayout()
    {
        upcomingButtonBack = v.findViewById(R.id.upcomingButtonBack);
        upcomingLinearLayout = v.findViewById(R.id.upcomingLinearLayout);
        upcomingImageViewCover = v.findViewById(R.id.upcomingImageViewCover);
        upcomingImageViewArtist = v.findViewById(R.id.upcomingImageViewArtist);
        upcomingImageViewVenue = v.findViewById(R.id.upcomingImageViewVenue);
        upcomingTextViewBuytickets = v.findViewById(R.id.upcomingTextViewBuytickets);
        upcomingTextViewArtist = v.findViewById(R.id.upcomingTextViewArtist);
        upcomingTextViewVenue = v.findViewById(R.id.upcomingTextViewVenue);
        upcomingTextViewDate = v.findViewById(R.id.upcomingTextViewDate);
    }

    private void setupButtonBuyTicket()
    {

    }
    private void setupBackButton()
    {

    }
    private void setupLinearLayout()
    {

    }
}
