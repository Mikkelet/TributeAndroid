package com.tributedummy.metbb.dummy3;


import android.app.SearchableInfo;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.ResourceCursorAdapter;
import android.widget.SearchView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import com.tributedummy.metbb.dummy3.Adapters.DiscoverBlockRVA;
import com.tributedummy.metbb.dummy3.Adapters.SearchcardRVA;
import com.tributedummy.metbb.dummy3.Adapters.SmallCardRVA;
import com.tributedummy.metbb.dummy3.Classes.Artist;
import com.tributedummy.metbb.dummy3.Classes.Concert;
import com.tributedummy.metbb.dummy3.Classes.DiscoverBlock;
import com.tributedummy.metbb.dummy3.Classes.Venue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Random;

public class DiscoverFragment extends Fragment {


    private static final String TAG = "DiscoverFragment";
    // vars
    private ArrayList<DiscoverBlock> discoverBlocks = new ArrayList<>();
    private HashMap<String,DiscoverBlock> discoverBlockHashMap = new HashMap<>();

    private DiscoverBlockRVA discoverBlockRVA;
    private SearchcardRVA searchcardRVA;

    // Layout elements
    private SwipeRefreshLayout swipeRefreshLayout;
    private SearchView searchView;
    private RecyclerView recyclerView;
    private CardView cardView;
    private MainActivity mainActivity;

    private View v;

    public DiscoverFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_discover, container, false);
        recyclerView = v.findViewById(R.id.discoverRecyclerview);
        mainActivity = ((MainActivity)getActivity());

        setupSwipeRefresh();
        setupSearch();
        initImageBitmap();

        return v;
    }

    private void initImageBitmap(){
        Log.d(TAG, "initImageBitmap: "+mainActivity.getConcerts().size());
        //TODO this gets added on every load
        addDiscoverBlock("Last reviewed concerts", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Go to your reviews", Toast.LENGTH_SHORT).show();
            }
        });
        addDiscoverBlock("Upcoming concerts", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Go to your reviews", Toast.LENGTH_SHORT).show();
            }
        });

        applyDatatoAdapters();
    }

    public void addDiscoverBlock(String title, View.OnClickListener action)
    {
        // TODO add different adapters for different lists

        SmallCardRVA adapter = new SmallCardRVA(v.getContext(),mainActivity.getConcerts());

        // This gets called from OnCreateView, which gets called every time the fragment loads. This check is to make sure multiple blocks wont get added on every load.
        if(!discoverBlockHashMap.containsKey(title)) {
            discoverBlockHashMap.put(title, new DiscoverBlock(title, action, adapter));
        }
    }

    private void applyDatatoAdapters()
    {
        recyclerView.setLayoutManager(new LinearLayoutManager(v.getContext()));
        discoverBlockRVA = new DiscoverBlockRVA(v.getContext(), new ArrayList<>(discoverBlockHashMap.values()));
        searchcardRVA = new SearchcardRVA(v.getContext(), mainActivity.getConcerts());
        recyclerView.setAdapter(discoverBlockRVA);
    }

    private void setupSearch()
    {
        cardView = v.findViewById(R.id.discoverCardviewSearch);
        searchView = v.findViewById(R.id.discoverSearchview);

        // needed to show close button when access search via tapping the bar instead of the icon.
        final int closebuttonID = searchView.getContext().getResources().getIdentifier("android:id/search_close_btn", null, null);


        final SearchView.OnCloseListener onCloseListener = new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                recyclerView.setAdapter(discoverBlockRVA);
                mainActivity.toggleMenu(true);
                return false;
            }
        };
        final SearchView.OnClickListener onSearchListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerView.setAdapter(searchcardRVA);
                mainActivity.toggleMenu(false);
            }
        };

        searchView.setSubmitButtonEnabled(true);


        searchView.setOnCloseListener(onCloseListener);
        searchView.setOnSearchClickListener(onSearchListener);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Log.d(TAG, "onQueryTextSubmit: "+mainActivity.getConcerts().size());
                ArrayList<Concert> c = mainActivity.getConcerts();
                searchcardRVA.search(c ,query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        searchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    searchView.onActionViewExpanded();
                    // sets the close button to visible
                ImageView closebutton = v.findViewById(closebuttonID);
                closebutton.setVisibility(View.VISIBLE);
            }
        });


    }

    private void setupSwipeRefresh()
    {
        swipeRefreshLayout = v.findViewById(R.id.discoverSwiperefresh);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                Toast.makeText(getContext(), "Feeling fresh yo", Toast.LENGTH_SHORT).show();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }
}

