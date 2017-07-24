package com.hubrickchallenge.android.model;

import com.google.firebase.database.IgnoreExtraProperties;
import com.google.firebase.database.PropertyName;

@IgnoreExtraProperties
public class AvatarImage {

    @PropertyName("mimeType")
    private String mimeType;
    @PropertyName("url")
    private String url;

    public AvatarImage() {
        // Default constructor required for calls to DataSnapshot.getValue(AvatarImage.class)
    }

    public String getMimeType() {
        return mimeType;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public String toString() {
        return "AvatarImage{" +
                "mimeType='" + mimeType + '\'' +
                ", url='" + url + '\'' +
                '}';
    }

}
