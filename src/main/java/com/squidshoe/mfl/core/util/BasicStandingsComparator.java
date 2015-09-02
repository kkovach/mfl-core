package com.squidshoe.mfl.core.util;

import com.squidshoe.mfl.core.model.Franchise;

import java.util.Comparator;

/**
 * Created by kkovach on 4/2/15.
 */
public class BasicStandingsComparator implements Comparator<Franchise> {

    @Override
    public int compare(Franchise franchise1, Franchise franchise2) {

        return getPercentage(franchise1).compareTo(getPercentage(franchise2));
    }

    private Float getPercentage(Franchise franchise) {

        return Float.valueOf(franchise.h2hw) / (Float.valueOf(franchise.h2hw) + Float.valueOf(franchise.h2hl) + Float.valueOf(franchise.h2ht));
    }
}