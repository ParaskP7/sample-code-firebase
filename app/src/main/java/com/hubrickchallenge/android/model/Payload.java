package com.hubrickchallenge.android.model;

import com.google.firebase.database.IgnoreExtraProperties;
import com.google.firebase.database.PropertyName;

import io.realm.RealmObject;

import static com.hubrickchallenge.android.util.GeneralUtil.HASH_CODE;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Payload payload = (Payload) o;

        if (!stats.equals(payload.stats)) return false;
        if (!plainTitle.equals(payload.plainTitle)) return false;
        if (!plainContentPreview.equals(payload.plainContentPreview)) return false;
        if (!path.equals(payload.path)) return false;
        return headLineImage.equals(payload.headLineImage);

    }

    @Override
    public int hashCode() {
        int result = stats.hashCode();
        result = HASH_CODE * result + plainTitle.hashCode();
        result = HASH_CODE * result + plainContentPreview.hashCode();
        result = HASH_CODE * result + path.hashCode();
        result = HASH_CODE * result + headLineImage.hashCode();
        return result;
    }

}
