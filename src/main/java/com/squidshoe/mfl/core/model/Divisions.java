package com.squidshoe.mfl.core.model;

/**
 * Created by kkovach on 3/31/15.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Divisions {

    @Expose
    public String count;
    @Expose
    @SerializedName("division")
    public List<Division> divisions;

    public Divisions() {

        divisions = new ArrayList<>();
    }
}
