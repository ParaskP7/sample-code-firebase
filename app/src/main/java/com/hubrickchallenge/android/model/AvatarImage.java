package com.hubrickchallenge.android.model;

import com.google.firebase.database.IgnoreExtraProperties;
import com.google.firebase.database.PropertyName;

import io.realm.RealmObject;

@IgnoreExtraProperties
public class AvatarImage extends RealmObject {

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

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "AvatarImage{" +
                "mimeType='" + mimeType + '\'' +
                ", url='" + url + '\'' +
                '}';
    }

}
