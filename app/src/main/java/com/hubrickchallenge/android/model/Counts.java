package com.hubrickchallenge.android.model;

import com.google.firebase.database.IgnoreExtraProperties;
import com.google.firebase.database.PropertyName;

import io.realm.RealmObject;

import static com.hubrickchallenge.android.util.GeneralUtil.HASH_CODE;

@IgnoreExtraProperties
public class Counts extends RealmObject {

    @PropertyName("SHARE")
    private Long share;
    @PropertyName("LIKE")
    private Long like;

    public Counts() {
        // Default constructor required for calls to DataSnapshot.getValue(Counts.class)
    }

    public Long getShare() {
        return share;
    }

    public void setShare(Long share) {
        this.share = share;
    }

    public Long getLike() {
        return like;
    }

    public void setLike(Long like) {
        this.like = like;
    }

    @Override
    public String toString() {
        return "Counts{" +
                "share=" + share +
                ", like=" + like +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Counts counts = (Counts) o;

        if (!share.equals(counts.share)) return false;
        return like.equals(counts.like);

    }

    @Override
    public int hashCode() {
        int result = share.hashCode();
        result = HASH_CODE * result + like.hashCode();
        return result;
    }

}
