package com.squidshoe.mfl.core.model;

import com.google.gson.annotations.Expose;

/**
 * Created by kkovach on 3/31/15.
 */
public class LeagueHistory {

    public static final String URL = "url";
    public static final String YEAR = "year";

    @Expose
    public String url;
    @Expose
    public String year;
}
