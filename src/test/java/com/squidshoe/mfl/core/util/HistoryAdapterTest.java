package com.squidshoe.mfl.core.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import com.squidshoe.mfl.core.model.History;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

/**
 * Created by kkovach on 9/6/15.
 */
public class HistoryAdapterTest {

    private Gson mGson;

    @Before
    public void setUp() throws Exception {

        MflTypeAdapterFactory mflTypeAdapterFactory = new MflTypeAdapterFactory();
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapterFactory(mflTypeAdapterFactory);
        mGson = gsonBuilder.create();
    }

    @Test
    public void singleHistory() {

        String singleHistoryJson = "{\"league\":{\"url\":\"http://football27.myfantasyleague.com/2015/home/63554\",\"year\":\"2015\"}}";
        assertNotNull(mGson.fromJson(singleHistoryJson, History.class));
    }

    @Test
    public void multipleHistory() {

        String multipleHistoryJson
                = "{\"league\":[{\"url\":\"http://football16.myfantasyleague.com/2012/home/35165\",\"year\":\"2012\"},{\"url\":\"http://football5.myfantasyleague.com/2008/home/77096\",\"year\":\"2008\"},{\"url\":\"http://football99.myfantasyleague.com/2009/home/14443\",\"year\":\"2009\"},{\"url\":\"http://football99.myfantasyleague.com/2010/home/20182\",\"year\":\"2010\"},{\"url\":\"http://football99.myfantasyleague.com/2011/home/21793\",\"year\":\"2011\"},{\"url\":\"http://football.myfantasyleague.com/2013/home/23308\",\"year\":\"2013\"}]}";

        History history = mGson.fromJson(multipleHistoryJson, History.class);
        assertThat(history.league.size(), is(6));
    }
}
