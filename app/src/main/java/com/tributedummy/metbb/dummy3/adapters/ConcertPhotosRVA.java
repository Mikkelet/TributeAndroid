package com.tributedummy.metbb.dummy3.adapters;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.tributedummy.metbb.dummy3.classes.Concert;
import com.tributedummy.metbb.dummy3.databinding.LayoutConcertphotosBinding;

import java.util.ArrayList;

public class ConcertPhotosRVA extends  RecyclerView.Adapter<ConcertPhotosRVA.ViewHolder> {
    private static final String TAG = "ConcertPhotosRVA";

    private LayoutConcertphotosBinding concertphotosBinding;

    private Context mContext;
    private ArrayList<Integer> photos;

    public ConcertPhotosRVA(Context mContext, ArrayList<Integer> photos) {
        this.mContext = mContext;
        this.photos = photos;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        concertphotosBinding = LayoutConcertphotosBinding.inflate(LayoutInflater.from(viewGroup.getContext()));
        return new ViewHolder(concertphotosBinding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int position) {
        Log.d(TAG, "onBindViewHolder: called");
        concertphotosBinding.setImage(photos.get(position));
        concertphotosBinding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, photos.get(position), Toast.LENGTH_SHORT).show(); }
        }
        );
    }

    @Override
    public int getItemCount() {
        return photos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
