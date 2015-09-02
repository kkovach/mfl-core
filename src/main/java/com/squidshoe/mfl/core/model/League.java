package com.squidshoe.mfl.core.model;

import com.google.gson.annotations.Expose;

/**
 * Created by kkovach on 3/31/15.
 */
public class League {

    public static final String LEAGUE = "league";
    public static final String VERSION = "version";
    public static final String ENCODING = "encoding";

    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String YEAR = "year";

    @Expose
    public String version;
    @Expose
    public String encoding;

    @Expose
    public String currentWaiverType;
    @Expose
    public String playerLimitUnit;
    @Expose
    public String taxiSquad;
    @Expose
    public String endWeek;
    @Expose
    public String maxWaiverRounds;
    @Expose
    public String lockout;
    @Expose
    public String nflPoolStartWeek;
    @Expose
    public Franchises franchises;
    @Expose
    public String standingsSort;
    @Expose
    public String id;
    @Expose
    public Integer year;
    @Expose
    public String nflPoolType;
    @Expose
    public History history;
    @Expose
    public String rosterSize;
    @Expose
    public String name;
    @Expose
    public String fantasyPoolType;
    @Expose
    public String draftLimitHours;
    @Expose
    public Starters starters;
    @Expose
    public String fantasyPoolEndWeek;
    @Expose
    public String nflPoolEndWeek;
    @Expose
    public String precision;
    @Expose
    public Conferences conferences;
    @Expose
    public String lastRegularSeasonWeek;
    @Expose
    public String survivorPool;
    @Expose
    public String injuredReserve;
    @Expose
    public String startWeek;
    @Expose
    public String survivorPoolStartWeek;
    @Expose
    public String fantasyPoolStartWeek;
    @Expose
    public String survivorPoolEndWeek;
    @Expose
    public String rostersPerPlayer;
    @Expose
    public String leagueLogo;
    @Expose
    public String h2h;
    @Expose
    public RosterLimits rosterLimits;
    @Expose
    public String baseURL;
    @Expose
    public Divisions divisions;
    @Expose
    public String loadRosters;

    public League() {

        conferences = new Conferences();
        divisions = new Divisions();
        franchises = new Franchises();
    }

    public String toString() {

        if (name != null) {
            return "(" + year + ") " + name;
        } else {
            return "(" + year + ") " + id;
        }
    }
}
