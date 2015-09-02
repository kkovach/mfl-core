package com.squidshoe.mfl.core.model;

import com.google.gson.annotations.Expose;

/**
 * Created by kkovach on 3/31/15.
 */
public class StandingsResponse {

    @Expose
    public String version;
    @Expose
    private LeagueStandings leagueStandings;
    @Expose
    private String encoding;
}
