package com.squidshoe.mfl.core.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import com.squidshoe.mfl.core.model.Player;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by kkovach on 8/24/15.
 */
public class PlayerListAdapter extends TypeAdapter<List<Player>> {

    private final static Logger LOGGER = Logger.getLogger(PlayerListAdapter.class.getName());

    private static final String TAG = "LeagueListAdapter";

    private TypeAdapter<List<Player>> mDelegate;
    private Gson mGson;

    public PlayerListAdapter(TypeAdapter<List<Player>> delegate) {

        this.mDelegate = delegate;

        mGson = new GsonBuilder().create();
        nullSafe();
    }

    @Override
    public List<Player> read(JsonReader reader) throws IOException {

        LOGGER.log(Level.INFO, "Parsing player list...");

        String name;
        Player[] playerArray;
        List<Player> playerList = new ArrayList<>();

        reader.beginObject();

        while (reader.hasNext()) {

            name = reader.nextName();
            LOGGER.log(Level.INFO, "Name is " + name);

            if (name.equals("players")) {

                reader.beginObject();

                LOGGER.log(Level.INFO, "Next token is " + reader.peek().name());

                while (reader.hasNext()) {

                    name = reader.nextName();

                    if (name.equals("player")) {

                        playerArray = mGson.fromJson(reader, Player[].class);

                        if (playerArray != null) {

                            playerList = new ArrayList<>(Arrays.asList(playerArray));

                        } else {

                            playerList = new ArrayList<>();
                        }

                    } else {

                        LOGGER.log(Level.INFO, "Skipping " + reader.peek().toString());

                        reader.skipValue();
                    }
                }

                reader.endObject();

            } else if(name.equals("rosters")) {

                reader.beginObject();

                LOGGER.log(Level.INFO, "Next token is " + reader.peek().name());

                while (reader.hasNext()) {

                    name = reader.nextName();

                    if (name.equals("franchise")) {

                        reader.beginObject();

                        LOGGER.log(Level.INFO, "Next token is " + reader.peek().name());

                        while (reader.hasNext()) {

                            name = reader.nextName();

                            if (name.equals("player")) {

                                playerArray = mGson.fromJson(reader, Player[].class);

                                if (playerArray != null) {

                                    playerList = new ArrayList<>(Arrays.asList(playerArray));

                                } else {

                                    playerList = new ArrayList<>();
                                }

                            } else {

                                reader.skipValue();
                            }
                        }

                        reader.endObject();
                    }
                }

                reader.endObject();

            } else {

                LOGGER.log(Level.INFO, "Skipping " + reader.peek().toString());
                reader.skipValue();
            }
        }

        reader.endObject();

        return playerList;
    }

    @Override
    public void write(JsonWriter out, List<Player> value) throws IOException {

        mDelegate.write(out, value);
    }
}
