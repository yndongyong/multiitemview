package com.yndongyong.widget.demo;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.yndongyong.widget.multiitemview.ItemViewProvider;

/**
 * Created by dongzhiyong on 2017/5/29.
 */

public class Category2EntryItemViewProvider extends ItemViewProvider<Category2Entry,Category2EntryItemViewProvider.InnerViewHolder>{


    @Override
    public InnerViewHolder onCreateViewHolder(LayoutInflater inflater, ViewGroup parent) {
        return new InnerViewHolder(inflater.inflate(R.layout.item_category_1,parent,false));
    }

    @Override
    public void onBindViewHolder(final InnerViewHolder holder, Category2Entry entity) {
        holder.tv_category_name.setText(entity.getDescription());
        Glide.with(holder.iv_icon).load(entity.getUrl()).into(holder.iv_icon);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "Click viewType:" +holder.getItemViewType() +" :position:"+holder.getAdapterPosition(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    class InnerViewHolder extends RecyclerView.ViewHolder {
        private ImageView iv_icon;
        private TextView tv_category_name;
        private  View rootView;

        public InnerViewHolder(View itemView) {

            super(itemView);
            this.rootView = itemView;
            this.iv_icon = (ImageView) itemView.findViewById(R.id.iv_icon);
            this.tv_category_name = (TextView) itemView.findViewById(R.id.tv_category_name);
        }
    }
}
