package com.hubrickchallenge.android.model;

import com.google.firebase.database.IgnoreExtraProperties;
import com.google.firebase.database.PropertyName;

@IgnoreExtraProperties
public class HeadLineImage {

    @PropertyName("mimeType")
    private String mimeType;
    @PropertyName("url")
    private String url;

    public HeadLineImage() {
        // Default constructor required for calls to DataSnapshot.getValue(HeadLineImage.class)
    }

    public String getMimeType() {
        return mimeType;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public String toString() {
        return "HeadLineImage{" +
                "mimeType='" + mimeType + '\'' +
                ", url='" + url + '\'' +
                '}';
    }

}
