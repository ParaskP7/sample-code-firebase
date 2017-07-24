package com.hubrickchallenge.android.model;

import com.google.firebase.database.IgnoreExtraProperties;
import com.google.firebase.database.PropertyName;

@IgnoreExtraProperties
public class Payload {

    @PropertyName("stats")
    private Stats stats;
    @PropertyName("plainTitle")
    private String plainTitle;
    @PropertyName("plainContentPreview")
    private String plainContentPreview;
    @PropertyName("path")
    private String path;
    @PropertyName("headLineImage")
    private HeadLineImage headLineImage;

    public Payload() {
        // Default constructor required for calls to DataSnapshot.getValue(Payload.class)
    }

    public Stats getStats() {
        return stats;
    }

    public String getPlainTitle() {
        return plainTitle;
    }

    public String getPlainContentPreview() {
        return plainContentPreview;
    }

    public String getPath() {
        return path;
    }

    public HeadLineImage getHeadLineImage() {
        return headLineImage;
    }

    @Override
    public String toString() {
        return "Payload{" +
                "stats=" + stats +
                ", plainTitle='" + plainTitle + '\'' +
                ", plainContentPreview='" + plainContentPreview + '\'' +
                ", path='" + path + '\'' +
                ", headLineImage=" + headLineImage +
                '}';
    }

}
