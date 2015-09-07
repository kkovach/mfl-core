package com.squidshoe.mfl.core.util;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import com.squidshoe.mfl.core.model.League;

import java.io.IOException;

/**
 * Created by kkovach on 4/2/15.
 */
public class LeagueAdapter extends TypeAdapter<League> {

    private TypeAdapter<League> mDelegate;

    /**
     * <p>
     * Constructor for LeagueStandingsAdapter.
     * </p>
     *
     * @param delegate a {@link TypeAdapter} object.
     */
    public LeagueAdapter(TypeAdapter<League> delegate) {

        this.mDelegate = delegate;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public League read(JsonReader reader) throws IOException {

        League league = new League();
        String name;

        reader.beginObject();

        while (reader.hasNext()) {

            name = reader.nextName();

            if (name.equals(League.LEAGUE)) {

                league = mDelegate.read(reader);

            } else if (name.equals(League.VERSION)) {

                league.version = reader.nextString();

            } else if (name.equals(League.ENCODING)) {

                league.encoding = reader.nextString();

            } else {

                reader.skipValue();
            }
        }

        reader.endObject();

        return league;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(JsonWriter out, League value) throws IOException {

        mDelegate.write(out, value);
    }
}
