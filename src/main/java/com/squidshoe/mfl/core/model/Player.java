package com.squidshoe.mfl.core.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by kkovach on 3/31/15.
 */
public class Player {

    public static final String ID = "id";
    public static final String LEAGUE_ID = "leagueId";
    public static final String FRANCHISE_ID = "franchiseId";
    public static final String NAME = "name";
    public static final String POSITION = "position";
    public static final String TEAM = "team";
    public static final String JERSEY = "jersey";
    public static final String HEIGHT = "height";
    public static final String WEIGHT = "weight";
    public static final String STATUS = "status";
    public static final String COLLEGE = "college";
    public static final String DRAFT_YEAR = "draft_year";
    public static final String DRAFT_ROUND = "draft_round";
    public static final String DRAFT_PICK = "draft_pick";
    public static final String DRAFT_TEAM = "draft_team";
    public static final String NFL_ID = "nfl_id";
    public static final String STATS_ID = "stats_id";
    public static final String ESPN_ID = "espn_id";
    public static final String SPORTSTICKER_ID = "sportsticker_id";
    public static final String FANNBALL_ID = "fannball_id";
    public static final String SPORTSDATA_ID = "sportsdata_id";
    public static final String CBS_ID = "cbs_id";
    public static final String FLEAFLICKER_ID = "fleaflicker_id";
    public static final String ROTOWORLD_ID = "rotoworld_id";
    public static final String KFFL_ID = "kffl_id";
    public static final String TWITTER_ID = "twitter_username";

    @Expose
    public String id;
    @SerializedName(DRAFT_YEAR)
    @Expose
    public String draftYear;
    @SerializedName(DRAFT_ROUND)
    @Expose
    public String draftRound;
    @SerializedName(NFL_ID)
    @Expose
    public String nflId;
    @SerializedName(ROTOWORLD_ID)
    @Expose
    public String rotoworldId;
    @SerializedName(STATS_ID)
    @Expose
    public String statsId;
    @Expose
    public String position;
    @Expose
    public String status;
    @SerializedName(ESPN_ID)
    @Expose
    public String espnId;
    @SerializedName(KFFL_ID)
    @Expose
    public String kfflId;
    @Expose
    public String weight;
    @Expose
    public String birthdate;
    @SerializedName(DRAFT_TEAM)
    @Expose
    public String draftTeam;
    @Expose
    public String name;
    @SerializedName(DRAFT_PICK)
    @Expose
    public String draftPick;
    @SerializedName(SPORTSTICKER_ID)
    @Expose
    public String sportstickerId;
    @Expose
    public String college;
    @Expose
    public String height;
    @SerializedName(FANNBALL_ID)
    @Expose
    public String fanballId;
    @Expose
    public Integer jersey;
    @SerializedName(SPORTSDATA_ID)
    @Expose
    public String sportsdataId;
    @Expose
    public String team;
    @SerializedName(CBS_ID)
    @Expose
    public String cbsId;
    @SerializedName(FLEAFLICKER_ID)
    @Expose
    public String fleaflickerId;
    @SerializedName(TWITTER_ID)
    @Expose
    public String twitterUsername;
}
