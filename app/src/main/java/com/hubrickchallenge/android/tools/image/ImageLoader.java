package com.hubrickchallenge.android.tools.image;

import android.net.Uri;
import android.support.annotation.DrawableRes;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import timber.log.Timber;

public final class ImageLoader {

    public static void loadImage(String url, ImageView imageView) {
        Timber.d("Fetching image from url: %s", url);
        Uri uri = Uri.parse(url);
        Glide.with(imageView.getContext())
                .load(uri)
                .into(imageView);
    }

    public static void loadImage(@DrawableRes int drawableId, ImageView imageView) {
        Timber.d("Fetching image from drawable id: %s", drawableId);
        Glide.with(imageView.getContext())
                .load(drawableId)
                .into(imageView);
    }

    private ImageLoader() {
        throw new AssertionError();
    }

}
