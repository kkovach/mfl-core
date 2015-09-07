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

    private String mLeagueJson2 = "{\"version\":\"1.0\",\"league\":{\"currentWaiverType\":\"NONE\",\"playerLimitUnit\":\"LEAGUE\",\"taxiSquad\":\"0\",\"survivorPool\":\"Yes\",\"lastRegularSeasonWeek\":\"17\",\"endWeek\":\"17\",\"maxWaiverRounds\":\"2\",\"lockout\":\"No\",\"nflPoolStartWeek\":\"1\",\"injuredReserve\":\"0\",\"franchises\":{\"count\":\"10\",\"franchise\":[{\"logo\":\"http://www13.myfantasyleague.com/fflnetdynamic2013/13642_franchise_logo0001.gif\",\"icon\":\"http://www13.myfantasyleague.com/fflnetdynamic2013/13642_franchise_icon0001.gif\",\"abbrev\":\"F3D\",\"name\":\"Fantasy Football For Dummies\",\"id\":\"0001\",\"waiverSortOrder\":\"1\",\"stadium\":\"The House That Dummies Built\"},{\"icon\":\"http://www22.myfantasyleague.com/fflnet2014/newhelmets/nh-0055.png\",\"name\":\"MattyIce\",\"id\":\"0002\",\"waiverSortOrder\":\"6\"},{\"logo\":\"http://www22.myfantasyleague.com/fflnet2003/helmets/h-0677.gif\",\"icon\":\"http://www22.myfantasyleague.com/fflnet2003/helmets/h-0677.gif\",\"name\":\"Card Sharks\",\"id\":\"0003\",\"waiverSortOrder\":\"4\"},{\"icon\":\"http://www22.myfantasyleague.com/fflnet2009/helmets/h-0061.gif\",\"name\":\"Sentinels\",\"id\":\"0004\",\"waiverSortOrder\":\"8\"},{\"logo\":\"http://www99.myfantasyleague.com/fflnetdynamic2005/38720_franchise_logo0005.jpg\",\"icon\":\"http://www99.myfantasyleague.com/fflnetdynamic2005/38720_franchise_logo0005.jpg\",\"abbrev\":\"BOYS\",\"name\":\"Beastie Boys\",\"id\":\"0005\",\"waiverSortOrder\":\"3\",\"stadium\":\"Dinky Field\"},{\"logo\":\"http://www99.myfantasyleague.com/fflnetdynamic2004/49436_franchise_logo0006.gif\",\"icon\":\"http://www99.myfantasyleague.com/fflnetdynamic2004/49436_franchise_icon0006.gif\",\"name\":\"Iceman\",\"id\":\"0006\",\"waiverSortOrder\":\"2\"},{\"logo\":\"http://www13.myfantasyleague.com/fflnetdynamic2013/13642_franchise_logo0007.jpg\",\"icon\":\"http://www13.myfantasyleague.com/fflnetdynamic2013/13642_franchise_icon0007.jpg\",\"name\":\"Alte Brewmeisters\",\"id\":\"0007\",\"waiverSortOrder\":\"9\",\"iscommish\":\"1\",\"sound\":\"http://www.wavevents.com/MyFilez/wavs/variety/mnf.wav\"},{\"icon\":\"http://www22.myfantasyleague.com/fflnet2011/newhelmets/nh-0003.png\",\"name\":\"In-Laws\",\"id\":\"0008\",\"waiverSortOrder\":\"7\"},{\"logo\":\"http://www22.myfantasyleague.com/fflnet2013/newhelmets/nh-0244.png\",\"icon\":\"http://www22.myfantasyleague.com/fflnet2013/newhelmets/nh-0244.png\",\"name\":\"Chaos Beasts\",\"id\":\"0009\",\"waiverSortOrder\":\"5\"},{\"logo\":\"http://www13.myfantasyleague.com/fflnetdynamic2011/20963_franchise_logo0010.jpg\",\"icon\":\"http://www22.myfantasyleague.com/fflnetdynamic2014/29080_franchise_icon0010.jpg\",\"name\":\"Outlaws\",\"id\":\"0010\",\"waiverSortOrder\":\"10\"}]},\"standingsSort\":\"PCT,H2H,PTS,\",\"draftPlayerPool\":\"Both\",\"id\":\"29080\",\"startWeek\":\"1\",\"nflPoolType\":\"Confidence\",\"survivorPoolStartWeek\":\"1\",\"survivorPoolEndWeek\":\"17\",\"history\":{\"league\":[{\"url\":\"http://football22.myfantasyleague.com/2014/home/29080\",\"year\":\"2014\"},{\"url\":\"http://football.myfantasyleague.com/fflnet2001/home/7058\",\"year\":\"2001\"},{\"url\":\"http://football.myfantasyleague.com/2002/home/33829\",\"year\":\"2002\"},{\"url\":\"http://football.myfantasyleague.com/2003/home/35944\",\"year\":\"2003\"},{\"url\":\"http://football2.myfantasyleague.com/2004/home/49436\",\"year\":\"2004\"},{\"url\":\"http://football99.myfantasyleague.com/2005/home/38720\",\"year\":\"2005\"},{\"url\":\"http://football99.myfantasyleague.com/2006/home/26334\",\"year\":\"2006\"},{\"url\":\"http://football29.myfantasyleague.com/2007/home/39313\",\"year\":\"2007\"},{\"url\":\"http://football12.myfantasyleague.com/2008/home/20617\",\"year\":\"2008\"},{\"url\":\"http://football25.myfantasyleague.com/2009/home/13808\",\"year\":\"2009\"},{\"url\":\"http://football25.myfantasyleague.com/2010/home/19791\",\"year\":\"2010\"},{\"url\":\"http://football25.myfantasyleague.com/2011/home/20963\",\"year\":\"2011\"},{\"url\":\"http://football25.myfantasyleague.com/2012/home/35500\",\"year\":\"2012\"},{\"url\":\"http://football.myfantasyleague.com/2013/home/13642\",\"year\":\"2013\"}]},\"rosterSize\":\"18\",\"name\":\"Ultimate Football League (GY)\",\"rostersPerPlayer\":\"1\",\"h2h\":\"YES\",\"draftLimitHours\":\"0:02\",\"starters\":{\"count\":\"9\",\"position\":[{\"name\":\"QB\",\"limit\":\"1\"},{\"name\":\"RB\",\"limit\":\"2-3\"},{\"name\":\"WR\",\"limit\":\"2-3\"},{\"name\":\"TE\",\"limit\":\"1\"},{\"name\":\"PK\",\"limit\":\"1\"},{\"name\":\"Def\",\"limit\":\"1\"}],\"idp_starters\":\"\"},\"baseURL\":\"http://football22.myfantasyleague.com\",\"nflPoolEndWeek\":\"17\",\"precision\":\"1\",\"loadRosters\":\"live_draft\"},\"encoding\":\"ISO-8859-1\"}";

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
