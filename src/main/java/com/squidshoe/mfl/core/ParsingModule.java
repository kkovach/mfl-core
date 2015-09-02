package com.squidshoe.mfl.core;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import com.squidshoe.mfl.core.util.MflTypeAdapterFactory;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit.converter.GsonConverter;

/**
 * Created by kkovach on 4/2/15.
 */
@Module
public class ParsingModule {

    @Provides
    @Singleton
    MflTypeAdapterFactory provideMflTypeAdapterFactory() {

        return new MflTypeAdapterFactory();
    }

    @Provides
    @Singleton
    GsonBuilder provideGsonBuilder(MflTypeAdapterFactory mflTypeAdapterFactory) {

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapterFactory(mflTypeAdapterFactory);
        return gsonBuilder;
    }

    @Provides
    @Singleton
    Gson provideGson(GsonBuilder builder) {

        return builder.create();
    }

    @Provides
    @Singleton
    GsonConverter provideGsonConverter(Gson gson) {

        return new GsonConverter(gson);
    }
}