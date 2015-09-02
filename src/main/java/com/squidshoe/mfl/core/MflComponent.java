package com.squidshoe.mfl.core;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by kkovach on 4/1/15.
 */

@Singleton
@Component(modules = NetworkModule.class)
public interface MflComponent {

//    MflService mflService();
    void inject(LeagueManager leageManager);
}