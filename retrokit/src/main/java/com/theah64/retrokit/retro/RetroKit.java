package com.theah64.retrokit.retro;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.ColorRes;

import com.joanzapata.iconify.IconFontDescriptor;
import com.joanzapata.iconify.Iconify;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.theah64.bugmailer.core.BugMailer;
import com.theah64.bugmailer.core.BugMailerConfig;
import com.theah64.bugmailer.exceptions.BugMailerException;
import com.theah64.retrokit.R;
import com.theah64.retrokit.callbacks.OnErrorTrueCallback;
import com.theah64.retrokit.utils.PreferenceUtils;
import com.wang.avi.Indicator;
import com.wang.avi.indicators.BallPulseSyncIndicator;

import java.text.SimpleDateFormat;
import java.util.Locale;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by theapache64 on 25/8/17.
 */

public class RetroKit {

    public static final int BALL_PULSE_SYNC_INDICATOR = 123;
    private static RetroKit instance;

    public static final String FONT_ROBOTO_REGULAR = "fonts/Roboto-Regular.ttf";
    private static final String FONT_ROBOTO_BOLD = "fonts/Roboto-Bold.ttf";
    public static final String FONT_ROBOTO_MEDIUM = "fonts/Roboto-Medium.ttf";
    public static final String FONT_PACIFICO = "fonts/Pacifico.ttf";

    private static SimpleDateFormat ddMMyyyy = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
    private static SimpleDateFormat defaultDateFormat = ddMMyyyy;
    private final Context context;
    private final Class<?> apiInterface;

    private Class<? extends Indicator> defaultProgressIndicator;
    private boolean versionCheck;
    private boolean logNetwork;

    @ColorRes
    private final int colorPrimary, colorPrimaryDark;
    private String errorMessage;
    private OnErrorTrueCallback errorCallback;
    private int spinnerItem;


    public static RetroKit init(final Context context, Class<?> apiInterface, @ColorRes int colorPrimary, @ColorRes int colorPrimaryDark) {
        if (instance == null) {
            instance = new RetroKit(context, apiInterface, colorPrimary, colorPrimaryDark);
        }
        return instance;
    }

    public static RetroKit getInstance() {
        return instance;
    }

    private RetroKit(final Context context, Class<?> apiInterface, int colorPrimary, int colorPrimaryDark) {
        this.context = context;
        this.apiInterface = apiInterface;
        this.colorPrimary = colorPrimary;
        this.colorPrimaryDark = colorPrimaryDark;
        this.defaultProgressIndicator = BallPulseSyncIndicator.class;
        this.spinnerItem = android.R.layout.simple_spinner_item;

        PreferenceUtils.init(context);
    }

    public RetroKit setCustomSpinnerItemRowLayout(int spinnerItem) {
        this.spinnerItem = spinnerItem;
        return this;
    }

    public static SimpleDateFormat getDefaultDateFormat() {
        return defaultDateFormat;
    }


    public int getColorPrimary() {
        return colorPrimary;
    }

    public int getColorPrimaryDark() {
        return colorPrimaryDark;
    }

    public Class<?> getApiInterface() {
        return apiInterface;
    }

    public RetroKit setRetrofitBaseURL(String baseUrl) {
        RetrofitClient.setBaseUrl(baseUrl);
        return this;
    }

    public RetroKit addIconModule(IconFontDescriptor descriptor) {
        Iconify.with(descriptor);
        return this;
    }

    public RetroKit setDefaultProgressIndicator(Class<? extends Indicator> defaultProgressIndicator) {
        this.defaultProgressIndicator = defaultProgressIndicator;
        return this;
    }


    @SuppressWarnings("TryWithIdenticalCatches")
    public Indicator getDefaultProgressIndicator() {
        try {
            return defaultProgressIndicator.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return new BallPulseSyncIndicator();
    }

    public RetroKit setDefaultFontPathAsRobotoRegular() {
        setDefaultFontPath(FONT_ROBOTO_REGULAR);
        return this;
    }

    public RetroKit setDefaultFontPath(String defaultAppFont) {
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath(defaultAppFont)
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
        return this;
    }


    public boolean isVersionCheck() {
        return versionCheck;
    }

    public RetroKit enableVersionCheck() {
        this.versionCheck = true;
        return this;
    }

    public RetroKit enableNetworkLog() {
        this.logNetwork = true;
        return this;
    }

    public boolean isLogNetwork() {
        return logNetwork;
    }

    public RetroKit enableImageLoader() {
        initImageLoader(context);
        return this;
    }

    private static void initImageLoader(final Context context) {

        ImageLoaderConfiguration.Builder config = new ImageLoaderConfiguration.Builder(context);
        config.threadPriority(Thread.NORM_PRIORITY - 2);
        config.denyCacheImageMultipleSizesInMemory();

        final DisplayImageOptions defaultImageOption = new DisplayImageOptions.Builder()
                .cacheOnDisk(true)
                .cacheInMemory(true)
                .considerExifParams(true)
                .imageScaleType(ImageScaleType.IN_SAMPLE_POWER_OF_2)
                .showImageOnLoading(R.drawable.img_placeholder)
                .showImageOnFail(R.drawable.img_placeholder)
                .showImageForEmptyUri(R.drawable.img_placeholder)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .build();

        config.defaultDisplayImageOptions(defaultImageOption);

        config.diskCacheFileNameGenerator(new Md5FileNameGenerator());
        config.diskCacheSize(100 * 1024 * 1024); // 100 MiB
        config.memoryCacheSize(50 * 1024 * 1024);
        config.tasksProcessingOrder(QueueProcessingType.LIFO);
        //config.writeDebugLogs(); // Remove for release app

        // Initialize ImageLoader with configuration.
        ImageLoader.getInstance().init(config.build());
    }

    public RetroKit enableBugMailer(String... recipients) {
        final BugMailerConfig config = new BugMailerConfig(recipients[0]);
        if (recipients.length > 1) {
            for (final String r : recipients) {
                config.addRecipientEmail(r);
            }
        }
        try {
            BugMailer.init(context, config);
        } catch (BugMailerException e) {
            e.printStackTrace();
        }
        return this;
    }

    public RetroKit setDefaultDateFormat(SimpleDateFormat defaultDateFormat) {
        RetroKit.defaultDateFormat = defaultDateFormat;
        return this;
    }


    public RetroKit setDefaultDateFormat(String format) {
        RetroKit.defaultDateFormat = new SimpleDateFormat(format, Locale.getDefault());
        return this;
    }


    public RetroKit setCustomErrorHandler(String errorMessage, OnErrorTrueCallback errorCallback) {
        this.errorMessage = errorMessage;
        this.errorCallback = errorCallback;
        return this;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public OnErrorTrueCallback getErrorCallback() {
        return errorCallback;
    }

    public int getCustomSpinnerItemRowLayout() {
        return spinnerItem;
    }
}
