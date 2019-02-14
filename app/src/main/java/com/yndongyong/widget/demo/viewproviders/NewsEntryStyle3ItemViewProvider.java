package com.yndongyong.widget.demo.viewproviders;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.yndongyong.adapter.ItemViewProvider;
import com.yndongyong.adapter.SimpleViewHolder;
import com.yndongyong.widget.demo.R;
import com.yndongyong.widget.demo.entities.NewsEntry;

public class NewsEntryStyle3ItemViewProvider extends ItemViewProvider<NewsEntry> {
    @Override
    public int getLayoutId() {
        return R.layout.list_item_home_styl2;
    }


    @Override
    public void onBindViewHolder(SimpleViewHolder holder, NewsEntry entity) {
        holder.setText(R.id.tv_category_name, entity.getDescription());
        ImageView imageView = holder.getView(R.id.iv_icon);
        Glide.with(imageView).load(entity.getUrl()).into(imageView);
    }
    @Override
    public boolean accept(NewsEntry newsEntry, int position) {
        return newsEntry.getType() == 3;
    }
}
