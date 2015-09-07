package com.squidshoe.mfl.core.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import com.squidshoe.mfl.core.model.League;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by kkovach on 8/24/15.
 */
public class LeagueListAdapter extends TypeAdapter<List<League>> {

    private static final String TAG = "LeagueListAdapter";

    private TypeAdapter<List<League>> mDelegate;
    private Gson mGson;

    public LeagueListAdapter(TypeAdapter<List<League>> delegate) {

        this.mDelegate = delegate;

        mGson = new GsonBuilder().create();
        nullSafe();
    }

    @Override
    public List<League> read(JsonReader reader) throws IOException {

        System.out.println("Reading league list...");

        String name;
        League[] leagueArray;
        List<League> leagueList = new ArrayList<>();

        if(reader.peek() == JsonToken.BEGIN_OBJECT)
        reader.beginObject();

        while (reader.hasNext()) {

            name = reader.nextName();

            if (name.equals("leagues")) {

                reader.beginObject();

                name = reader.nextName();

                if (name.equals("league")) {

                    leagueArray = mGson.fromJson(reader, League[].class);

                    if (leagueArray != null) {

                        leagueList = new ArrayList<>(Arrays.asList(leagueArray));

                    } else {

                        leagueList = new ArrayList<>();
                    }

                } else {

                    reader.skipValue();
                }

                reader.endObject();

            } else {

                reader.skipValue();
            }
        }

        reader.endObject();

        return leagueList;
    }

    @Override
    public void write(JsonWriter out, List<League> value) throws IOException {

        mDelegate.write(out, value);
    }
}
