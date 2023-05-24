package com.example.getapp.Model;

import androidx.annotation.NonNull;

import com.google.firebase.firestore.Exclude;

public class BraceMapId {
    @Exclude
    public String BraceMapId;

    public <B extends BraceMapId> B withId(@NonNull final String id){
        this.BraceMapId = id;
        return (B) this;
    }
}
