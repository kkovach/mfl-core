package com.squidshoe.mfl.core.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kkovach on 3/31/15.
 */
public class Franchise {

    public static final String LEAGUE_YEAR = "leagueYear";
    public static final String LEAGUE_ID = "leagueId";
    public static final String LOGO = "logo";
    public static final String ICON = "icon";
    public static final String DIVISION = "division";
    public static final String NAME = "name";
    public static final String ID = "id";
    public static final String WAIVER_SORT_ORDER = "waiverSortOrder";
    public static final String ABBREVIATION = "abbrev";
    public static final String IS_COMMISH = "iscommish";
    public static final String SOUND = "sound";
    public static final String URL = "url";
    public static final String ROSTER = "player";
    public static final String HEAD_TO_HEAD_WINS = "h2hw";
    public static final String HEAD_TO_HEAD_LOSSES = "h2hl";
    public static final String HEAD_TO_HEAD_TIES = "h2ht";
    public static final String POWER_RANK = "power_rank";
    public static final String DEFENSIVE_POINTS = "dp";
//    public static final String
//    public static final String
//    public static final String
//    public static final String
//    public static final String
//    public static final String

    // League Attributes
    @Expose
    public String logo;
    @Expose
    public String icon;
    @Expose
    public String division;
    @Expose
    public String name;
    @Expose
    public String id;
    @Expose
    public String waiverSortOrder;
    @Expose
    public String abbrev;
    @Expose
    public String iscommish;
    @Expose
    public String sound;
    @Expose
    public String url;
    
    // Roster Attributes
    @Expose
    @SerializedName(ROSTER)
    public List<Player> roster = new ArrayList<Player>();

    // Standings Attributes
    @Expose
    public Integer h2hl;
    @SerializedName(POWER_RANK)
    @Expose
    public String powerRank;
    @Expose
    public Double dp;
    @Expose
    public Double pf;
    @SerializedName("streak_len")
    @Expose
    public String streakLen;
    @Expose
    public Double divpf;
    @Expose
    public Integer conft;
    @Expose
    public Double pa;
    @Expose
    public String acct;
    @Expose
    public Double maxpa;
    @Expose
    public Integer h2ht;
    @Expose
    public Double confpf;
    @SerializedName("all_play_l")
    @Expose
    public Integer allPlayL;
    @Expose
    public Integer h2hw;
    @Expose
    public Integer confl;
    @Expose
    public Integer confw;
    @SerializedName("all_play_w")
    @Expose
    public Integer allPlayW;
    @Expose
    public String altpwr;
    @Expose
    public String vp;
    @Expose
    public Double pp;
    @Expose
    public Double pwr;
    @Expose
    public Integer divw;
    @Expose
    public Double minpa;
    @Expose
    public Integer divl;
    @SerializedName("all_play_t")
    @Expose
    public Integer allPlayT;
    @SerializedName("streak_type")
    @Expose
    public String streakType;
    @Expose
    public String op;
    @Expose
    public Integer divt;

    public String toString() {

        return name + "(" + id + ")";
    }

}
