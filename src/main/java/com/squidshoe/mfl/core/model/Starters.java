package com.squidshoe.mfl.core.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kkovach on 3/31/15.
 */
public class Starters {

    @Expose
    public String count;
    @Expose
    public List<Position> position = new ArrayList<Position>();
    @SerializedName("idp_starters")
    @Expose
    public String idpStarters;
}
