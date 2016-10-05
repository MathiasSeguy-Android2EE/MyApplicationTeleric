package com.android2ee.formation.juilmmxvi.teleric.view.main;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.android2ee.formation.juilmmxvi.teleric.R;
import com.android2ee.formation.juilmmxvi.teleric.view.generic.MotherActivity;

public class MainActivity extends MotherActivity {
    private static final String TAG = "MainActivity";


    /***********************************************************
     * Managing LifeCycle
     **********************************************************/
    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate() called with: " + "savedInstanceState = [" + savedInstanceState + "]");
        super.onCreate(savedInstanceState);
        //instantiate the view
        setContentView(R.layout.activity_main);

    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        Log.d(TAG, "onSaveInstanceState() called with: " + "outState = [" + outState + "]");
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        Log.d(TAG, "onRestoreInstanceState() called with: " + "savedInstanceState = [" + savedInstanceState + "]");
        super.onRestoreInstanceState(savedInstanceState);

    }

    /***********************************************************
     *  Managing the AlertDialog
     **********************************************************/
    //first define an id for your alert dialog
    private final int AlertDialogUniqueID=110274;

    @Override
    protected Dialog onCreateDialog(int id) {
        Dialog dialog=null;
        switch(id){
            case AlertDialogUniqueID:
                dialog=buildDialog();
                break;
        }
        return dialog;
    }

    /**
     * Create the AlertDialog
     */
    private Dialog buildDialog(){

        return null;
    }

    /***********************************************************
     *  Managing menu
     **********************************************************/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return super.onOptionsItemSelected(item);
    }


}
