package com.squidshoe.mfl.core.util;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;

import com.squidshoe.mfl.core.model.League;
import com.squidshoe.mfl.core.model.LeagueAdapter;
import com.squidshoe.mfl.core.model.LeagueRosters;
import com.squidshoe.mfl.core.model.LeagueRostersAdapter;
import com.squidshoe.mfl.core.model.LeagueStandings;
import com.squidshoe.mfl.core.model.LeagueStandingsAdapter;

import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * Created by kkovach on 4/2/15.
 */
public class MflTypeAdapterFactory implements TypeAdapterFactory {

    @Override
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {

        TypeAdapter<T> typeAdapter = null;
        TypeAdapter<T> delegate = gson.getDelegateAdapter(this, type);

        try {

            if (type.getRawType() == LeagueStandings.class) {

                typeAdapter = (TypeAdapter<T>) new LeagueStandingsAdapter((TypeAdapter<LeagueStandings>) delegate);

            } else if (type.getRawType() == League.class) {

                typeAdapter = (TypeAdapter<T>) new LeagueAdapter((TypeAdapter<League>) delegate);

            } else if (type.getRawType() == List.class) {

                if (((ParameterizedType) type.getType()).getActualTypeArguments()[0] == League.class) {
                    typeAdapter = (TypeAdapter<T>) new LeagueSearchAdapter((TypeAdapter<List<League>>) delegate);
                }

            } else if (type.getRawType() == LeagueRosters.class) {

                typeAdapter = (TypeAdapter<T>) new LeagueRostersAdapter((TypeAdapter<LeagueRosters>) delegate);
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return (TypeAdapter<T>) typeAdapter;
    }
}