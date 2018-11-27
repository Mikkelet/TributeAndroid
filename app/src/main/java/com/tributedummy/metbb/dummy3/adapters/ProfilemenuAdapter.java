package com.tributedummy.metbb.dummy3.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tributedummy.metbb.dummy3.databinding.LayoutProfilemenuBinding;

import java.util.ArrayList;

public class ProfilemenuAdapter extends  RecyclerView.Adapter<ProfilemenuAdapter.ViewHolder> {
    private static final String TAG = "";

    private Context mContext;
    private ArrayList<String> menuOptions;
    private ArrayList<View.OnClickListener> menuActions;
    private ArrayList<Integer> menuImages;
    private LayoutProfilemenuBinding profilemenuBinding;


    public ProfilemenuAdapter(Context mContext, ArrayList<String> menuOptions, ArrayList<Integer> menuImages, ArrayList<View.OnClickListener> menuActions) {
        this.mContext = mContext;
        this.menuOptions = menuOptions;
        this.menuImages = menuImages;
        this.menuActions = menuActions;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        profilemenuBinding = LayoutProfilemenuBinding.inflate(LayoutInflater.from(viewGroup.getContext()));
        return new ViewHolder(profilemenuBinding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int position) {
        Log.d(TAG, "onBindViewHolder: called");

        profilemenuBinding.setImage(menuImages.get(position));
        profilemenuBinding.setMenuOption(menuOptions.get(position));
        profilemenuBinding.setOnclick(menuActions.get(position));
    }

    @Override
    public int getItemCount() {
        return menuOptions.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

}
