package com.yndongyong.widget.demo;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.yndongyong.widget.multiitemview.ItemViewProvider;

/**
 * Created by dongzhiyong on 2017/5/29.
 */

public class StringItemViewProvider extends ItemViewProvider<String, StringItemViewProvider.InnerViewHolder> {


    @Override
    public InnerViewHolder onCreateViewHolder(LayoutInflater inflater, ViewGroup parent) {
        return new InnerViewHolder(inflater.inflate(R.layout.item_header, parent, false));
    }

    @Override
    public void onBindViewHolder(final InnerViewHolder holder, String entity) {
        holder.tv_header_name.setText(entity);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "Click viewType:" +holder.getItemViewType() +" :position:"+holder.getAdapterPosition(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    class InnerViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_header_name;
        private View rootView;

        public InnerViewHolder(View itemView) {
            super(itemView);
            rootView = itemView;
            tv_header_name = (TextView) itemView.findViewById(R.id.tv_header_name);


        }

    }
}
