package com.example.testwidget;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Timer;
import java.util.TimerTask;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.widget.RemoteViews;

public class WidgetDemo extends AppWidgetProvider {

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new UpdateTimer(context, appWidgetManager), 1, 60 * 1000);
        super.onUpdate(context, appWidgetManager, appWidgetIds);
    }

    private class UpdateTimer extends TimerTask {
        AppWidgetManager appWidgetManager;
        RemoteViews remoteViews;
        ComponentName componentName;

        public UpdateTimer(Context context, AppWidgetManager appWidgetManager) {
            this.appWidgetManager = appWidgetManager;
            this.remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget_layout);
            this.componentName = new ComponentName(context, WidgetDemo.class);
        }

        @Override
        public void run() {
            Date date = new Date();
            Calendar calendar = new GregorianCalendar(2015, 9, 14);
            long days = ((calendar.getTimeInMillis() - date.getTime()) / 1000) / (24 * 60 * 60);
            remoteViews.setTextViewText(R.id.widget_content_tv, String.format("距离老婆生日还有%s天", days));
            this.appWidgetManager.updateAppWidget(componentName, remoteViews);
        }

    }
}
