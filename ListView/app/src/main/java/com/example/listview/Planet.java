package com.example.listview;

import android.widget.ImageView;
import android.widget.TextView;

public class Planet {
    String name;
    String desc;
    int imageView;

    public Planet() {
        this.name = "";
        this.desc = "";
        this.imageView = 0;
    }

    public Planet(String name, String desc, int imageView) {
        this.name = name;
        this.desc = desc;
        this.imageView = imageView;
    }
}