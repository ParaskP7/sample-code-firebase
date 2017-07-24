package com.hubrickchallenge.android.model;

import com.google.firebase.database.IgnoreExtraProperties;
import com.google.firebase.database.PropertyName;

import io.realm.RealmObject;

@IgnoreExtraProperties
public class Payload extends RealmObject {

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

    public void setStats(Stats stats) {
        this.stats = stats;
    }

    public String getPlainTitle() {
        return plainTitle;
    }

    public void setPlainTitle(String plainTitle) {
        this.plainTitle = plainTitle;
    }

    public String getPlainContentPreview() {
        return plainContentPreview;
    }

    public void setPlainContentPreview(String plainContentPreview) {
        this.plainContentPreview = plainContentPreview;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public HeadLineImage getHeadLineImage() {
        return headLineImage;
    }

    public void setHeadLineImage(HeadLineImage headLineImage) {
        this.headLineImage = headLineImage;
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
