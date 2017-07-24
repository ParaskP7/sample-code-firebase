package com.hubrickchallenge.android.model;

import com.google.firebase.database.IgnoreExtraProperties;
import com.google.firebase.database.PropertyName;

import io.realm.RealmObject;

@IgnoreExtraProperties
public class Author extends RealmObject {

    @PropertyName("name")
    private String name;
    @PropertyName("avatarImage")
    private AvatarImage avatarImage;
    @PropertyName("displayName")
    private String displayName;

    public Author() {
        // Default constructor required for calls to DataSnapshot.getValue(Author.class)
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AvatarImage getAvatarImage() {
        return avatarImage;
    }

    public void setAvatarImage(AvatarImage avatarImage) {
        this.avatarImage = avatarImage;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return "Author{" +
                "name='" + name + '\'' +
                ", avatarImage=" + avatarImage +
                ", displayName='" + displayName + '\'' +
                '}';
    }

}
