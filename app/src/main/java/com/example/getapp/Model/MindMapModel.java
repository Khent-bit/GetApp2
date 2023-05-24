package com.example.getapp.Model;

import android.graphics.drawable.Drawable;

public class MindMapModel extends MindMapId {
    private String task;
    private byte[] mapImage;
    private Drawable map;

    public String getTask() {
        return task;
    }

    public void setMapImage(byte[] mapImage) {
        this.mapImage = mapImage;
    }

    public Drawable getMap() {
        return map;
    }

    public void setMap(Drawable map) {
        this.map = map;
    }
}
