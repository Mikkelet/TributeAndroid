package com.tributedummy.metbb.dummy3.databinding;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Adapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.tributedummy.metbb.dummy3.R;
import com.tributedummy.metbb.dummy3.adapters.ConcertPhotosRVA;
import com.tributedummy.metbb.dummy3.classes.Concert;

import java.util.ArrayList;
import java.util.List;

public class GlideBindingAdapter {
    /**
     * this class creates a new binding method in the XML that takes an int and set the image from that int using Glide.
     * @param view
     * @param imageUrl
     */
    @BindingAdapter("imageUrl")
    public static void setImageResourse(ImageView view, int imageUrl)
    {
        Context context = view.getContext();
        RequestOptions requestOptions = new RequestOptions()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background);
        Glide.with(context)
                .setDefaultRequestOptions(requestOptions)
                .load(imageUrl)
                .into(view);
    }

    @BindingAdapter("concertPhotosAdapter")
    public static void setConcerts(RecyclerView v, ArrayList<Integer> photos)
    {
        v.setLayoutManager(new LinearLayoutManager(v.getContext(), LinearLayoutManager.HORIZONTAL,true));
        ConcertPhotosRVA adapter = new ConcertPhotosRVA(v.getContext(),photos);
        v.setAdapter(adapter);
    }
}