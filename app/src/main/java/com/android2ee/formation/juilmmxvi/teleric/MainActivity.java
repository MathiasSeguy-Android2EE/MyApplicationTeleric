package com.android2ee.formation.juilmmxvi.teleric;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

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
    private TextView txvResult;

    /***********************************************************
     * Managing LifeCycle
     **********************************************************/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //instantiate the view
        setContentView(R.layout.activity_main);
        //instantiate graphical components
        edtMessage = (EditText) findViewById(R.id.edtMessage);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        txvResult = (TextView) findViewById(R.id.txvResult);
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
        super.onSaveInstanceState(outState);
        outState.putString(EDT,edtMessage.getText().toString());
        outState.putString(RES,txvResult.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        edtMessage.setText(savedInstanceState.getString(EDT));
        txvResult.setText(savedInstanceState.getString(RES));
    }
    /***********************************************************
 *  Business Methods
 **********************************************************/
    /**
     * Add the message from the editText in the Result area
     */
    private void addMessageInResult() {
        String message = txvResult.getText() + "\r\n" + edtMessage.getText().toString();
        txvResult.setText(message);
        edtMessage.setText("");
    }
}
