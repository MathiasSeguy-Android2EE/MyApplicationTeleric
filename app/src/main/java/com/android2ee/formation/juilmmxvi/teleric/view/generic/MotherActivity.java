/**
 * <ul>
 * <li>MotherActivity</li>
 * <li>com.android2ee.formation.juilmmxvi.teleric</li>
 * <li>18/07/2016</li>
 * <p/>
 * <li>======================================================</li>
 * <p/>
 * <li>Projet : Mathias Seguy Project</li>
 * <li>Produit par MSE.</li>
 * <p/>
 * /**
 * <ul>
 * Android Tutorial, An <strong>Android2EE</strong>'s project.</br>
 * Produced by <strong>Dr. Mathias SEGUY</strong>.</br>
 * Delivered by <strong>http://android2ee.com/</strong></br>
 * Belongs to <strong>Mathias Seguy</strong></br>
 * ***************************************************************************************************************</br>
 * This code is free for any usage but can't be distribute.</br>
 * The distribution is reserved to the site <strong>http://android2ee.com</strong>.</br>
 * The intelectual property belongs to <strong>Mathias Seguy</strong>.</br>
 * <em>http://mathias-seguy.developpez.com/</em></br> </br>
 * <p/>
 * *****************************************************************************************************************</br>
 * Ce code est libre de toute utilisation mais n'est pas distribuable.</br>
 * Sa distribution est reservée au site <strong>http://android2ee.com</strong>.</br>
 * Sa propriété intellectuelle appartient à <strong>Mathias Seguy</strong>.</br>
 * <em>http://mathias-seguy.developpez.com/</em></br> </br>
 * *****************************************************************************************************************</br>
 */

package com.android2ee.formation.juilmmxvi.teleric.view.generic;

import android.Manifest;
import android.content.pm.PackageManager;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Mathias Seguy - Android2EE on 18/07/2016.
 */
public class MotherActivity extends AppCompatActivity {
    private static final String TAG = "MotherActivity";

    /***********************************************************
     * Manging SmsReceive runtime permission asking
     **********************************************************/
    private final int MY_PERMISSIONS_REQUEST_READ_CONTACTS=48;
    @Override
    protected void onStart() {
        Log.d(TAG, "onStart() called with: " + "");

        //My Application need to have the receive sms permission
        // Do I have the permission already granted

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.RECEIVE_SMS)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            //Yes we have
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.RECEIVE_SMS)) {

                // Show an expanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

            } else {

                // No explanation needed, we can request the permission.

                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.RECEIVE_SMS},
                        MY_PERMISSIONS_REQUEST_READ_CONTACTS);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        }
        super.onStart();
    }

    @Override
    public boolean shouldShowRequestPermissionRationale(String permission) {
        //depending on the permission do I need to display an Dialog
        return false;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_READ_CONTACTS: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                    //For now, I can receive Sms nothing to do
                    Toast.makeText(this,"Thanks dude, Sms will be displayed as notification",Toast.LENGTH_LONG).show();

                } else {
                    Toast.makeText(this,"We won't receive Sms so, bad ass!",Toast.LENGTH_LONG).show();
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }

    @Override
    protected void onResume() {
        Log.d(TAG, "onResume() called with: " + "");
        super.onResume();
    }

    @Override
    protected void onRestart() {
        Log.d(TAG, "onRestart() called with: " + "");
        super.onRestart();
    }

    @Override
    protected void onPause() {
        Log.d(TAG, "onPause() called with: " + "");
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.d(TAG, "onStop() called with: " + "");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.d(TAG, "onDestroy() called with: " + "");
        super.onDestroy();
    }
}
