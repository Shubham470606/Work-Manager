package com.example.demo.workmanager;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.concurrent.TimeUnit;

import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActivityCompat.requestPermissions(this, new String[]{
                        Manifest.permission.WAKE_LOCK, //11
                        Manifest.permission.RECEIVE_BOOT_COMPLETED,//12
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE
                },
                1);

        if (checkPermissionIsEnabledForRegistraionActivity()) {
            PeriodicWorkRequest.Builder photoWorkBuilder =
                    new PeriodicWorkRequest.Builder(SchedulePullApiWorker.class, 15,
                            TimeUnit.MINUTES);
            PeriodicWorkRequest myWork = photoWorkBuilder
                    .build();
            WorkManager.getInstance().enqueue(myWork);

        }

    }

    public Boolean checkPermissionIsEnabledForRegistraionActivity() {
        Boolean permissionGiven = false;
        Activity activity = this;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.WAKE_LOCK) == PackageManager.PERMISSION_GRANTED
                    && ActivityCompat.checkSelfPermission(activity, Manifest.permission.RECEIVE_BOOT_COMPLETED) == PackageManager.PERMISSION_GRANTED
                    && ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
                    && ActivityCompat.checkSelfPermission(activity, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED

                    ) {
                permissionGiven = true;
            }
        } else {
            permissionGiven = true;
        }

        return permissionGiven;

    }


}
