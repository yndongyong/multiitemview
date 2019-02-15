package com.yndongyong.widget.demo.viewproviders;

import android.support.v7.widget.GridLayoutManager;
import android.util.DisplayMetrics;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.yndongyong.adapter.ItemViewProvider;
import com.yndongyong.adapter.SimpleViewHolder;
import com.yndongyong.widget.demo.R;
import com.yndongyong.widget.demo.entities.NewsEntry;

public class NewsEntryStyle2ItemViewProvider extends ItemViewProvider<NewsEntry> {

    @Override
    public int getLayoutId() {
        return R.layout.list_item_home_styl2;
    }

    @Override
    public void onBindViewHolder(SimpleViewHolder holder, NewsEntry entity) {
        GridLayoutManager.LayoutParams layoutParams = (GridLayoutManager.LayoutParams) holder.itemView.getLayoutParams();
        DisplayMetrics metrics =  holder.getContext().getResources().getDisplayMetrics();
        int mScreenHeightPx = Math.max(metrics.heightPixels, metrics.widthPixels);
        int mScreenWidthPx = Math.min(metrics.heightPixels, metrics.widthPixels);
        layoutParams.height = mScreenWidthPx / 3 - holder.getContext().getResources().getDimensionPixelOffset(R.dimen.dp_2) ;


//        holder.setText(R.id.tv_category_name, entity.getDescription());
        ImageView imageView = holder.getView(R.id.iv_icon);
        Glide.with(imageView).load(entity.getUrl()).into(imageView);
    }

    @Override
    public boolean accept(NewsEntry newsEntry, int position) {
        return newsEntry.getType() == 2;
    }
}
