package com.squidshoe.mfl.core.util;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;

import com.squidshoe.mfl.core.model.Franchise;
import com.squidshoe.mfl.core.model.History;
import com.squidshoe.mfl.core.model.League;
import com.squidshoe.mfl.core.model.LeagueStandings;
import com.squidshoe.mfl.core.model.Player;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by kkovach on 4/2/15.
 */
public class MflTypeAdapterFactory implements TypeAdapterFactory {

    private final static Logger LOGGER = Logger.getLogger(MflTypeAdapterFactory.class.getName());

    @Override
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {

        TypeAdapter<T> typeAdapter = null;
        TypeAdapter<T> delegate = gson.getDelegateAdapter(this, type);

        try {

            if (type.getRawType() == LeagueStandings.class) {

                typeAdapter = (TypeAdapter<T>) new LeagueStandingsAdapter((TypeAdapter<LeagueStandings>) delegate);

            } else if (type.getRawType() == League.class) {

                typeAdapter = (TypeAdapter<T>) new LeagueAdapter((TypeAdapter<League>) delegate);

            } else if (type.getRawType() == History.class) {

                typeAdapter = (TypeAdapter<T>) new HistoryAdapter((TypeAdapter<History>) delegate);

            } else if (type.getRawType() == List.class) {

                if (((ParameterizedType) type.getType()).getActualTypeArguments()[0] == League.class) {

                    typeAdapter = (TypeAdapter<T>) new LeagueListAdapter((TypeAdapter<List<League>>) delegate);

                } else if(((ParameterizedType) type.getType()).getActualTypeArguments()[0] == Franchise.class) {

                    typeAdapter = (TypeAdapter<T>) new FranchiseListAdapter((TypeAdapter<List<Franchise>>) delegate);

                } else if(((ParameterizedType) type.getType()).getActualTypeArguments()[0] == Player.class) {

                    LOGGER.log(Level.INFO, "PlayerListAdapter instantiated.");

                    typeAdapter = (TypeAdapter<T>) new PlayerListAdapter((TypeAdapter<List<Player>>) delegate);
                }
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return (TypeAdapter<T>) typeAdapter;
    }
}