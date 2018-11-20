package com.tributedummy.metbb.dummy3.databinding;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.MultiTransformation;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.tributedummy.metbb.dummy3.R;
import jp.co.cyberagent.android.gpuimage.filter.GPUImageMultiplyBlendFilter;
import jp.wasabeef.blurry.Blurry;
import jp.wasabeef.glide.transformations.BlurTransformation;
import jp.wasabeef.glide.transformations.ColorFilterTransformation;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;
import jp.wasabeef.glide.transformations.gpu.BrightnessFilterTransformation;
import jp.wasabeef.glide.transformations.gpu.ContrastFilterTransformation;

public class BindingAdapters {
    /**
     * this class creates a new binding method in the XML that takes an int and set the image from that int using Glide.
     * @param view
     * @param imageUrl
     */
    @BindingAdapter("imageUrl")
    public static void setImageResource(ImageView view, int imageUrl)
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
    @BindingAdapter("blurryImageUrl")
    public static void setImageResourceBlurry(ImageView view, int imageUrl)
    {
        Context context = view.getContext();
        RequestOptions requestOptions = new RequestOptions()
                .placeholder(R.drawable.ic_launcher_background)
               .error(R.drawable.ic_launcher_background);
        MultiTransformation multi = new MultiTransformation<Bitmap>(
                new BlurTransformation(12,2),
                new ColorFilterTransformation(R.color.blackFaded));
        Glide.with(context)
                .setDefaultRequestOptions(requestOptions)
                .load(imageUrl)
                .apply(RequestOptions.bitmapTransform(multi))
                .into(view);
    }
    @BindingAdapter("circleImageUrl")
    public static void setImageResourceCircle(ImageView view, int imageUrl)
    {
        Context context = view.getContext();
        RequestOptions requestOptions = new RequestOptions()
                .placeholder(R.drawable.ic_launcher_background)
                .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                .error(R.drawable.ic_launcher_background);
        Glide.with(context)
                .setDefaultRequestOptions(requestOptions)
                .load(imageUrl)
                .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                .into(view);
    }
    @BindingAdapter("roundedImageUrl")
<<<<<<< HEAD
    public static void setImageResourceRounded(ImageView view, int imageUrl) {
=======
    public static void setImageResourceRounded(ImageView view, int imageUrl)
    {
>>>>>>> 781c801388d6222f44c6bd27263fcdac89534d9b
        Context context = view.getContext();
        RequestOptions requestOptions = new RequestOptions()
                .placeholder(R.drawable.ic_launcher_background)
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(3)))
                .error(R.drawable.ic_launcher_background);
        Glide.with(context)
                .setDefaultRequestOptions(requestOptions)
                .load(imageUrl)
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(3)))
                .into(view);
    }
}