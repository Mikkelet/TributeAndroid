package com.tributedummy.metbb.dummy3.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.tributedummy.metbb.dummy3.R;

import java.util.ArrayList;

public class RecyclerViewAdapter  extends  RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private static final String TAG = "RecyclerViewAdapter";

    private Context mContext;
    private ArrayList<String> artists;
    private ArrayList<Integer> artistCoverIDs;
    private ArrayList<Double> ratings;
    private ArrayList<String> venues;

    public RecyclerViewAdapter(ArrayList<String> artists, ArrayList<String> venues, ArrayList<Double> ratings, ArrayList<Integer> artistCoverIDs, Context mContext) {
        this.artists = artists;
        this.mContext = mContext;
        this.artistCoverIDs = artistCoverIDs;
        this.ratings = ratings;
        this.venues = venues;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_bigcard, viewGroup, false);

        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int position) {
        Log.d(TAG, "onBindViewHolder: called");

        viewHolder.imageViewArtist.setImageResource(artistCoverIDs.get(position));
        viewHolder.textViewArtist.setText(artists.get(position));
        viewHolder.textViewRating.setText(ratings.get(position).toString());
        viewHolder.textViewVenue.setText(venues.get(position));
        viewHolder.imageViewArtist.setOnClickListener(new View.OnClickListener() {
                                                         @Override
                                                         public void onClick(View v) {
                                                             Log.d(TAG, "onClick: clicked: on"+artists.get(position));
                                                             Toast.makeText(mContext, artists.get(position), Toast.LENGTH_SHORT).show();
                                                         }
                                                     }

        );
        /*
        Glide.with(mContext).asDrawable()
                .load(artistCoverIDs.get(position))//get position in array -- mImages.get(position)
                .into(viewHolder.imageViewArtist);
        viewHolder.imageView.setImageResource(mImageIDs.get(position));
        viewHolder.textView.setText(mImageNames.get(position));
        viewHolder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: clicked: on"+mImageNames.get(position));
                Toast.makeText(mContext,mImageNames.get(position), Toast.LENGTH_SHORT).show();
            }}
            );*/

    }

    @Override
    public int getItemCount() {
        return artists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView imageViewArtist;
        TextView textViewArtist;
        TextView textViewVenue;
        TextView textViewRating;
        CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewArtist = itemView.findViewById(R.id.imageViewArtist);
            textViewArtist = itemView.findViewById(R.id.artistTexView);
            textViewVenue = itemView.findViewById(R.id.venueTextView);
            textViewRating = itemView.findViewById(R.id.ratingTextView);
            cardView = itemView.findViewById(R.id.layout_allconcerts);
        }
    }
}
