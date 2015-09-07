package com.squidshoe.mfl.core.util;

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

    private TypeAdapter<History> mDelegate;

    public HistoryAdapter(TypeAdapter<History> delegate) {

        this.mDelegate = delegate;
    }

    @Override
    public void write(JsonWriter out, History value) throws IOException {

        mDelegate.write(out, value);
    }

    @Override
    public History read(JsonReader reader) throws IOException {

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

                    reader.beginObject();
                    curLeagueHistory = new LeagueHistory();

                    while (reader.hasNext()) {

                        name = reader.nextName();

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

                    reader.beginArray();

                    while (reader.hasNext()) {

                        reader.beginObject();
                        curLeagueHistory = new LeagueHistory();

                        while (reader.hasNext()) {

                            name = reader.nextName();

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
                    }

                    reader.endArray();
                }
            }
        }

        reader.endObject();

        return history;
    }
}
