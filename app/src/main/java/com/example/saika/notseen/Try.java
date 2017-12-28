package com.example.saika.notseen;

import android.app.Notification;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.util.Log;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by saika on 08-12-2017.
 */

public class Try extends NotificationListenerService {

    private SQLiteDatabase db;
    private Cursor resultSet;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("saikat","Oncreate called");
    }

    @Override
    public void onNotificationPosted(StatusBarNotification sbn) {
        super.onNotificationPosted(sbn);
        Calendar c = Calendar.getInstance();
        SimpleDateFormat dateformat = new SimpleDateFormat("hh:mm:ss");
        String datetime = dateformat.format(c.getTime());
        Notification notification=sbn.getNotification();
        String package_name=sbn.getPackageName();
        String title=notification.extras.get(notification.EXTRA_TITLE).toString();
        String body=notification.extras.get(notification.EXTRA_TEXT).toString();
        Log.i("saikat","New Notification from "+sbn.getPackageName());
        Log.i("saikat","time: "+datetime);
        Log.i("saikat","Title : "+title);
        Log.i("saikat","body : "+body);
        if(package_name.contains("whatsapp")||package_name.contains("facebook")) {
            String query = "insert into notifications values('" + package_name + "','" + title + "','" + body + "','"+datetime+"');";
            try {
                db = openOrCreateDatabase("mydb", MODE_PRIVATE, null);
                db.execSQL("create table if not exists notifications(package_name varchar(50),title varchar(250),body varchar(250),time varchar(30));");
                db.execSQL(query);
            } catch (Exception e) {
                Log.i("saikat", e.toString());
            }
        }
    }
}
