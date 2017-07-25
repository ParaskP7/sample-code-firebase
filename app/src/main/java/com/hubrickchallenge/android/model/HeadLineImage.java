package com.hubrickchallenge.android.model;

import com.google.firebase.database.IgnoreExtraProperties;
import com.google.firebase.database.PropertyName;

import io.realm.RealmObject;

import static com.hubrickchallenge.android.util.GeneralUtil.HASH_CODE;

@IgnoreExtraProperties
public class HeadLineImage extends RealmObject {

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
        return "HeadLineImage{" +
                "mimeType='" + mimeType + '\'' +
                ", url='" + url + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HeadLineImage that = (HeadLineImage) o;

        if (!mimeType.equals(that.mimeType)) return false;
        return url.equals(that.url);

    }

    @Override
    public int hashCode() {
        int result = mimeType.hashCode();
        result = HASH_CODE * result + url.hashCode();
        return result;
    }

}
