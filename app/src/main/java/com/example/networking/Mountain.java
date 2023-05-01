package com.example.networking;

import android.media.Image;

public class Mountain {
    private String name;
    private String location;
    private int elevationM;
    private int elevationFt;
    private String wikiLink;

    public Mountain(String name) {
        this.name = name;
    }

    public Mountain(String name, String location, int elevationM, int elevationFt, String wikiLink) {
        this.name = name;
        this.location = location;
        this.elevationM = elevationM;
        this.elevationFt = elevationFt;
        this.wikiLink = wikiLink;
    }

    public String getName() {
        return name;
    }
}
