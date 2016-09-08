package com.teach.myjobscheduler;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

/**
 * Created by tyagp001 on 8/29/16.
 */

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class MyJobService extends JobService {
    private static final String TAG = "MyJobService";

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "Service created");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "Service destroyed");
    }

    @Override
    public boolean onStartJob(JobParameters jobParameters) {
        Log.i(TAG, "onStartJob: starting job- " + jobParameters.getJobId());
        Toast.makeText(getApplicationContext(), "Starting job: " + jobParameters.getJobId(), Toast.LENGTH_LONG).show();
        createNotification();
        return true;
    }

    @Override
    public boolean onStopJob(JobParameters jobParameters) {
        Log.i(TAG, "onStopJob: stopping job- " + jobParameters.getJobId());
        Toast.makeText(getApplicationContext(), "Canceled job: " + jobParameters.getJobId(), Toast.LENGTH_LONG).show();
        return true;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void createNotification() {
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
                        .addAction(R.drawable.bb8, "Open", pendingIntent)
                        .build();

        //Generate notification manager
        NotificationManager notificationManager = (NotificationManager)
                getSystemService(NOTIFICATION_SERVICE);

        //hides notification after its selection
        notification.flags |= Notification.FLAG_AUTO_CANCEL;
        notification.icon = R.drawable.bb8;

        notificationManager.notify(0, notification);
    }
}
