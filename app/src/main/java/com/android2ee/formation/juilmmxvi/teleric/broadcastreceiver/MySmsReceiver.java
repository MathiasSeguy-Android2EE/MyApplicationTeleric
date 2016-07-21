package com.android2ee.formation.juilmmxvi.teleric.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.android2ee.formation.juilmmxvi.teleric.service.MySmsService;

public class MySmsReceiver extends BroadcastReceiver {
    private static final String TAG = "MySmsReceiver";
    public MySmsReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e(TAG,"MySmsReceiver onReceive called");
        Intent startTheSmsServiceIntent= new Intent(context, MySmsService.class);
        startTheSmsServiceIntent.putExtras(intent);
        startTheSmsServiceIntent.setAction(intent.getAction());

        context.startService(startTheSmsServiceIntent);
    }
}
