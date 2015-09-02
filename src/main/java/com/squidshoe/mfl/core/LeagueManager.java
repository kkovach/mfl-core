package com.squidshoe.mfl.core;

import com.squidshoe.mfl.core.model.League;
import com.squidshoe.mfl.core.model.LeagueStandings;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by kkovach on 8/24/15.
 */
public class LeagueManager {

    @Inject
    MflService mflService;
    private Integer mYear;
    private String mId;

    public LeagueManager(Integer year, String id) {

        DaggerMflComponent.builder().build().inject(this);
        mYear = year;
        mId = id;
    }

    public void setYear(Integer year) {

        mYear = year;
    }

    public void setId(String id) {

        mId = id;
    }

    Observable<List<League>> search(String searchString) {

        return mflService.leagueSearch(Constants.LEAGUE_SEARCH, mYear, searchString, Constants.JSON_ENABLED);
    }

    Observable<League> get() {

        return mflService.getLeague(Constants.LEAGUE_GET, mYear, mId, Constants.JSON_ENABLED);
    }

    Observable<LeagueStandings> standings() {

        return mflService.getStandings(Constants.LEAGUE_STANDINGS, mYear, mId, Constants.JSON_ENABLED);
    }
}
