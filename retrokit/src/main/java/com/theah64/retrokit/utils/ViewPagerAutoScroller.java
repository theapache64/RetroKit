package com.theah64.retrokit.utils;

import android.os.Handler;
import android.support.v4.view.ViewPager;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by theapache64 on 23/5/17.
 */

public class ViewPagerAutoScroller {

    private final int totalBanners;
    private final ViewPager viewPager;
    private final long interval;
    private int exCurPage = -1;
    private boolean isScrolled;
    private boolean isHolding = false;

    public ViewPagerAutoScroller(int totalBanners, ViewPager viewPager, long interval) {
        this.totalBanners = totalBanners;
        this.viewPager = viewPager;
        this.interval = interval;
    }

    public void start() {

        if (isScrolled) {
            throw new IllegalArgumentException("Already scrolled!");
        }

        isScrolled = true;

        //Setting auto scroll
        final Handler handler = new Handler();
        final Runnable updateAd = new Runnable() {
            public void run() {

                if (!isHolding) {
                    if (exCurPage == (totalBanners)) {
                        exCurPage = 0;
                    }

                    viewPager.setCurrentItem(exCurPage++, true);
                }
            }
        };

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                exCurPage = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        final Timer timer = new Timer(); // This will create a new Thread
        timer.schedule(new TimerTask() { // task to be scheduled

            @Override
            public void run() {
                handler.post(updateAd);
            }
        }, 0, interval);
    }
}
