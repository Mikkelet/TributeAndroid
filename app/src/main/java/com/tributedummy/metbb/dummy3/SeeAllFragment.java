package com.tributedummy.metbb.dummy3;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tributedummy.metbb.dummy3.adapters.ReviewFragmentAdapter;
import com.tributedummy.metbb.dummy3.classes.Concert;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class SeeAllFragment extends Fragment {

    private String filterTitle;
    private ArrayList<Concert> concerts;
    private RecyclerView recyclerView;


    public SeeAllFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_see_all, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        recyclerView = getView().findViewById(R.id.seeallRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        ReviewFragmentAdapter adapter = new ReviewFragmentAdapter(getContext(),concerts);
        recyclerView.setAdapter(adapter);
        getActivity().setTitle(filterTitle);
    }

    public void setFilterTitle(String filterTitle) {
        this.filterTitle = filterTitle;
    }
    public void setConcerts(ArrayList<Concert> concerts) {
        this.concerts = concerts;
    }
}
