package com.android2ee.formation.juilmmxvi.teleric.service;

import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.IBinder;
import androidx.core.app.NotificationManagerCompat;
import android.support.v7.app.NotificationCompat;
import android.telephony.SmsMessage;
import android.util.Log;

import com.android2ee.formation.juilmmxvi.teleric.R;
import com.android2ee.formation.juilmmxvi.teleric.view.main.MainActivity;

public class MySmsService extends Service {
    private static final String TAG = "MySmsService";
    private static final String SMS_RECEIVE_INTENT_NAME = "android.provider.Telephony.SMS_RECEIVED";
    private PendingIntent pdIntent = null;
    private final int UniqueNotificationId=19112014;
    public MySmsService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Intent activityIntent=new Intent(this, MainActivity.class);
        activityIntent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        pdIntent=PendingIntent.getActivity(this,0,activityIntent,PendingIntent.FLAG_CANCEL_CURRENT);

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e(TAG, "MySmsService onStratCommand called");
        //analyse SMS
        analyseSms(intent);
        stopSelf();
        return Service.START_NOT_STICKY;
    }

    private void analyseSms(Intent intent) {
        if (intent.getAction().equals(SMS_RECEIVE_INTENT_NAME)) {
            //Retrieve the bundle that handles the Messages
            Bundle bundle = intent.getExtras();
            if (bundle != null) {
                //Retrieve the data store in the SMS
                Object[] pdus = (Object[]) bundle.get("pdus");
                //Declare the associated SMS Messages
                SmsMessage[] smsMessages = new SmsMessage[pdus.length];
                //Rebuild your SMS Messages
                for (int i = 0; i < pdus.length; i++) {
                    smsMessages[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
                }
                //Parse your SMS Message
                SmsMessage currentMessage;
                String body, from;
                long time;
                for (int i = 0; i < smsMessages.length; i++) {
                    currentMessage = smsMessages[i];
                    body = currentMessage.getDisplayMessageBody();
                    from = currentMessage.getDisplayOriginatingAddress();
                    time=currentMessage.getTimestampMillis();
                    displayNotif(from,body);
                }
            }
        }
    }



    private void displayNotif(String from, String body) {
         //display the notif
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setAutoCancel(true)
                .setContentIntent(pdIntent)
                .setContentText(body)
                .setContentTitle("New SMS de :" + from)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                .setLights(0x99FF0000, 0, 1000)//don't work
                .setNumber(41108)
                .setOngoing(false)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setSmallIcon(R.drawable.ic_notif)
                .setSubText("SubText")
                .setTicker("You received a new SMS from " + from)
                .setVibrate(new long[]{100, 200, 100, 200, 100}) //need permission
                .setWhen(System.currentTimeMillis());

        NotificationManagerCompat notifManager = NotificationManagerCompat.from(this);
        notifManager.notify(UniqueNotificationId, builder.build());
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onDestroy() {
        Log.e(TAG, "MySmsService onDestroy called");
        super.onDestroy();
    }
}
