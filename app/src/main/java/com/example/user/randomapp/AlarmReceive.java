package com.example.user.randomapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by user on 9/10/17.
 */

class AlarmReceive extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e("Service_call_"  , "You are in AlarmReceive class.");
        Intent background = new Intent(context, LocationService.class);
        Log.e("AlarmReceive ","testing called broadcast called");
        context.startService(background);
    }
}