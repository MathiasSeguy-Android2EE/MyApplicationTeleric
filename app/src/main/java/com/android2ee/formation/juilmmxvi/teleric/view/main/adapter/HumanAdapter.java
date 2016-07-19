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
    ViewHolder vh;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //create the view
        rowView=convertView;
        if(rowView==null){
            if(getItemViewType(position)==0){
                rowView=inflater.inflate(R.layout.human_even,parent,false);
            }else{
                rowView=inflater.inflate(R.layout.human_odd,parent,false);
            }
            vh=new ViewHolder(rowView);
            rowView.setTag(vh);
        }
        //update the view
        humanTemp=getItem(position);
        vh= (ViewHolder) rowView.getTag();
        vh.getTxvName().setText(humanTemp.getName());
        vh.getTxvMessage().setText(humanTemp.getMessage());
        vh.getImvPicture().setImageResource(humanTemp.getPicture());
        //return it
        return rowView;
    }

    /***********************************************************
     *  Managing multiple layouts
     **********************************************************/
    @Override
    public int getViewTypeCount() {
        //how many pools I have in my convertView's pool
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        //depending on the position return in which pool to pick the view
        humanTemp=getItem(position);

        return humanTemp.getName().equals("toto")?0:1;
    }

    /***********************************************************
     *  The ViewHolder pattern
     **********************************************************/
    public class ViewHolder{
        private TextView txvName,txvMessage;
        private ImageView imvPicture;
        public ViewHolder(View view){
            txvName= (TextView) view.findViewById(R.id.txvName);
            txvMessage= (TextView) view.findViewById(R.id.txvMessage);
            imvPicture= (ImageView) view.findViewById(R.id.imvPicture);
        }

        public ImageView getImvPicture() {
            return imvPicture;
        }

        public TextView getTxvMessage() {
            return txvMessage;
        }

        public TextView getTxvName() {
            return txvName;
        }
    }
}
