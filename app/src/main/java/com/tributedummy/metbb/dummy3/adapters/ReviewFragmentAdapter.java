package com.tributedummy.metbb.dummy3.adapters;

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

import com.tributedummy.metbb.dummy3.classes.Concert;
import com.tributedummy.metbb.dummy3.MainActivity;
import com.tributedummy.metbb.dummy3.R;

import java.util.ArrayList;

public class ReviewFragmentAdapter extends  RecyclerView.Adapter<ReviewFragmentAdapter.ViewHolder> {
    private static final String TAG = "reviewRVA";

    private Context mContext;
    private ArrayList<Concert> concerts;
    private MainActivity mainActivity;

    public ReviewFragmentAdapter(Context mContext, ArrayList<Concert> concerts) {
        this.mContext = mContext;
        this.concerts = concerts;
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
        mainActivity = (MainActivity)mContext;
        viewHolder.imageViewArtist.setImageResource(concerts.get(position).getArtist().getImage());
        viewHolder.textViewArtist.setText(concerts.get(position).getArtist().getName());
        viewHolder.textViewRating.setText("" + concerts.get(position).getRating());
        viewHolder.textViewVenue.setText(concerts.get(position).getVenue().getName() + "\n" + concerts.get(position).getDate());
        viewHolder.imageViewArtist.setOnClickListener(new View.OnClickListener() {
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
        TextView textViewArtist;
        TextView textViewVenue;
        TextView textViewRating;
        CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewArtist = itemView.findViewById(R.id.bigcardImageViewArtist);
            textViewArtist = itemView.findViewById(R.id.artistTexView);
            textViewVenue = itemView.findViewById(R.id.venueTextView);
            textViewRating = itemView.findViewById(R.id.ratingTextView);
            cardView = itemView.findViewById(R.id.layout_allconcerts);
        }
    }
}
