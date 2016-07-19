/**
 * <ul>
 * <li>HumanAdapter</li>
 * <li>com.android2ee.formation.juilmmxvi.teleric.view.main.adapter</li>
 * <li>19/07/2016</li>
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

package com.android2ee.formation.juilmmxvi.teleric.view.main.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android2ee.formation.juilmmxvi.teleric.R;
import com.android2ee.formation.juilmmxvi.teleric.model.Human;

import java.util.List;

/**
 * Created by Mathias Seguy - Android2EE on 19/07/2016.
 */
public class HumanAdapter extends ArrayAdapter<Human> {
    /***********************************************************
    *  Attributes
    **********************************************************/

    LayoutInflater inflater;
    /***********************************************************
    *  Constructors
    **********************************************************/
    /**
     * Constructor
     * @param context  The current context.
     * @param objects  The objects to represent in the ListView.
     */
    public HumanAdapter(Context context, List<Human> objects) {
        super(context, -1, objects);
        //instantiate heavy objects
        inflater=LayoutInflater.from(context);

    }

    /***********************************************************
     * Temp variable for getView to avoid creating pointer
     **********************************************************/
    Human humanTemp;
    View rowView;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //create the view
        rowView=inflater.inflate(R.layout.human_even,parent,false);
        //update the view
        humanTemp=getItem(position);
        ((TextView)rowView.findViewById(R.id.txvName)).setText(humanTemp.getName());
        ((TextView)rowView.findViewById(R.id.txvMessage)).setText(humanTemp.getMessage());
        ((ImageView)rowView.findViewById(R.id.imvPicture)).setImageResource(humanTemp.getPicture());
        //return it
        return rowView;
    }
}
