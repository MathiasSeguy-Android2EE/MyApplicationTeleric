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

package com.android2ee.formation.juilmmxvi.teleric.view;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;

/**
 * Created by Mathias Seguy - Android2EE on 18/07/2016.
 */
public class MotherActivity extends AppCompatActivity {
    private static final String TAG = "MotherActivity";

    @Override
    protected void onStart() {
        Log.d(TAG, "onStart() called with: " + "");
        super.onStart();
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
