package com.squidshoe.mfl.core.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import com.squidshoe.mfl.core.model.History;
import com.squidshoe.mfl.core.model.LeagueHistory;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by kkovach on 9/6/15.
 */
public class HistoryAdapter extends TypeAdapter<History> {

    private static final String TAG = "LeagueAdapter";

    private TypeAdapter<History> mDelegate;
    private Gson mGson;

    public HistoryAdapter(TypeAdapter<History> delegate) {

        this.mDelegate = delegate;

        mGson = new GsonBuilder().create();
        nullSafe();
    }

    @Override
    public void write(JsonWriter out, History value) throws IOException {

        mDelegate.write(out, value);
    }

    @Override
    public History read(JsonReader reader) throws IOException {

        System.out.println("Parsing HISTORY!!!");

        History history = new History();
        history.league = new ArrayList<>();
        LeagueHistory curLeagueHistory;
        String name;

        reader.beginObject();

        while (reader.hasNext()) {

            name = reader.nextName();

            if (name.equals(History.LEAGUE)) {

                JsonToken firstToken = reader.peek();

                if (firstToken == JsonToken.BEGIN_OBJECT) {

                    System.out.println("Begin object!");

                    reader.beginObject();
                    curLeagueHistory = new LeagueHistory();

                    while (reader.hasNext()) {

                        name = reader.nextName();
                        System.out.println("Name is " + name);

                        if (name.equals(LeagueHistory.URL)) {

                            curLeagueHistory.url = reader.nextString();

                        } else if (name.equals(LeagueHistory.YEAR)) {

                            curLeagueHistory.year = reader.nextString();

                        } else {

                            reader.skipValue();
                        }
                    }

                    history.league.add(curLeagueHistory);
                    reader.endObject();

                } else if (firstToken == JsonToken.BEGIN_ARRAY) {

                    System.out.println("It's an array!!");

                    System.out.println("Begining array");
                    reader.beginArray();

                    while (reader.hasNext()) {

                        System.out.println("Begining object");
                        reader.beginObject();
                        curLeagueHistory = new LeagueHistory();

                        while (reader.hasNext()) {

                            name = reader.nextName();
                            System.out.println("Name is " + name);

                            if (name.equals(LeagueHistory.URL)) {

                                curLeagueHistory.url = reader.nextString();

                            } else if (name.equals(LeagueHistory.YEAR)) {

                                curLeagueHistory.year = reader.nextString();

                            } else {

                                reader.skipValue();
                            }
                        }

                        System.out.println("Adding 1 to list.");
                        history.league.add(curLeagueHistory);
                        reader.endObject();
                    }

                    reader.endArray();
                }
            }
        }

        reader.endObject();

        return history;
    }
}
