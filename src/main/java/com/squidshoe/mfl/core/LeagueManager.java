package com.squidshoe.mfl.core;

import com.squidshoe.mfl.core.model.Franchise;
import com.squidshoe.mfl.core.model.League;
import com.squidshoe.mfl.core.model.LeagueStandings;
import com.squidshoe.mfl.core.model.Player;
import com.squidshoe.mfl.core.util.StringUtils;

import java.util.List;

import javax.inject.Inject;

import retrofit.RestAdapter;
import rx.Observable;

/**
 * Created by kkovach on 8/24/15.
 */
public class LeagueManager {

    @Inject
    MflService mflService;
    private Integer mYear;
    private String mId;

    public LeagueManager(Integer year, String id, RestAdapter.LogLevel ll) {

        if (ll == null) {
            DaggerMflComponent.builder().build().inject(this);
        } else {
            DaggerMflComponent.builder().networkModule(new NetworkModule(ll)).build().inject(this);
        }
        mYear = year;
        mId = id;
    }

    public void setYear(Integer year) {

        mYear = year;
    }

    public void setId(String id) {

        mId = id;
    }

    public Observable<List<League>> search(String searchString) {

        return mflService.leagueSearch(Constants.LEAGUE_SEARCH, mYear, searchString, Constants.JSON_ENABLED);
    }

    public Observable<League> get() {

        return mflService.getLeague(Constants.LEAGUE_GET, mYear, mId, Constants.JSON_ENABLED);
    }

    public Observable<LeagueStandings> standings() {

        return mflService.getStandings(Constants.LEAGUE_STANDINGS, mYear, mId, Constants.JSON_ENABLED);
    }

    public Observable<List<Player>> roster(String franchiseId) {

        return mflService.getRoster(Constants.ROSTER, mYear, mId, franchiseId, Constants.JSON_ENABLED);
    }

    public Observable<List<Franchise>> rosters() {

        return mflService.getRosters(Constants.ROSTER, mYear, mId, Constants.JSON_ENABLED);
    }

    public Observable<List<Player>> players() {

        return mflService.getPlayers(Constants.PLAYERS, mYear, null, Constants.DETAILS_ENABLED, Constants.JSON_ENABLED);
    }

    public Observable<List<Player>> players(List<String> playerIds) {

        return mflService.getPlayers(Constants.PLAYERS, mYear, StringUtils.join(playerIds, ","), Constants.DETAILS_ENABLED, Constants.JSON_ENABLED);
    }
}
