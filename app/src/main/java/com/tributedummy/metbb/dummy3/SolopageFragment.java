package com.tributedummy.metbb.dummy3;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.tributedummy.metbb.dummy3.adapters.DiscoverBlockRVA;
import com.tributedummy.metbb.dummy3.adapters.ReviewFragmentRVA;
import com.tributedummy.metbb.dummy3.adapters.SmallCardRVA;
import com.tributedummy.metbb.dummy3.classes.Concert;
import com.tributedummy.metbb.dummy3.classes.ConcertElement;
import com.tributedummy.metbb.dummy3.classes.DiscoverBlock;
import com.tributedummy.metbb.dummy3.databinding.FragmentSolopageBinding;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * A simple {@link Fragment} subclass.
 */
public class SolopageFragment extends Fragment {

    private ConcertElement concertElement;
    private HashMap<String,DiscoverBlock> discoverBlockHashMap = new HashMap<>();
    private ArrayList<Concert> concerts;
    private MainActivity mainActivity;

    // layouts
    private FragmentSolopageBinding solopageBinding;

    //adapters
    private DiscoverBlockRVA discoverBlockRVA;


    public SolopageFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater     inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // assign layout fields
        solopageBinding = FragmentSolopageBinding.bind(inflater.inflate(R.layout.fragment_solopage, container, false));

        // Assign fieds
        concerts = concertElement.getConcerts();
        mainActivity = (MainActivity)getActivity();

        // statics
        solopageBinding.setElement(concertElement);

        // setups

        setupRecycleview();
        setupBackbutton();
        applyDatatoAdapters();
        // Inflate the layout for this fragment
        return solopageBinding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        mainActivity.setActionBarVisibility(false);
    }

    private void setupRecycleview() {
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
    private void setupBackbutton() {
        solopageBinding.solopageButtonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    mainActivity.onBackPressed();
            }
        });
    }

    public void addDiscoverBlock(String title,  ArrayList<Concert> concerts, View.OnClickListener action)
    {
        // TODO add different adapters for different lists
        SmallCardRVA adapter = new SmallCardRVA(getContext(), concerts);
        // This gets called from OnCreateView, which gets called every time the fragment loads. This check is to make sure multiple blocks wont get added on every load.
        if(!discoverBlockHashMap.containsKey(title)) {
            discoverBlockHashMap.put(title, new DiscoverBlock(title, action, adapter));
        }
    }

    private void applyDatatoAdapters()
    {
        solopageBinding.solopageRecycleviewConcerts.setLayoutManager(new LinearLayoutManager(getContext()));
        discoverBlockRVA = new DiscoverBlockRVA(getContext(), new ArrayList<>(discoverBlockHashMap.values()));
        solopageBinding.solopageRecycleviewConcerts.setAdapter(discoverBlockRVA);
    }

    // Setter
    public void setConcertElement(ConcertElement solopage) {
        this.concertElement = solopage;
    }
}
