package com.squidshoe.mfl.core.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import com.squidshoe.mfl.core.model.League;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by kkovach on 9/6/15.
 */
public class LeagueAdapterTest {

    private String mLeagueJson =
            "{\"version\":\"1.0\",\"league\":{\"currentWaiverType\":\"NONE\",\"playerLimitUnit\":\"LEAGUE\",\"taxiSquad\":\"0\",\"survivorPool\":\"Yes\",\"lastRegularSeasonWeek\":\"1\",\"endWeek\":\"17\",\"lockout\":\"No\",\"nflPoolStartWeek\":\"1\",\"injuredReserve\":\"2\",\"franchises\":{\"count\":\"14\",\"franchise\":[{\"division\":\"00\",\"name\":\"America's Cowboys\",\"id\":\"0001\",\"waiverSortOrder\":\"14\"},{\"division\":\"00\",\"name\":\"Show Me Tds\",\"id\":\"0002\",\"waiverSortOrder\":\"13\"},{\"division\":\"00\",\"name\":\"Bear Claws\",\"id\":\"0003\",\"waiverSortOrder\":\"12\"},{\"division\":\"01\",\"name\":\"Darcy's Demonic Destroyers\",\"id\":\"0004\",\"waiverSortOrder\":\"11\"},{\"division\":\"01\",\"name\":\"Booners Bombers\",\"id\":\"0005\",\"waiverSortOrder\":\"10\"},{\"division\":\"01\",\"name\":\"Harley's Hotheads\",\"id\":\"0006\",\"waiverSortOrder\":\"9\"},{\"division\":\"02\",\"name\":\"Brees Hotties\",\"id\":\"0007\",\"waiverSortOrder\":\"8\"},{\"division\":\"02\",\"name\":\"Ducky's Bratts\",\"id\":\"0008\",\"waiverSortOrder\":\"7\"},{\"division\":\"02\",\"name\":\"Brain Busters\",\"id\":\"0009\",\"waiverSortOrder\":\"6\"},{\"division\":\"03\",\"name\":\"Indiana Hoosiers\",\"id\":\"0010\",\"waiverSortOrder\":\"5\"},{\"division\":\"03\",\"name\":\"Freak of Nature\",\"id\":\"0011\",\"waiverSortOrder\":\"4\"},{\"division\":\"03\",\"name\":\"Aprils Avengers\",\"id\":\"0012\",\"waiverSortOrder\":\"3\"},{\"division\":\"03\",\"name\":\"Junkyard Dogs\",\"id\":\"0013\",\"waiverSortOrder\":\"2\"},{\"division\":\"03\",\"name\":\"Sports Mommie\",\"id\":\"0014\",\"waiverSortOrder\":\"1\"}]},\"standingsSort\":\"PCT,PTS\",\"id\":\"63554\",\"startWeek\":\"1\",\"nflPoolType\":\"Confidence\",\"survivorPoolStartWeek\":\"1\",\"survivorPoolEndWeek\":\"17\",\"history\":{\"league\":{\"url\":\"http://football27.myfantasyleague.com/2015/home/63554\",\"year\":\"2015\"}},\"rosterSize\":\"16\",\"name\":\"Ultimate Fantasy Football League\",\"rostersPerPlayer\":\"1\",\"h2h\":\"YES\",\"divisions\":{\"count\":\"4\",\"division\":[{\"name\":\"Division 1\",\"id\":\"00\"},{\"name\":\"Division 2\",\"id\":\"01\"},{\"name\":\"Division 3\",\"id\":\"02\"},{\"name\":\"Division 4\",\"id\":\"03\"}]},\"starters\":{\"count\":\"8\",\"position\":[{\"name\":\"QB\",\"limit\":\"1\"},{\"name\":\"RB\",\"limit\":\"2\"},{\"name\":\"WR\",\"limit\":\"2\"},{\"name\":\"TE\",\"limit\":\"1\"},{\"name\":\"PK\",\"limit\":\"1\"},{\"name\":\"Def\",\"limit\":\"1\"}],\"idp_starters\":\"\"},\"baseURL\":\"http://football27.myfantasyleague.com\",\"nflPoolEndWeek\":\"17\",\"precision\":\"0\",\"loadRosters\":\"none\"},\"encoding\":\"ISO-8859-1\"}";
    
    private Gson mGson;

    @Before
    public void setUp() throws Exception {

        MflTypeAdapterFactory mflTypeAdapterFactory = new MflTypeAdapterFactory();
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapterFactory(mflTypeAdapterFactory);
        mGson = gsonBuilder.create();
    }

    @Test
    public void parseLeague() {

        League league = mGson.fromJson(mLeagueJson, League.class);

        assertThat(league.history.league.get(0).year, is("2015"));
    }
}
