package com.tributedummy.metbb.dummy3.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.tributedummy.metbb.dummy3.Classes.Concert;
import com.tributedummy.metbb.dummy3.Classes.Review;
import com.tributedummy.metbb.dummy3.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ConcertReviewsRVA extends  RecyclerView.Adapter<ConcertReviewsRVA.ViewHolder> {
    private static final String TAG = "RecyclerViewAdapter";

    private Context mContext;
    private Concert concerts;
    private LinearLayout linearLayout;

    public ConcertReviewsRVA(Context mContext, Concert concerts, LinearLayout linearLayout) {
        this.mContext = mContext;
        this.concerts = concerts;
        this.linearLayout = linearLayout;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_reviewcard, viewGroup, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int position) {
        Log.d(TAG, "onBindViewHolder: called");
        final Review currentReview = concerts.getReviews().get(position);
        viewHolder.imageViewUser.setImageResource(currentReview.getSubmittedBy().getProfilePic());
        viewHolder.textViewUser.setText(currentReview.getSubmittedBy().getName());
        viewHolder.textViewDate.setText(currentReview.getDate());
        viewHolder.textViewRating.setText(""+currentReview.getRating());
        viewHolder.textViewConcert.setText(concerts.getArtist().getName()+" / "+concerts.getVenue().getName());
        viewHolder.textViewReview.setText(currentReview.getReview());
        viewHolder.textViewFavourites.setText(""+currentReview.getFavourites());
        viewHolder.buttonGotoConcert.setOnClickListener(new View.OnClickListener() {
                                                         @Override
                                                         public void onClick(View v) {
                                                             Toast.makeText(mContext, currentReview.getSubmittedBy().getName(), Toast.LENGTH_SHORT).show();
                                                         }
                                                     }

        );
        linearLayout.addView(viewHolder.itemView);
    }

    @Override
    public int getItemCount() {
        return concerts.getReviews().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView imageViewUser;
        TextView textViewUser;
        TextView textViewDate;
        TextView textViewRating;
        TextView textViewReview;
        TextView textViewFavourites;
        TextView textViewConcert;
        ImageButton buttonGotoConcert;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewUser = itemView.findViewById(R.id.reviewImageviewUser);
            textViewUser = itemView.findViewById(R.id.reviewTextviewUser);
            textViewRating = itemView.findViewById(R.id.reviewTextviewRating);
            textViewDate = itemView.findViewById(R.id.reviewTextviewDate);
            textViewReview = itemView.findViewById(R.id.reviewTextviewReview);
            textViewFavourites = itemView.findViewById(R.id.reviewTextviewFavorites);
            textViewConcert = itemView.findViewById(R.id.reviewTextviewConcert);
            buttonGotoConcert = itemView.findViewById(R.id.reviewButtonGotoconcert);

        }
    }
}
