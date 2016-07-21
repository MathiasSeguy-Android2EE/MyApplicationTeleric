package com.android2ee.formation.juilmmxvi.teleric.view.main;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android2ee.formation.juilmmxvi.teleric.R;
import com.android2ee.formation.juilmmxvi.teleric.model.Human;
import com.android2ee.formation.juilmmxvi.teleric.view.generic.MotherActivity;
import com.android2ee.formation.juilmmxvi.teleric.view.main.adapter.HumanRecyclerAdapter;
import com.android2ee.formation.juilmmxvi.teleric.view.main.adapter.OnItemClickedRecyclerView;

import java.util.ArrayList;

public class MainActivity extends MotherActivity implements OnItemClickedRecyclerView {
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
    private RecyclerView rcvResult;
    /**
     * The arrayAdapter == the model of the list view
     */
    private HumanRecyclerAdapter humanRecyclerAdapter;
    /**
     * The list of messages to display
     */
    private ArrayList<Human> humen;
    /**
     * HoneyComb or not  ?o?
     */
    private boolean isHoneyComb;

    /**
     * Animator Set for animation postHC
     */
    AnimatorSet animatorSet;
    /**
     * Animator Set for animation preHC
     */
    Animation animation;

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
        //instantiate graphical components
        edtMessage = (EditText) findViewById(R.id.edtMessage);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        rcvResult = (RecyclerView) findViewById(R.id.rcvResult);
        isHoneyComb=getResources().getBoolean(R.bool.isHC);
        //instanciate animation
        if(isHoneyComb){
            animatorSet= (AnimatorSet) AnimatorInflater.loadAnimator(this,R.animator.btnadd_pushed_anim);
            animatorSet.setTarget(btnAdd);
        }else{
            animation= AnimationUtils.loadAnimation(this,R.anim.btn_add_pushed);
        }
        //to avoid selector to be drawn like a big rectancle
        if (savedInstanceState != null) {
            humen = savedInstanceState.getParcelableArrayList(RES);
        } else {
            humen = new ArrayList<>();
            for(int i=0;i<2;i++){
                humen.add(new Human("Item "+i,i));
            }
        }
        humanRecyclerAdapter = new HumanRecyclerAdapter(humen, this) {
            /**
             * The item is clicked please do something
             *
             * @param position
             */
            @Override
            public void itemClicked(int position) {
                MainActivity.this.itemClicked(position);
            }
        };
        rcvResult.setLayoutManager(new LinearLayoutManager(this));
        rcvResult.setAdapter(humanRecyclerAdapter);
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
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setMessage(getString(R.string.alertdialog_message,messageTemp));
        builder.setPositiveButton(getString(R.string.alertdialog_ok),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        copyMessageInEdt();
                    }
                });
        builder.setNegativeButton(getString(R.string.alertdialog_nok),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        abordCopy();
                    }
                });
        return builder.create();
    }

    /***********************************************************
     *  Managing menu
     **********************************************************/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //create the menu
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.menu_deleteall:
                deleteAll();
                return true;
            case R.id.menu_addall:
                addAll();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /***********************************************************
     *  Business Methods
     **********************************************************/
    String messageTemp;
    Human humanTemp;
    /**
     * Add the message from the editText in the Result area
     */
    @SuppressLint("NewApi")
    private void addMessageInResult() {
        if(isHoneyComb){
            animatorSet.start();
        }else{
            btnAdd.startAnimation(animation);
        }
        messageTemp = edtMessage.getText().toString();
        //first way
//        messages.add(message);
//        arrayAdapter.notifyDataSetChanged();
        //second
        humanRecyclerAdapter.add(0,new Human(messageTemp, humen.size()));
        edtMessage.setText("");
    }

    /**
     * Copy the item selected into the editText
     * @param position
     */
    public void itemClicked(int position){
        messageTemp=humanRecyclerAdapter.getItem(position).getMessage();
        showDialog(AlertDialogUniqueID);
    }

    /**
     * Copythe value of message in the EditText
     */
    private void copyMessageInEdt(){
        edtMessage.setText(messageTemp);
    }

    /**
     * Abord copy
     */
    private void abordCopy(){
        Toast.makeText(this, R.string.toast_abordcopy,Toast.LENGTH_LONG).show();
    }

    /**
     * Delete all the item
     */
    public void deleteAll(){
        humanRecyclerAdapter.deleteAll();
    }

    /**
     * Add a bunch of people
     */
    public void addAll(){
        int size= humanRecyclerAdapter.getItemCount();
        for(int i=size;i<size+2;i++){
            humen.add(new Human("Item "+i,i));
        }
        humanRecyclerAdapter.notifyItemRangeInserted(size,2);
    }
}
