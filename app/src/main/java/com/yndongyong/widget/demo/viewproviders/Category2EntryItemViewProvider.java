package com.yndongyong.widget.demo.viewproviders;

import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.yndongyong.adapter.ItemViewProvider;
import com.yndongyong.adapter.SimpleViewHolder;
import com.yndongyong.widget.demo.R;
import com.yndongyong.widget.demo.entities.CategoryEntry;

/**
 * Created by dongzhiyong on 2017/6/14.
 */

public class Category2EntryItemViewProvider extends ItemViewProvider<CategoryEntry> {

    @Override
    public int getLayoutId() {
        return R.layout.item_category_2;
    }

    @Override
    public void onBindViewHolder(final SimpleViewHolder holder, CategoryEntry entity) {
        ImageView icon = holder.getView(R.id.iv_icon);
        Glide.with(icon).load(entity.getUrl()).into(icon);
        holder.setText(R.id.tv_category_name, entity.getDescription());
        holder.setOnClickListener(R.id.iv_icon, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(holder.getContext(),  "click image position:" + holder.getAdapterPosition() + ";", Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public boolean accept(CategoryEntry categoryEntry, int position) {
        return categoryEntry.getType() == 2;
    }
}
