package com.example.demo.workmanager;

import android.content.Context;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.util.Log;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import androidx.work.Worker;
import androidx.work.WorkerParameters;

public class SchedulePullApiWorker extends Worker {

    public SchedulePullApiWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {

        Log.i("SHUBHAM :: ", "SHUBHAM ::  ");
        writeDataTofile();
        return Result.success();
    }


    private void writeDataTofile() {
        try {
            File sd = Environment.getExternalStorageDirectory();

            if (sd.canWrite()) {
                String backupDBPath = "shubham_worker.txt";
                File backupDB = new File(sd, backupDBPath);

                if (!backupDB.exists()) {
                    backupDB.createNewFile();
                }
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);
                Date sdate = new Date();

                OutputStreamWriter file_writer = new OutputStreamWriter(new FileOutputStream(backupDB, true));
                BufferedWriter buffered_writer = new BufferedWriter(file_writer);
                buffered_writer.write(formatter.format(sdate) + "\n");
                buffered_writer.close();
            }

        } catch (Exception e) {

        }
    }


}