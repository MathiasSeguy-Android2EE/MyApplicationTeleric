/**
 * <ul>
 * <li>MainFragment</li>
 * <li>com.android2ee.formation.juilmmxvi.teleric.view.main.main_fragment</li>
 * <li>22/07/2016</li>
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

package com.android2ee.formation.juilmmxvi.teleric.view.main.main_fragment;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android2ee.formation.juilmmxvi.teleric.R;
import com.android2ee.formation.juilmmxvi.teleric.model.Human;
import com.android2ee.formation.juilmmxvi.teleric.view.main.adapter.HumanRecyclerAdapter;
import com.android2ee.formation.juilmmxvi.teleric.view.main.adapter.OnItemClickedRecyclerView;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

/**
 * Created by Mathias Seguy - Android2EE on 22/07/2016.
 */
public class MainFragment extends Fragment implements OnItemClickedRecyclerView {
    private static final String TAG = "MainFragment";
    /***********************************************************
     * Constant
     **********************************************************/
    public static final String EDT = "EDT";
    public static final String RES = "RES";
    public static final String MY_DIALOG_ERROR = "MyDialogError";
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
    /**
     * The AlertDialog
     */
    DeleteDialog dialogFragment;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        isHoneyComb=getResources().getBoolean(R.bool.isHC);
        setHasOptionsMenu(true);
    }

    @SuppressLint("NewApi")
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Context ctx=getActivity();
        View view=inflater.inflate(R.layout.fragment_main,container,false);
        //instantiate graphical components
        edtMessage = (EditText) view.findViewById(R.id.edtMessage);
        btnAdd = (Button) view.findViewById(R.id.btnAdd);
        rcvResult = (RecyclerView) view.findViewById(R.id.rcvResult);
        //instanciate animation
        if(isHoneyComb){
            animatorSet= (AnimatorSet) AnimatorInflater.loadAnimator(ctx,R.animator.btnadd_pushed_anim);
            animatorSet.setTarget(btnAdd);
        }else{
            animation= AnimationUtils.loadAnimation(ctx,R.anim.btn_add_pushed);
        }
        //to avoid selector to be drawn like a big rectancle
        if (savedInstanceState != null) {
            humen = savedInstanceState.getParcelableArrayList(RES);
            edtMessage.setText(savedInstanceState.getString(EDT));
        } else {
            humen = new ArrayList<>();
            for(int i=0;i<2;i++){
                humen.add(new Human("Item "+i,i));
            }
        }
        humanRecyclerAdapter = new HumanRecyclerAdapter(humen, ctx) {
            /**
             * The item is clicked please do something
             *
             * @param position
             */
            @Override
            public void itemClicked(int position) {
                MainFragment.this.itemClicked(position);
            }
        };
        rcvResult.setLayoutManager(new LinearLayoutManager(ctx));
        rcvResult.setAdapter(humanRecyclerAdapter);
        //implement the listeners
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addMessageInResult();
            }
        });
        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putString(EDT, edtMessage.getText().toString());
        outState.putParcelableArrayList(RES, humen);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onStop() {
        super.onStop();
        dialogFragment= (DeleteDialog) getFragmentManager().findFragmentByTag(MY_DIALOG_ERROR);
        if(dialogFragment!=null){
            if(dialogFragment.isVisible()){
                dialogFragment.dismiss();
            }
            dialogFragment=null;
        }
    }

    /***********************************************************
     *  Managing menu
     ****************-******************************************/
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.main_menu,menu);
        super.onCreateOptionsMenu(menu, inflater);
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
        Log.e(TAG,"itemClicked called at "+position);
        messageTemp=humanRecyclerAdapter.getItem(position).getMessage();
        //showDialog(AlertDialogUniqueID);
        dialogFragment= (DeleteDialog) getActivity().getSupportFragmentManager().findFragmentByTag(MY_DIALOG_ERROR);
        if(dialogFragment==null){
            dialogFragment=new DeleteDialog(this);
        }
        dialogFragment.show(getFragmentManager(),"StackDialogDisaplyed");
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
        Toast.makeText(getActivity(), R.string.toast_abordcopy,Toast.LENGTH_LONG).show();
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

    /***********************************************************
     *  AlertDialogFragment pattern
     **********************************************************/
    @SuppressLint("ValidFragment")
    private static class DeleteDialog extends DialogFragment{
        WeakReference<MainFragment> mainFragmentWeak;
        public DeleteDialog(MainFragment frag){
            mainFragmentWeak=new WeakReference<MainFragment>(frag);
        }
        @NonNull
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            AlertDialog.Builder alertBulder=new AlertDialog.Builder(getActivity());
            if(mainFragmentWeak!=null&&mainFragmentWeak.get()!=null){
                alertBulder.setMessage(getString(R.string.alertdialog_message,mainFragmentWeak.get().messageTemp));
            }else{
                alertBulder.setMessage(getString(R.string.alertdialog_message,":("));
            }
            alertBulder.setPositiveButton(R.string.alertdialog_ok, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if(mainFragmentWeak!=null&&mainFragmentWeak.get()!=null){
                        mainFragmentWeak.get().copyMessageInEdt();
                    }
                }
            });
            alertBulder.setNegativeButton(R.string.alertdialog_nok, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if(mainFragmentWeak!=null&&mainFragmentWeak.get()!=null){
                        mainFragmentWeak.get().abordCopy();
                    }
                }
            });
            return alertBulder.create();
        }

        @Override
        public void onResume() {
            super.onResume();
            if(mainFragmentWeak!=null&&mainFragmentWeak.get()!=null){
                ((AlertDialog)getDialog()).setMessage(getString(R.string.alertdialog_message,mainFragmentWeak.get().messageTemp));
            }
        }
    }
}
