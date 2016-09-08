package com.teach.notification;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.teach.myjobscheduler.MainActivity;
import com.teach.myjobscheduler.R;

public class NotificationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT_WATCH)
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                createNotification(view);
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT_WATCH)
    public void createNotification(View view) {
        //Activity to be opened from notification
        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,
                (int)System.currentTimeMillis(), intent, 0);

        //Build notification
        Notification notification =
                new Notification.Builder(this)
                .setContentTitle("JobSchedulerExample")
                .setContentText("Check out JobScheduler example")
                .setContentIntent(pendingIntent)
                .addAction(new Notification.Action(R.drawable.avd, "Open", pendingIntent))
                .build();

        //Generate notification manager
        NotificationManager notificationManager = (NotificationManager)
                getSystemService(NOTIFICATION_SERVICE);

        //hides notification after its selection
        notification.flags |= Notification.FLAG_AUTO_CANCEL;

        notificationManager.notify(0, notification);
    }

}
