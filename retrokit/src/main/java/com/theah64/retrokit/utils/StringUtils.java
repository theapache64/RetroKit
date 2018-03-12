package com.theah64.retrokit.utils;

import java.text.NumberFormat;
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

    public static String toIndianNumber(long totalHits) {
        StringBuilder number = new StringBuilder(String.valueOf(totalHits));
        if (totalHits != -1) {

            final int totalLength = number.length();

            if (totalLength >= 4) {
                //Format it
                switch (totalLength) {

                    case 4:
                        return number.insert(1, ",").toString();

                    case 5:
                        return number.insert(2, ",").toString();


                    case 6:
                        return number.insert(1, ",").insert(4, ",").toString();


                    case 7:
                        return number.insert(2, ",").insert(6, ",").toString();


                    case 8:
                        return number.insert(1, ",").insert(4, ",").insert(7, ",").toString();


                    case 9:
                        return number.insert(2, ",").insert(5, ",").insert(8, ",").toString();

                    default:
                        return NumberFormat.getInstance().format(totalHits);

                }
            }
        }
        return number.toString();
    }

    public static String toIndianNumber(String count) {
        return toIndianNumber(Long.parseLong(count));
    }
}

