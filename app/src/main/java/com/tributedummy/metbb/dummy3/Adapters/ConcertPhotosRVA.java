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
import com.tributedummy.metbb.dummy3.R;

import java.util.ArrayList;

public class ConcertPhotosRVA extends  RecyclerView.Adapter<ConcertPhotosRVA.ViewHolder> {
    private static final String TAG = "";

    private Context mContext;
    private ArrayList<Integer> photos;

    public ConcertPhotosRVA(Context mContext, ArrayList<Integer> photos) {
        this.mContext = mContext;
        this.photos = photos;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_concertphotos, viewGroup, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int position) {
        Log.d(TAG, "onBindViewHolder: called");

        viewHolder.imageViewArtist.setImageResource(photos.get(position));
        viewHolder.imageViewArtist.setOnClickListener(new View.OnClickListener() {
                                                         @Override
                                                         public void onClick(View v) {
                                                             Toast.makeText(mContext, photos.get(position), Toast.LENGTH_SHORT).show();
                                                         }
                                                     }

        );
    }

    @Override
    public int getItemCount() {
        return photos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView imageViewArtist;
        ConstraintLayout constraintLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewArtist = itemView.findViewById(R.id.concertphotoImageview);
            constraintLayout = itemView.findViewById(R.id.concertphotoConstraint);
        }
    }
}
