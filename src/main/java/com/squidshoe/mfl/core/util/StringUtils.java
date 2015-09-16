package com.squidshoe.mfl.core.util;

import java.util.List;

/**
 * Created by kkovach on 9/7/15.
 */
public class StringUtils {

    public static String join(List<String> stringList, String delim) {

        StringBuilder stringBuilder = new StringBuilder();

        String delimiter = "";

        for (String string : stringList) {

            stringBuilder.append(delimiter);
            stringBuilder.append(string);

            delimiter = delim;
        }

        return stringBuilder.toString();
    }
}
