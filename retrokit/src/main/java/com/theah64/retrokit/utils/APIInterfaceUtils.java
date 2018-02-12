package com.theah64.retrokit.utils;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Created by theapache64 on 5/1/18.
 */

public class APIInterfaceUtils {

    public static MultipartBody.Part toImagePart(final String key, File file) {

        if (file == null) {
            return null;
        }

        // create RequestBody instance from file
        RequestBody requestFile =
                RequestBody.create(
                        MediaType.parse("image/*"),
                        file
                );

        // MultipartBody.Part is used to send also the actual file name

        return MultipartBody.Part.createFormData(key, file.getName(), requestFile);
    }
}
