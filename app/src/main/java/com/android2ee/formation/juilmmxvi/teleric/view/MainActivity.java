package com.android2ee.formation.juilmmxvi.teleric.view;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.android2ee.formation.juilmmxvi.teleric.R;

import java.util.ArrayList;

public class MainActivity extends MotherActivity {
    private static final String TAG = "MainActivity";
    /***********************************************************
     *  Constant
     **********************************************************/
    public static final String EDT = "EDT";
    public static final String RES = "RES";
    /***********************************************************
     *  Attributes
     **********************************************************/
    /**
     * The editText to write message
     */
    private EditText edtMessage;
    /**
     * The button to add the message in  the result area
     */
    private Button btnAdd;
    /**
     * The Result area
     */
    private ListView lsvResult;
    /**
     * The arrayAdapter == the model of the list view
     */
    private ArrayAdapter<String> arrayAdapter;
    /**
     * The list of messages to display
     */
    private ArrayList<String> messages;

    /***********************************************************
     * Managing LifeCycle
     **********************************************************/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate() called with: " + "savedInstanceState = [" + savedInstanceState + "]");
        super.onCreate(savedInstanceState);
        //instantiate the view
        setContentView(R.layout.activity_main);
        //instantiate graphical components
        edtMessage = (EditText) findViewById(R.id.edtMessage);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        lsvResult = (ListView) findViewById(R.id.lsvResult);
        if(savedInstanceState!=null){
            messages=savedInstanceState.getStringArrayList(RES);
        }else{
            messages=new ArrayList<>();
        }
        arrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,messages);
        lsvResult.setAdapter(arrayAdapter);
        //implement the listeners
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addMessageInResult();
            }
        });
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        Log.d(TAG, "onSaveInstanceState() called with: " + "outState = [" + outState + "]");
        super.onSaveInstanceState(outState);
        outState.putString(EDT,edtMessage.getText().toString());
        outState.putStringArrayList(RES,messages);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        Log.d(TAG, "onRestoreInstanceState() called with: " + "savedInstanceState = [" + savedInstanceState + "]");
        super.onRestoreInstanceState(savedInstanceState);
        edtMessage.setText(savedInstanceState.getString(EDT));
//        ArrayList<String> temp=savedInstanceState.getStringArrayList(RES);
//        for (int i = 0; i < temp.size(); i++) {
//            messages.add(temp.get(i));
//        }
//        arrayAdapter.notifyDataSetChanged();
    }
    /***********************************************************
 *  Business Methods
 **********************************************************/
    /**
     * Add the message from the editText in the Result area
     */
    private void addMessageInResult() {
        String message = edtMessage.getText().toString();
        //first way
//        messages.add(message);
//        arrayAdapter.notifyDataSetChanged();
        //second
        arrayAdapter.add(message);
        edtMessage.setText("");
    }
}
