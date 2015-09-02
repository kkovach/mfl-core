package com.squidshoe.mfl.core.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kkovach on 3/31/15.
 */
public class Franchises {

    @Expose
    public String count;
    @Expose
    @SerializedName("franchise")
    public List<Franchise> franchises;

    public Franchises() {

        franchises = new ArrayList<>();
    }
}
