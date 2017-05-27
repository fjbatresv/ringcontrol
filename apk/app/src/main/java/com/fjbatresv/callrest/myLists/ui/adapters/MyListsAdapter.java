package com.fjbatresv.callrest.myLists.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fjbatresv.callrest.R;
import com.fjbatresv.callrest.entities.List;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by javie on 27/05/2017.
 */

public class MyListsAdapter extends RecyclerView.Adapter<MyListsAdapter.ViewHolder> {
    private ArrayList<List> listas;

    public MyListsAdapter(ArrayList<List> listas) {
        this.listas = listas;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //View view = LayoutInflater.from(parent.getContext());
        return null;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    public void addLists(ArrayList<List> news){
        listas.clear();
        listas.addAll(news);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return listas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{


        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
