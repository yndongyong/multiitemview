package com.yndongyong.widget.demo;

import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.yndongyong.widget.multiitem.ItemViewProvider;
import com.yndongyong.widget.multiitem.SimpleViewHolder;

/**
 * Created by dongzhiyong on 2017/6/14.
 */

public class HeaderItemViewProvider extends ItemViewProvider<String> {

    @Override
    public int getLayoutId() {
        return R.layout.item_header;
    }

    @Override
    public void onBindViewHolder(final SimpleViewHolder holder, String entity) {
        holder.setText(R.id.tv_header_name, entity);

        holder.setOnClickListener(R.id.tv_header_name, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(holder.getContext(),  "click header position:" + holder.getAdapterPosition() + ";", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
