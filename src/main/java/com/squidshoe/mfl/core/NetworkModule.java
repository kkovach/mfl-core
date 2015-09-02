package com.squidshoe.mfl.core;

import com.squareup.okhttp.Cache;
import com.squareup.okhttp.OkHttpClient;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit.RestAdapter;
import retrofit.RestAdapter.Builder;
import retrofit.client.OkClient;
import retrofit.converter.GsonConverter;

/**
 * Created by kkovach on 3/29/15.
 */
@Module(includes = ParsingModule.class)
public class NetworkModule {

    public static final String CONTENT_API_BASE = "http://football.myfantasyleague.com/";

    private RestAdapter.LogLevel mLogLevel;
    private String mEndPoint;
    private Cache mCache;

    public NetworkModule() {

        mLogLevel = RestAdapter.LogLevel.NONE;
        mEndPoint = CONTENT_API_BASE;
        mCache = null;
    }

    public NetworkModule(Cache cache) {

        mLogLevel = RestAdapter.LogLevel.NONE;
        mEndPoint = CONTENT_API_BASE;
        mCache = cache;
    }

    public NetworkModule(RestAdapter.LogLevel ll) {

        mLogLevel = ll;
        mEndPoint = CONTENT_API_BASE;
        mCache = null;
    }

    public NetworkModule(RestAdapter.LogLevel ll, Cache cache) {

        mLogLevel = ll;
        mEndPoint = CONTENT_API_BASE;
        mCache = cache;
    }

    @Provides
    @Singleton
    Executor provideExecutor() {

        Executor executor = Executors.newCachedThreadPool();
        return executor;
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient() {

        OkHttpClient okHttpClient = new OkHttpClient();

        if (mCache != null) {
            okHttpClient.setCache(mCache);
        }

        return okHttpClient;
    }

    @Provides
    @Singleton
    OkClient provideOkClient(OkHttpClient okHttpClient) {

        return new OkClient(okHttpClient);
    }

    @Provides
    @Singleton
    Builder provideBuilder(OkClient okClient, GsonConverter converter, Executor executor) {

        Builder builder = new Builder();
        builder.setLogLevel(mLogLevel).setClient(okClient).setConverter(converter).setEndpoint(mEndPoint).setExecutors(executor, executor);
        return builder;
    }

    @Provides
    @Singleton
    RestAdapter provideRestAdapter(Builder builder) {

        return builder.build();
    }

    @Provides
    @Singleton
    MflService provideMflService(RestAdapter restAdapter) {

        return restAdapter.create(MflService.class);
    }
}