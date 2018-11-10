package com.tributedummy.metbb.dummy3.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.tributedummy.metbb.dummy3.Classes.Concert;
import com.tributedummy.metbb.dummy3.MainActivity;
import com.tributedummy.metbb.dummy3.R;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class SearchcardRVA extends  RecyclerView.Adapter<SearchcardRVA.ViewHolder> {
    private static final String TAG = "";

    private Context mContext;
    private ArrayList<Concert> concerts;
    private MainActivity mainActivity;

    public SearchcardRVA(Context mContext, ArrayList<Concert> concerts) {
        this.mContext = mContext;
        this.concerts = concerts;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_searchcard, viewGroup, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int position) {
        mainActivity = ((MainActivity)mContext);
        viewHolder.imageViewArtist.setImageResource(concerts.get(position).getArtist().getImage());
        viewHolder.textViewTitle.setText(concerts.get(position).getArtist().getName());
        viewHolder.textViewRating.setText(""+concerts.get(position).getRating());
        viewHolder.textViewSubtitle.setText(concerts.get(position).getVenue().getName()+"\n"+concerts.get(position).getDate());
        viewHolder.constraintLayout.setOnClickListener(new View.OnClickListener() {
                                                         @Override
                                                         public void onClick(View v) {
                                                             mainActivity.switchFragment(mainActivity.getConcertFragment(concerts.get(position)),true);
                                                         }
                                                     }

        );
    }

    @Override
    public int getItemCount() {
        return concerts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView imageViewArtist;
        TextView textViewTitle;
        TextView textViewSubtitle;
        TextView textViewRating;
        ConstraintLayout constraintLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewArtist = itemView.findViewById(R.id.reviewImageviewUser);
            textViewTitle = itemView.findViewById(R.id.reviewTextviewUser);
            textViewSubtitle = itemView.findViewById(R.id.searchTextviewSubtitle);
            textViewRating = itemView.findViewById(R.id.reviewTextviewRating);
            constraintLayout = itemView.findViewById(R.id.searchConstraint);
        }
    }


    public void search(ArrayList<Concert> newConcerts, String query)
    {

        ArrayList<Concert> concertsFiltered = new ArrayList<>();


        ArrayList<Concert> venuesFiltered = new ArrayList<>();
        ArrayList<Concert> artistsFiltered = new ArrayList<>();
        String q = query.toLowerCase();
        for (Concert c: newConcerts) {
            if(c.getArtist().getName().toLowerCase().contains(q) || c.getVenue().getName().toLowerCase().contains(q))
            {
                concertsFiltered.add(c);
                Log.d(TAG, "search: added!");
            }
            if(c.getArtist().getName().toLowerCase().contains(q))
            {
                artistsFiltered.add(c);
            }
            if(c.getVenue().getName().toLowerCase().contains(q))
            {
                venuesFiltered.add(c);
            }
        }

        concerts = concertsFiltered;
        Log.d(TAG, "search: "+ concertsFiltered.size());
        this.notifyDataSetChanged();
    }
}
