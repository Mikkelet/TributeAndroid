package com.tributedummy.metbb.dummy3.adapters;

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

import com.tributedummy.metbb.dummy3.R;

import java.util.ArrayList;

public class ProfilemenuRVA extends  RecyclerView.Adapter<ProfilemenuRVA.ViewHolder> {
    private static final String TAG = "";

    private Context mContext;
    private ArrayList<String> menuOptions;
    private ArrayList<View.OnClickListener> menuActions;
    private ArrayList<Integer> menuImages;


    public ProfilemenuRVA(Context mContext, ArrayList<String> menuOptions, ArrayList<Integer> menuImages, ArrayList<View.OnClickListener> menuActions) {
        this.mContext = mContext;
        this.menuOptions = menuOptions;
        this.menuImages = menuImages;
        this.menuActions = menuActions;
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

        viewHolder.imageViewMenuImage.setImageResource(menuImages.get(position));
        viewHolder.textViewMenuOption.setText(menuOptions.get(position));
        viewHolder.constraintLayout.setOnClickListener(menuActions.get(position));
    }

    @Override
    public int getItemCount() {
        return menuOptions.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView imageViewMenuImage;
        TextView textViewMenuOption;
        ConstraintLayout constraintLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewMenuImage = itemView.findViewById(R.id.profilemenuImageview);
            textViewMenuOption = itemView.findViewById(R.id.profilemenuTextview);
            constraintLayout = itemView.findViewById(R.id.profilemenuConstraint);
        }
    }

}
