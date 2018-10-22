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

import java.util.ArrayList;

public class SmallCardRVA extends  RecyclerView.Adapter<SmallCardRVA.ViewHolder> {
    private static final String TAG = "RecyclerViewAdapter";

    private Context mContext;
    private ArrayList<Concert> concerts;

    private MainActivity mainActivity;

    public SmallCardRVA(Context mContext, ArrayList<Concert> concerts) {
        this.mContext = mContext;
        this.concerts = concerts;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_smallcard, viewGroup, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int position) {
        Log.d(TAG, "onBindViewHolder: called");
        mainActivity = ((MainActivity)mContext);
        viewHolder.imageViewArtist.setImageResource(concerts.get(position).getArtist().getImage());
        viewHolder.textViewArtist.setText(concerts.get(position).getArtist().getName());
        viewHolder.textViewRating.setText(""+concerts.get(position).getRating());
        viewHolder.textViewVenue.setText(concerts.get(position).getVenue().getName());
        viewHolder.imageViewArtist.setOnClickListener(new View.OnClickListener() {
                                                         @Override
                                                         public void onClick(View v) {
                                                             Toast.makeText(mContext, concerts.get(position).getArtist().getName(), Toast.LENGTH_SHORT).show();
                                                             mainActivity.SwitchFragment(mainActivity.getConcertFragment(concerts.get(position)));
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
        ConstraintLayout constraintLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewArtist = itemView.findViewById(R.id.smallcardImageviewArtist);
            textViewArtist = itemView.findViewById(R.id.smallcardTextviewArtist);
            textViewVenue = itemView.findViewById(R.id.smallcardTextviewVenue);
            textViewRating = itemView.findViewById(R.id.smallcardTextviewRating);
            constraintLayout = itemView.findViewById(R.id.smallcardConstraint);
        }
    }
}