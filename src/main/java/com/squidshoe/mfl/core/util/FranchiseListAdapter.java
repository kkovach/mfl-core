package com.squidshoe.mfl.core.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import com.squidshoe.mfl.core.model.Franchise;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by kkovach on 8/24/15.
 */
public class FranchiseListAdapter extends TypeAdapter<List<Franchise>> {

    private final static Logger LOGGER = Logger.getLogger(FranchiseListAdapter.class.getName());

    private static final String TAG = "LeagueListAdapter";

    private TypeAdapter<List<Franchise>> mDelegate;
    private Gson mGson;

    public FranchiseListAdapter(TypeAdapter<List<Franchise>> delegate) {

        this.mDelegate = delegate;

        mGson = new GsonBuilder().create();
        nullSafe();
    }

    @Override
    public List<Franchise> read(JsonReader reader) throws IOException {

        String name;
        Franchise[] franchiseArray;
        List<Franchise> franchiseList = new ArrayList<>();

        if (reader.peek() == JsonToken.BEGIN_OBJECT) {

            reader.beginObject();

            while (reader.hasNext()) {

                name = reader.nextName();

                if (name.equals("rosters")) {

                    reader.beginObject();

                    name = reader.nextName();

                    if (name.equals("franchise")) {

                        franchiseArray = mGson.fromJson(reader, Franchise[].class);

                        if (franchiseArray != null) {

                            franchiseList = new ArrayList<>(Arrays.asList(franchiseArray));

                        } else {

                            franchiseList = new ArrayList<>();
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

        } else if (reader.peek() == JsonToken.BEGIN_ARRAY) {

            franchiseArray = mGson.fromJson(reader, Franchise[].class);

            if (franchiseArray != null) {

                franchiseList = new ArrayList<>(Arrays.asList(franchiseArray));

            } else {

                franchiseList = new ArrayList<>();
            }

        } else if (reader.peek() == JsonToken.NAME) {

            name = reader.nextName();
            LOGGER.log(Level.INFO, "First token is name '" + name + "'");
        }

        return franchiseList;
    }

    @Override
    public void write(JsonWriter out, List<Franchise> value) throws IOException {

        mDelegate.write(out, value);
    }
}
