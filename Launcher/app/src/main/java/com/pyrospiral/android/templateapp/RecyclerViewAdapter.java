package com.pyrospiral.android.templateapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

/**
 * Created by Kush on 4/3/2015.
 */
public class RecyclerViewAdapter  extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>{

    private LayoutInflater inflater;
    List<AppDrawer.RecyclerData> data = Collections.emptyList();

    public RecyclerViewAdapter(Context context, List<AppDrawer.RecyclerData> data) {
        inflater = LayoutInflater.from(context);
        this.data = data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.recycler_view_holder, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewAdapter.MyViewHolder holder, int position) {

        AppDrawer.RecyclerData current = data.get(position);
        holder.data1.setText(current.label);
        holder.img.setImageDrawable(current.icon);
        holder.data3.setText(current.name);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView data1, data3;
        ImageView img;


        public MyViewHolder(View itemView) {
            super(itemView);
            data1 = (TextView) itemView.findViewById(R.id.data1);
            img = (ImageView) itemView.findViewById(R.id.data2);
            data3 = (TextView)itemView.findViewById(R.id.data3);
        }
    }



}
