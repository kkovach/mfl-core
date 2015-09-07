package com.squidshoe.mfl.core.model;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kkovach on 3/31/15.
 */
public class History {

    public static final String LEAGUE = "league";

    @Expose
    public List<LeagueHistory> league = new ArrayList<LeagueHistory>();
}
