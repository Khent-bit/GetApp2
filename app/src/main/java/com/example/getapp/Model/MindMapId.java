package com.example.getapp.Model;

import androidx.annotation.NonNull;

import com.google.firebase.firestore.Exclude;

public class MindMapId {
    @Exclude
    public String MindMapId;

    public <M extends MindMapId> M withId(@NonNull final String id){
        this.MindMapId = id;
        return (M) this;
    }
}
