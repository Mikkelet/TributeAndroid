package com.tributedummy.metbb.dummy3.databinding;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.tributedummy.metbb.dummy3.R;

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
}