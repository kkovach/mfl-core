package com.squidshoe.mfl.core.model;

import com.google.gson.annotations.Expose;

/**
 * Created by kkovach on 3/31/15.
 */
public class RostersResponse {

    @Expose
    public Rosters rosters;
    @Expose
    public String version;
    @Expose
    public String encoding;

}
