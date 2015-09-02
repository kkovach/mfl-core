package com.squidshoe.mfl.core.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kkovach on 3/31/15.
 */
public class LeagueRosters {

    public static final String LEAGUE_ROSTERS = "rosters";
    public static final String FRANCHISES = "franchise";
    public static final String VERSION = "version";
    public static final String ENCODING = "encoding";

    @Expose
    public String version;
    @Expose
    @SerializedName(FRANCHISES)
    public List<Franchise> franchises = new ArrayList<>();
    @Expose
    public String encoding;
}
