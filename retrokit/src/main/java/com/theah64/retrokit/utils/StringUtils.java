package com.theah64.retrokit.utils;

import java.util.Random;

/**
 * Created by theapache64 on 16/11/17.
 */

public class StringUtils {

    private static final String randomEngine = "0123456789AaBbCcDdEeFfGgHhIiJjKkLkMmNnOoPpQqRrSsTtUuVvWwXxYyZz";
    private static Random random;


    public static String getRandomString(final int length) {
        if (random == null) {
            random = new Random();
        }
        final StringBuilder apiKeyBuilder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            apiKeyBuilder.append(randomEngine.charAt(random.nextInt(randomEngine.length())));
        }
        return apiKeyBuilder.toString();
    }

    public static String getRandomFilename(final int fileNameLength, final String fileExtension) {
        return getRandomString(fileNameLength) + fileExtension;
    }


    public static String getProper(int count, final String singular) {
        return getProper(count, singular, singular + "s");
    }

    private static String getProper(int count, String singular, String plural) {
        return count > 1 ? plural : singular;
    }

    public static String getProperWithCount(int count, String singular) {
        return count + " " + getProper(count, singular);
    }
}

