package com.tributedummy.metbb.dummy3.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.tributedummy.metbb.dummy3.classes.Concert;
import com.tributedummy.metbb.dummy3.classes.DiscoverBlock;
import com.tributedummy.metbb.dummy3.MainActivity;
import com.tributedummy.metbb.dummy3.R;

import java.util.ArrayList;

public class DiscoverBlockAdapter extends  RecyclerView.Adapter<DiscoverBlockAdapter.ViewHolder> {

    private static final String TAG = "DiscoverblockRVA";

    private Context mContext;
    private ArrayList<DiscoverBlock> discoverBlocks;

    private RecyclerView recyclerView;

    public DiscoverBlockAdapter(Context mContext, ArrayList<DiscoverBlock> discoverBlocks) {
        this.mContext = mContext;
        this.discoverBlocks = discoverBlocks;
    }

    MainActivity mainActivity;


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_discoverblock, viewGroup, false);
        ViewHolder holder = new ViewHolder(view);
        recyclerView = view.findViewById(R.id.discoverBlockRecyclerview);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int position) {
        mainActivity = ((MainActivity) mContext);
        RecyclerView recyclerView = viewHolder.getRecycleView();
        final ArrayList<Concert> concertsFiltered = ((SmallCardAdapter) discoverBlocks.get(position).getAdapter()).getConcerts();
        final String blockTitle = discoverBlocks.get(position).getTitle();
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(discoverBlocks.get(position).getAdapter());
        viewHolder.textViewTitle.setText(blockTitle);
        viewHolder.textViewSeeall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "SEE ALL", Toast.LENGTH_SHORT).show();
                mainActivity.switchFragment(mainActivity.getSeeAllFragment(blockTitle, concertsFiltered),true);
            }
        });
        viewHolder.constraintLayout.setOnClickListener(new View.OnClickListener() {
                                                           @Override
                                                           public void onClick(View v) {
                                                               Toast.makeText(mContext, discoverBlocks.get(position).getTitle(), Toast.LENGTH_SHORT).show();
                                                           }
                                                       }

        );
    }

    @Override
    public int getItemCount() {
        return discoverBlocks.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView textViewTitle;
        RecyclerView recyclerView;
        TextView textViewSeeall;
        ConstraintLayout constraintLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            recyclerView = itemView.findViewById(R.id.discoverBlockRecyclerview);
            textViewTitle = itemView.findViewById(R.id.discoverBlockTextviewTitle);
            constraintLayout = itemView.findViewById(R.id.discoverblockConstraint);
            textViewSeeall = itemView.findViewById(R.id.discoverBlockTextviewSeeall);
        }

        public RecyclerView getRecycleView()
        {
            return recyclerView;
        }
    }
}
