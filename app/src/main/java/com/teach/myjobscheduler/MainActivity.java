package com.teach.myjobscheduler;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.teach.myjobscheduler.R;

public class MainActivity extends AppCompatActivity {
    MyJobService jobService;
    private static int jobId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void enableNotifications(View v){
        ComponentName serviceComponentName =
                new ComponentName(this, MyJobService.class);

        JobInfo.Builder builder =
                new JobInfo.Builder(jobId++, serviceComponentName);
        //wait for 10 seconds
        //builder.setMinimumLatency(10 * 1000);

        //maximum delay at 1 minute
        //builder.setOverrideDeadline(60 * 1000);

        builder.setPeriodic(10);//Put here 1 day in ms
        //Agnostic of charging status of device
        builder.setRequiresCharging(false);

        JobScheduler jobScheduler = (JobScheduler) getApplication().
                getSystemService(Context.JOB_SCHEDULER_SERVICE);
        jobScheduler.schedule(builder.build());
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void disableNotifications(View v){
        JobScheduler jobScheduler = (JobScheduler) getApplication().
                getSystemService(Context.JOB_SCHEDULER_SERVICE);
        jobScheduler.cancelAll();
    }

//    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
//    public void createNotification(View view) {
//        //Activity to be opened from notification
//        Intent intent = new Intent(this, MainActivity.class);
//        PendingIntent pendingIntent = PendingIntent.getActivity(this,
//                (int)System.currentTimeMillis(), intent, 0);
//
//        //Build notification
//        Notification notification =
//                new Notification.Builder(this)
//                        .setContentTitle("JobSchedulerExample")
//                        .setContentText("Check out JobScheduler example")
//                        .setContentIntent(pendingIntent)
//                        .addAction(R.drawable.bb8, "Open", pendingIntent)
//                        .build();
//
//        //Generate notification manager
//        NotificationManager notificationManager = (NotificationManager)
//                getSystemService(NOTIFICATION_SERVICE);
//
//        //hides notification after its selection
//        notification.flags |= Notification.FLAG_AUTO_CANCEL;
//        notification.icon = R.drawable.bb8;
//
//        notificationManager.notify(0, notification);
//    }

}
