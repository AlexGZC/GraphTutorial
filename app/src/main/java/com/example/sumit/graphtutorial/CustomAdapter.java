package com.example.sumit.graphtutorial;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Space;
import android.widget.TextView;

import java.util.List;

/**
 * Created by sumit on 12/22/2016.
 */

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {

    private Context context;
    private List<Data> my_data;

    public CustomAdapter(Context context, List<Data> my_data) {
        this.context = context;
        this.my_data = my_data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout,parent,false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        holder.subject.setText(my_data.get(position).getSubject().toString());
        holder.subject.setBackgroundColor(Color.argb(255,my_data.get(position).getR(),my_data.get(position).getG(),my_data.get(position).getB()));



    }

    @Override
    public int getItemCount() {
        return my_data.size();
    }

    public  class ViewHolder extends  RecyclerView.ViewHolder{


       TextView subject;


        public ViewHolder(View itemView) {
            super(itemView);

            subject=(TextView)itemView.findViewById(R.id.subject);

        }
    }
}
