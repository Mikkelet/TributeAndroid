package com.tributedummy.metbb.dummy3.adapters;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tributedummy.metbb.dummy3.classes.Concert;
import com.tributedummy.metbb.dummy3.classes.Review;
import com.tributedummy.metbb.dummy3.databinding.LayoutReviewcardBinding;

import java.util.List;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ViewHolder> {

    public Context context;
    private List<Review> reviews;
    private Concert concert;
    private LayoutReviewcardBinding reviewcardBinding;

    public ReviewAdapter(Context context, Concert concert) {
        this.context = context;
        this.concert = concert;
        reviews = concert.getReviews();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        reviewcardBinding = LayoutReviewcardBinding.inflate(LayoutInflater.from(viewGroup.getContext()));
        return new ViewHolder(reviewcardBinding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Review review = reviews.get(i);
        reviewcardBinding.setConcert(concert);
        reviewcardBinding.setReview(review);
        reviewcardBinding.setUser(review.getSubmittedBy());
    }

    @Override
    public int getItemCount() {
        return reviews.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
