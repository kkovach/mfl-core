package com.squidshoe.mfl.core;

import com.squidshoe.mfl.core.model.Franchise;
import com.squidshoe.mfl.core.model.League;
import com.squidshoe.mfl.core.model.LeagueStandings;
import com.squidshoe.mfl.core.model.Player;

import java.util.List;

import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;
import rx.Observable;

/**
 * Created by kkovach on 3/30/15.
 */
public interface MflService {

    @GET("/{year}/export")
    Observable<League> getLeague(@Query("TYPE") String type, @Path("year") Integer year, @Query("L") String leagueId, @Query("JSON") Integer jsonEnabled);

    @GET("/{year}/export")
    Observable<List<League>> leagueSearch(@Query("TYPE") String type, @Path("year") Integer year, @Query("SEARCH") String searchString,
            @Query("JSON") Integer jsonEnabled);

    @GET("/{year}/export")
    Observable<LeagueStandings> getStandings(@Query("TYPE") String type, @Path("year") Integer year, @Query("L") String leagueId,
            @Query("JSON") Integer jsonEnabled);

    @GET("/{year}/export")
    Observable<List<Player>> getRoster(@Query("TYPE") String type, @Path("year") Integer year, @Query("L") String leagueId, @Query("FRANCHISE") String franchiseId,
            @Query("JSON") Integer jsonEnabled);

    @GET("/{year}/export")
    Observable<List<Franchise>> getRosters(@Query("TYPE") String type, @Path("year") Integer year, @Query("L") String leagueId, @Query("JSON") Integer jsonEnabled);

    @GET("/{year}/export")
    Observable<Player> getPlayer(@Query("TYPE") String type, @Path("year") Integer year, @Query("PLAYERS") String playerList,
            @Query("DETAILS") Integer detailsEnabled, @Query("JSON") Integer jsonEnabled);

    @GET("/{year}/export")
    Observable<List<Player>> getPlayers(@Query("TYPE") String type, @Path("year") Integer year, @Query("PLAYERS") String playerList,
            @Query("DETAILS") Integer detailsEnabled, @Query("JSON") Integer jsonEnabled);
}
