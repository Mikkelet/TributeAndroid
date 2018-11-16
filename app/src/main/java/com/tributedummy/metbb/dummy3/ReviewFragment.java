package com.tributedummy.metbb.dummy3;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tributedummy.metbb.dummy3.adapters.ReviewFragmentRVA;
import com.tributedummy.metbb.dummy3.classes.Concert;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */


public class ReviewFragment extends Fragment {

    private MainActivity mainActivity;
    ArrayList<Concert> concerts;
    View v;

    private static final String TAG = "ReviewFragment";

    public ReviewFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_review, container, false);
        mainActivity = ((MainActivity)getActivity());
        concerts =(ArrayList<Concert>) mainActivity.getConcerts();

        initRecyclerView();

        return v;
    }

    private void initRecyclerView()
    {
        Log.d(TAG, "initRecyclerView: init recycler view");
        RecyclerView recyclerView = v.findViewById(R.id.RecyclerViewReviews);
        recyclerView.setLayoutManager(new LinearLayoutManager(v.getContext()));
        ReviewFragmentRVA adapter = new ReviewFragmentRVA(v.getContext(),concerts);
        recyclerView.setAdapter(adapter);
    }
}
