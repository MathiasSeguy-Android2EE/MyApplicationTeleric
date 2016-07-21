/**
 * <ul>
 * <li>HumanRecyclerAdapter</li>
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
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android2ee.formation.juilmmxvi.teleric.R;
import com.android2ee.formation.juilmmxvi.teleric.model.Human;

import java.util.ArrayList;

/**
 * Created by Mathias Seguy - Android2EE on 19/07/2016.
 */
public abstract class HumanRecyclerAdapter extends RecyclerView.Adapter<HumanRecyclerAdapter.ViewHolder>
implements OnItemClickedRecyclerView{
    /***********************************************************
    *  Attributes
    **********************************************************/
    /**
     * dataset
     */
    ArrayList<Human> humen;
    /**
     * The inflater
     */
    LayoutInflater inflater;
    /***********************************************************
    *  Constructors
    **********************************************************/

    public HumanRecyclerAdapter(ArrayList<Human>dataset,Context ctx){
        humen=dataset;
        inflater=LayoutInflater.from(ctx);
    }

    /***********************************************************
     * Method of the RecyclerAdapter
     **********************************************************/
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rowView;
        if(viewType==0){
            rowView=inflater.inflate(R.layout.human_even,parent,false);
        }else{
            rowView=inflater.inflate(R.layout.human_odd,parent,false);
        }
        ViewHolder vh= new ViewHolder(rowView) {
            /**
             * When the item is click do a stuff
             */
            @Override
            public void itemClicked(int position) {
                HumanRecyclerAdapter.this.itemClicked(position);
            }

            /**
             * Please find a way to delete the current item
             */
            @Override
            public void deleteItem(int position) {
                HumanRecyclerAdapter.this.deleteItem(position);
            }
        };
        return vh;
    }
    Human humanTemp;
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        humanTemp=humen.get(position);
        holder.getTxvName().setText(humanTemp.getName());
        holder.getTxvMessage().setText(humanTemp.getMessage());
        holder.getImvPicture().setImageResource(humanTemp.getPicture());
        holder.setPosition(position);
    }

    /***********************************************************
    *  Methods to manage the list of humen
    **********************************************************/
    @Override
    public int getItemCount() {
        return humen.size();
    }

    /**
     * return the fucking human at that fucking position
     * @param position
     * @return
     */
    public Human getItem(int position){
        return humen.get(position);
    }

    /**
     * Add an human at a specific position
     * @param position
     * @param hum
     */
    public void add(int position,Human hum){
        humen.add(position,hum);
        notifyItemInserted(position);
    }
    /**
     * Add an human
     * @param hum
     */
    public void add(Human hum){
        humen.add(0,hum);
        notifyItemInserted(0);
    }

    /**
     * Delete all the item
     */
    public void deleteAll(){
        int size=humen.size();
        humen.clear();
        notifyItemRangeRemoved(0,size);
    }
    @Override
    public int getItemViewType(int position) {
        //depending on the position return in which pool to pick the view
        humanTemp=humen.get(position);
        return position%2==0?0:1;
    }

    /***********************************************************
    *  Methods
    **********************************************************/

    /**
     * Delete the item
     */
    public void deleteItem(int position){
        humen.remove(position);
        notifyItemRemoved(position);
    }

    /**
     * The item is clicked please do something
     * @param position
     */
    public abstract void itemClicked(int position);
    /***********************************************************
     * ViewHolder pattern
     **********************************************************/
    public abstract class ViewHolder extends RecyclerView.ViewHolder implements OnItemClickedRecyclerView{
        private TextView txvName,txvMessage;
        private ImageView imvPicture,imvDelete;
        private int position;

        public ViewHolder(View itemView){
            super(itemView);
            txvName= (TextView) itemView.findViewById(R.id.txvName);
            txvMessage= (TextView) itemView.findViewById(R.id.txvMessage);
            imvPicture= (ImageView) itemView.findViewById(R.id.imvPicture);
            imvDelete= (ImageView) itemView.findViewById(R.id.imvDelete);
            imvDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   deleteItem(position);
                }
            });
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemClicked(position);
                }
            });
        }

        /***********************************************************
        *  Interface Methods
        **********************************************************/


        /**
         * Please find a way to delete the current item
         */
        public abstract void deleteItem(int position);

        /***********************************************************
        *  Getters/Setters
        **********************************************************/

        public ImageView getImvPicture() {
            return imvPicture;
        }

        public TextView getTxvMessage() {
            return txvMessage;
        }

        public TextView getTxvName() {
            return txvName;
        }

        public void setPosition(int position) {
            this.position = position;
        }

    }
}
