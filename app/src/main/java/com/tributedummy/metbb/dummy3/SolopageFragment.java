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

import com.tributedummy.metbb.dummy3.adapters.DiscoverBlockRVA;
import com.tributedummy.metbb.dummy3.adapters.ReviewFragmentRVA;
import com.tributedummy.metbb.dummy3.adapters.SmallCardRVA;
import com.tributedummy.metbb.dummy3.classes.Concert;
import com.tributedummy.metbb.dummy3.classes.ConcertElement;
import com.tributedummy.metbb.dummy3.classes.DiscoverBlock;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * A simple {@link Fragment} subclass.
 */
public class SolopageFragment extends Fragment {

    private View v;
    private ConcertElement concertElement;
    private HashMap<String,DiscoverBlock> discoverBlockHashMap = new HashMap<>();
    private ArrayList<Concert> concerts;
    private Fragment previousFragment;
    private MainActivity mainActivity;

    // layouts
    private ImageView solopageImageviewCover;
    private ImageView solopageImageviewArtist;
    private Button solopageButtonBack;
    private TextView solopageTextviewRating;
    private TextView solopageTextviewArtist;
    private TextView solopageTextviewReviews;
    private TextView solopageTextviewConcerts;
    private RecyclerView solopageRecycleviewConcerts;
    private TextView solopageTextviewReviewsexpanded;
    private LinearLayout solopageLinearlayoutReviews;

    //adapters
    private ReviewFragmentRVA reviewRVA;
    private DiscoverBlockRVA discoverBlockRVA;


    public SolopageFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // assign layout fields
        v = inflater.inflate(R.layout.fragment_solopage, container, false);

        // Assign fieds
        concerts = (ArrayList<Concert>) concertElement.getConcerts();
        mainActivity = (MainActivity)getActivity();

        // statics
        solopageImageviewCover = v.findViewById(R.id.solopageImageviewCover);
        solopageImageviewArtist = v.findViewById(R.id.solopageImageviewArtist);
        solopageTextviewRating = v.findViewById(R.id.solopageTextviewRating);
        solopageTextviewArtist = v.findViewById(R.id.solopageTextviewArtist);
        solopageTextviewReviews = v.findViewById(R.id.solopageTextviewReviews);
        solopageTextviewConcerts = v.findViewById(R.id.solopageTextviewConcerts);
        solopageTextviewReviewsexpanded = v.findViewById(R.id.solopageTextviewReviewsexpanded);

        // setups
        setupPage();
        // Inflate the layout for this fragment
        return v;
    }

    private void setupPage()
    {
        // statics
        solopageImageviewCover.setImageResource(concertElement.getImage());
        solopageImageviewArtist.setImageResource(concertElement.getImage());
        solopageTextviewArtist.setText(concertElement.getName());
        solopageTextviewRating.setText(""+concertElement.getRating());
        solopageTextviewConcerts.setText("5 concerts");
        solopageTextviewReviews.setText(concertElement.getReviews().size()+" reviews");
        setupRecycleview();
        setupLinearLayout();
        setupBackbutton();

        applyDatatoAdapters();
    }

    private void setupRecycleview()
    {
        solopageRecycleviewConcerts = v.findViewById(R.id.solopageRecycleviewConcerts);
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
        solopageLinearlayoutReviews = v.findViewById(R.id.solopageLinearlayoutReviews);
    }

    private void setupBackbutton()
    {
        solopageButtonBack = v.findViewById(R.id.solopageButtonBack);
        solopageButtonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(previousFragment != null)//todo rewrite this to usue backstack
                    mainActivity.switchFragment(previousFragment,false);
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
        solopageRecycleviewConcerts.setLayoutManager(new LinearLayoutManager(v.getContext()));
        discoverBlockRVA = new DiscoverBlockRVA(v.getContext(), new ArrayList<>(discoverBlockHashMap.values()));
        solopageRecycleviewConcerts.setAdapter(discoverBlockRVA);
    }

    // Setter

    public void setPreviousFragment(Fragment previousFragment) {
        this.previousFragment = previousFragment;
    }
    public void setConcertElement(ConcertElement solopage) {
        this.concertElement = solopage;
    }
}
