package vn.edu.lop1k.modulemanagejobservice;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import vn.edu.lop1k.model.Job;

public class Receiver extends BroadcastReceiver {


    public Receiver() {
    }
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e("toi trong receiver","Xin chao");
        Intent myIntent=new Intent(context,Service.class);
        context.startService(myIntent);


    }





}
