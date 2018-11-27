package com.tributedummy.metbb.dummy3.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tributedummy.metbb.dummy3.classes.Concert;
import com.tributedummy.metbb.dummy3.classes.ConcertStatus;
import com.tributedummy.metbb.dummy3.MainActivity;
import com.tributedummy.metbb.dummy3.databinding.LayoutSmallcardBinding;

import java.util.ArrayList;

public class SmallCardAdapter extends  RecyclerView.Adapter<SmallCardAdapter.ViewHolder> {
    private static final String TAG = "";

    private Context mContext;
    private MainActivity mainActivity;
    private ArrayList<Concert> concerts;
    private LayoutSmallcardBinding smallcardBinding;


    public SmallCardAdapter(Context mContext, ArrayList<Concert> concerts) {
        this.mContext = mContext;
        this.concerts = concerts;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        mainActivity = ((MainActivity)mContext);
        smallcardBinding = LayoutSmallcardBinding.inflate(LayoutInflater.from((viewGroup.getContext())));

        return new ViewHolder(smallcardBinding.getRoot());
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int position) {
        Log.d(TAG, "onBindViewHolder: called");
        // adds OCL. If status is not done, go to the upcoming fragments, else go to the normal concert fragment
        smallcardBinding.setConcert(concerts.get(position));
        smallcardBinding.smallcardConstraint.setOnClickListener(new View.OnClickListener() {
                                                          @Override
                                                          public void onClick(View v) {
                                                              if(concerts.get(position).getStatus() != ConcertStatus.DONE)
                                                                  mainActivity.switchFragment(mainActivity.getUpcomingConcertFragment(concerts.get(position)),true);
                                                              else
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
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    public ArrayList<Concert> getConcerts() {
        return concerts;
    }
}
