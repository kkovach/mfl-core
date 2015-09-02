package com.squidshoe.mfl.core.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kkovach on 3/31/15.
 */
public class Conferences {

    @Expose
    public String count;
    @Expose
    @SerializedName("conference")
    public List<Conference> conferences;

    public Conferences() {

        conferences = new ArrayList<>();
    }
}
