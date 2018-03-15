package com.theah64.retrokit.utils;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;

import com.theah64.retrokit.R;

public class Notification {

    private static final String X = Notification.class.getSimpleName();
    private final Context context;
    private int notifId;
    private NotificationManager nm;
    private NotificationCompat.Builder notBuilder;

    public Notification(final Context context) {
        notifId = Random.getRandomInt();
        this.context = context;
        nm = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        this.notBuilder = new NotificationCompat.Builder(context)
                .setSmallIcon(R.drawable.ic_notification_bell)
                .setAutoCancel(true)
                .setDefaults(NotificationCompat.DEFAULT_SOUND);

    }

    public void showNotification(final String title, final String message, final boolean showProgress, @Nullable PendingIntent pendingIntent, @Nullable Bitmap image) {


        if (showProgress) {
            notBuilder.setProgress(100, 0, true);
        } else {
            notBuilder.setProgress(0, 0, false);
        }

        notBuilder
                .setTicker(title)
                .setContentTitle(title)
                .setStyle(new NotificationCompat.BigTextStyle().bigText(message))
                .setContentText(message)
                .setLargeIcon(image != null ? image : BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_notification_bell))
                .setStyle(image != null ? new NotificationCompat.BigPictureStyle(notBuilder).bigPicture(image) : new NotificationCompat.BigTextStyle())
                .setContentIntent(pendingIntent);

        nm.notify(notifId, notBuilder.build());
    }

    NotificationManager getNotificationManager() {
        return nm;
    }

    void dismiss() {
        nm.cancel(notifId);
    }

    private static class Random {
        private static final int MIN = 0;
        private static final int MAX = 1000;
        private static final java.util.Random random = new java.util.Random();

        private static int getRandomInt() {
            return random.nextInt(MAX - MIN + 1) + MIN;
        }
    }
}
