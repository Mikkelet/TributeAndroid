package com.tributedummy.metbb.dummy3;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.Toast;

import com.tributedummy.metbb.dummy3.adapters.DiscoverBlockAdapter;
import com.tributedummy.metbb.dummy3.adapters.SearchCardAdapter;
import com.tributedummy.metbb.dummy3.adapters.SmallCardAdapter;
import com.tributedummy.metbb.dummy3.classes.Concert;
import com.tributedummy.metbb.dummy3.classes.ConcertStatus;
import com.tributedummy.metbb.dummy3.classes.DiscoverBlock;

import java.util.ArrayList;

public class DiscoverFragment extends Fragment {
    private static final String TAG = "DiscoverFragment";

    // Discover block
    ArrayList<DiscoverBlock> discoverBlockArrayList = new ArrayList<>();


    // vars
    private MainActivity mainActivity;
    private ArrayList<Concert> concerts;

    private DiscoverBlockAdapter discoverBlockAdapter;
    private SearchCardAdapter searchCardAdapter;

    // Layout elements
    private SwipeRefreshLayout swipeRefreshLayout;
    private SearchView searchView;
    private RecyclerView recyclerView;

    // eventlisteners
    private View v;

    public DiscoverFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_discover, container, false);
        recyclerView = v.findViewById(R.id.discoverRecyclerview);
        mainActivity = ((MainActivity)getActivity());
        concerts = MainActivity.concerts;
        setupSwipeRefresh();
        setupSearch();

        // add horizontal scroll wheels
        String dbTitleToday = "Live Today";
        String dbTitleLastreviewed = "Last reviewed";
        String dbTitleUpcoming = "Upcoming concerts";
        addDiscoverBlock(dbTitleToday, onClickToast(dbTitleToday),MainActivity.getIndexedConcerts(ConcertStatus.TODAY));
        addDiscoverBlock(dbTitleLastreviewed,onClickToast(dbTitleLastreviewed),MainActivity.getIndexedConcerts(ConcertStatus.DONE));
        addDiscoverBlock(dbTitleUpcoming, onClickToast(dbTitleUpcoming),MainActivity.getIndexedConcerts(ConcertStatus.UPCOMING));
        applyDatatoAdapters();

        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        mainActivity.setActionBarVisibility(false);
    }

    private void addDiscoverBlock(String title, View.OnClickListener action, ArrayList<Concert> concerts) {
        // TODO add different adapters for different lists

        SmallCardAdapter adapter = new SmallCardAdapter(v.getContext(), concerts);
        DiscoverBlock discoverBlock = new DiscoverBlock(title,action,adapter);
        // This gets called from OnCreateView, which gets called every time the fragment loads. This check is to make sure multiple blocks wont get added on every load.
        // it compares titles with contains
        if(!discoverBlockArrayList.contains(discoverBlock))
            discoverBlockArrayList.add(discoverBlock);
    }
    private void applyDatatoAdapters() {
        recyclerView.setLayoutManager(new LinearLayoutManager(v.getContext()));

        discoverBlockAdapter = new DiscoverBlockAdapter(v.getContext(), discoverBlockArrayList);
        searchCardAdapter = new SearchCardAdapter(v.getContext(), concerts);
        recyclerView.setAdapter(discoverBlockAdapter);
    }
    private void setupSearch() {
        searchView = v.findViewById(R.id.discoverSearchview);

        // needed to show close button when access search via tapping the bar instead of the icon.
        final int closebuttonID = searchView.getContext().getResources().getIdentifier("android:id/search_close_btn", null, null);

        // On close
        final SearchView.OnCloseListener onCloseListener = new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                recyclerView.setAdapter(discoverBlockAdapter);
                mainActivity.toggleMenu(true);
                return false;
            }
        };

        final SearchView.OnClickListener onSearchListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerView.setAdapter(searchCardAdapter);
                mainActivity.toggleMenu(false);
            }
        };

        searchView.setSubmitButtonEnabled(true);


        searchView.setOnCloseListener(onCloseListener);
        searchView.setOnSearchClickListener(onSearchListener);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Log.d(TAG, "onQueryTextSubmit: "+MainActivity.concerts.size());
                ArrayList<Concert> c = MainActivity.concerts;
                searchCardAdapter.search(c ,query);
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
    private void setupSwipeRefresh() {
        swipeRefreshLayout = v.findViewById(R.id.discoverSwiperefresh);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Toast.makeText(getContext(), "Feeling fresh yo", Toast.LENGTH_SHORT).show();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    // eventlisteners
    private View.OnClickListener onClickToast(final String message){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
            }
        };
    }
}

