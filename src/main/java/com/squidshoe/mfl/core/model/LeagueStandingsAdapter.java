package com.squidshoe.mfl.core.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by kkovach on 4/2/15.
 */
public class LeagueStandingsAdapter extends TypeAdapter<LeagueStandings> {

    private static final String TAG = "LeagueStandingsAdapter";

    private TypeAdapter<LeagueStandings> mDelegate;
    private Gson mGson;

    /**
     * <p>
     * Constructor for LeagueStandingsAdapter.
     * </p>
     *
     * @param delegate a {@link com.google.gson.TypeAdapter} object.
     */
    public LeagueStandingsAdapter(TypeAdapter<LeagueStandings> delegate) {

        this.mDelegate = delegate;

        mGson = new GsonBuilder().create();
        nullSafe();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LeagueStandings read(JsonReader reader) throws IOException {

        LeagueStandings leagueStandings = new LeagueStandings();
        String name;
        Franchise[] franchiseArray;

        reader.beginObject();

        while (reader.hasNext()) {

            name = reader.nextName();

            if (name.equals(LeagueStandings.LEAGUE_STANDINGS)) {

                reader.beginObject();

                name = reader.nextName();

                if (name.equals(LeagueStandings.FRANCHISES)) {

                    franchiseArray = mGson.fromJson(reader, Franchise[].class);

                    if (franchiseArray != null) {

                        leagueStandings.franchises = new ArrayList<>(Arrays.asList(franchiseArray));

                    } else {

                        leagueStandings.franchises = new ArrayList<>();
                    }

                } else {

                    reader.skipValue();
                }

                reader.endObject();

            } else if (name.equals(LeagueStandings.VERSION)) {

                leagueStandings.version = reader.nextString();

            } else if (name.equals(LeagueStandings.ENCODING)) {

                leagueStandings.encoding = mGson.fromJson(reader, String.class);

            } else {

                reader.skipValue();
            }
        }

        reader.endObject();

        return leagueStandings;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(JsonWriter out, LeagueStandings value) throws IOException {

        mDelegate.write(out, value);
    }
}
