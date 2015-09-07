package com.squidshoe.mfl.core.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import com.squidshoe.mfl.core.model.Franchise;
import com.squidshoe.mfl.core.model.LeagueRosters;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by kkovach on 4/2/15.
 */
public class LeagueRostersAdapter extends TypeAdapter<LeagueRosters> {

    private static final String TAG = "LeagueRostersAdapter";

    private TypeAdapter<LeagueRosters> mDelegate;
    private Gson mGson;

    /**
     * <p>
     * Constructor for LeagueRostersAdapter.
     * </p>
     *
     * @param delegate a {@link TypeAdapter} object.
     */
    public LeagueRostersAdapter(TypeAdapter<LeagueRosters> delegate) {

        this.mDelegate = delegate;

        mGson = new GsonBuilder().create();
        nullSafe();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LeagueRosters read(JsonReader reader) throws IOException {

        LeagueRosters leagueRosters = new LeagueRosters();
        String name;
        Franchise[] franchiseArray;

        reader.beginObject();

        while (reader.hasNext()) {

            name = reader.nextName();

            if (name.equals(LeagueRosters.LEAGUE_ROSTERS)) {

                reader.beginObject();

                name = reader.nextName();

                if (name.equals(LeagueRosters.FRANCHISES)) {

                    franchiseArray = mGson.fromJson(reader, Franchise[].class);

                    if (franchiseArray != null) {

                        leagueRosters.franchises = new ArrayList<>(Arrays.asList(franchiseArray));

                    } else {

                        leagueRosters.franchises = new ArrayList<>();
                    }

                } else {

                    reader.skipValue();
                }

                reader.endObject();

            } else if (name.equals(LeagueRosters.VERSION)) {

                leagueRosters.version = reader.nextString();

            } else if (name.equals(LeagueRosters.ENCODING)) {

                leagueRosters.encoding = mGson.fromJson(reader, String.class);

            } else {

                reader.skipValue();
            }
        }

        reader.endObject();

        return leagueRosters;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(JsonWriter out, LeagueRosters value) throws IOException {

        mDelegate.write(out, value);
    }
}
