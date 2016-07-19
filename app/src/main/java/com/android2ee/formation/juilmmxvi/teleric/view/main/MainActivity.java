package com.android2ee.formation.juilmmxvi.teleric.view.main;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.android2ee.formation.juilmmxvi.teleric.R;
import com.android2ee.formation.juilmmxvi.teleric.model.Human;
import com.android2ee.formation.juilmmxvi.teleric.view.generic.MotherActivity;
import com.android2ee.formation.juilmmxvi.teleric.view.main.adapter.HumanAdapter;

import java.util.ArrayList;

public class MainActivity extends MotherActivity {
    private static final String TAG = "MainActivity";
    /***********************************************************
     * Constant
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
    private HumanAdapter humanAdapter;
    /**
     * The list of messages to display
     */
    private ArrayList<Human> humen;

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
        //to avoid selector to be drawn like a big rectancle
        lsvResult.setSelector(R.drawable.shape_listview_selector);
        if (savedInstanceState != null) {
            humen = savedInstanceState.getParcelableArrayList(RES);
        } else {
            humen = new ArrayList<>();
            for(int i=0;i<10000;i++){
                humen.add(new Human("Item "+i,i));
            }
        }
        humanAdapter = new HumanAdapter(this, humen);
        lsvResult.setAdapter(humanAdapter);
        //implement the listeners
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addMessageInResult();
            }
        });
        lsvResult.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                itemClicked(position);
            }
        });
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        Log.d(TAG, "onSaveInstanceState() called with: " + "outState = [" + outState + "]");
        super.onSaveInstanceState(outState);
        outState.putString(EDT, edtMessage.getText().toString());
        outState.putParcelableArrayList(RES, humen);
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
    String messageTemp;
    Human humanTemp;
    /**
     * Add the message from the editText in the Result area
     */
    private void addMessageInResult() {
        messageTemp = edtMessage.getText().toString();
        //first way
//        messages.add(message);
//        arrayAdapter.notifyDataSetChanged();
        //second
        humanAdapter.insert(new Human(messageTemp, humen.size()),0);
        edtMessage.setText("");
    }

    /**
     * Copy the item selected into the editText
     * @param position
     */
    private void itemClicked(int position){
        humanTemp=humanAdapter.getItem(position);
        edtMessage.setText(humanTemp.getMessage());
    }
}
