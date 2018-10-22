package com.tributedummy.metbb.dummy3;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.tributedummy.metbb.dummy3.Adapters.DiscoverBlockRVA;
import com.tributedummy.metbb.dummy3.Adapters.ReviewFragmentRVA;
import com.tributedummy.metbb.dummy3.Adapters.SmallCardRVA;
import com.tributedummy.metbb.dummy3.Classes.Artist;
import com.tributedummy.metbb.dummy3.Classes.Concert;
import com.tributedummy.metbb.dummy3.Classes.DiscoverBlock;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * A simple {@link Fragment} subclass.
 */
public class ArtistFragment extends Fragment {

    private View v;
    private Artist artist;
    private HashMap<String,DiscoverBlock> discoverBlockHashMap = new HashMap<>();
    private ArrayList<Concert> concerts;
    private Fragment previousFragment;
    private MainActivity mainActivity;

    // layouts
    private ImageView artistImageviewCover;
    private ImageView artistImageviewArtist;
    private Button artistButtonBack;
    private TextView artistTextviewRating;
    private TextView artistTextviewArtist;
    private TextView artistTextviewReviews;
    private TextView artistTextviewConcerts;
    private RecyclerView artistRecycleviewConcerts;
    private TextView artistTextviewReviewsexpanded;
    private LinearLayout artistLinearlayoutReviews;

    //adapters
    private ReviewFragmentRVA reviewRVA;
    private DiscoverBlockRVA discoverBlockRVA;


    public ArtistFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // assign layout fields
        v = inflater.inflate(R.layout.fragment_artist, container, false);

        // Assign fieds
        concerts = (ArrayList<Concert>) artist.getConcerts();
        mainActivity = (MainActivity)getActivity();

        // statics
        artistImageviewCover = v.findViewById(R.id.artistImageviewCover);
        artistImageviewArtist = v.findViewById(R.id.artistImageviewArtist);
        artistTextviewRating = v.findViewById(R.id.artistTextviewRating);
        artistTextviewArtist = v.findViewById(R.id.artistTextviewArtist);
        artistTextviewReviews = v.findViewById(R.id.artistTextviewReviews);
        artistTextviewConcerts = v.findViewById(R.id.artistTextviewConcerts);
        artistTextviewReviewsexpanded = v.findViewById(R.id.artistTextviewReviewsexpanded);


        // setups
        setupPage();
        // Inflate the layout for this fragment
        return v;
    }

    private void setupPage()
    {
        // statics
        artistImageviewCover.setImageResource(artist.getImage());
        artistImageviewArtist.setImageResource(artist.getImage());
        artistTextviewArtist.setText(artist.getName());
        artistTextviewRating.setText(""+artist.getRating());
        artistTextviewConcerts.setText("5 concerts");
        artistTextviewReviews.setText(artist.getReviews().size()+" reviews");
        setupRecycleview();
        setupLinearLayout();
        setupBackbutton();

        applyDatatoAdapters();
    }

    private void setupRecycleview()
    {
        artistRecycleviewConcerts = v.findViewById(R.id.artistRecycleviewConcerts);
        addDiscoverBlock("Last concerts",concerts, new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Toast.makeText(getContext(), "last concercts", Toast.LENGTH_SHORT).show();
        }
    });
        addDiscoverBlock("Upcoming concerts", concerts, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "upcoming concerts", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void setupLinearLayout()
    {
        artistLinearlayoutReviews = v.findViewById(R.id.artistLinearlayoutReviews);


    }

    private void setupBackbutton()
    {
        artistButtonBack = v.findViewById(R.id.artistButtonBack);
        artistButtonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(previousFragment != null)
                mainActivity.SwitchFragment(previousFragment);
            }
        });
    }

    public void addDiscoverBlock(String title,  ArrayList<Concert> concerts, View.OnClickListener action)
    {
        // TODO add different adapters for different lists
        SmallCardRVA adapter = new SmallCardRVA(v.getContext(), concerts);

        // This gets called from OnCreateView, which gets called every time the fragment loads. This check is to make sure multiple blocks wont get added on every load.
        if(!discoverBlockHashMap.containsKey(title)) {
            discoverBlockHashMap.put(title, new DiscoverBlock(title, action, adapter));
        }
    }

    private void applyDatatoAdapters()
    {
        artistRecycleviewConcerts.setLayoutManager(new LinearLayoutManager(v.getContext()));
        discoverBlockRVA = new DiscoverBlockRVA(v.getContext(), new ArrayList<>(discoverBlockHashMap.values()));
        artistRecycleviewConcerts.setAdapter(discoverBlockRVA);
    }

    // Setter

    public void setPreviousFragment(Fragment previousFragment) {
        this.previousFragment = previousFragment;
    }
    public void setArtist(Artist artist) {
        this.artist = artist;
    }
}
