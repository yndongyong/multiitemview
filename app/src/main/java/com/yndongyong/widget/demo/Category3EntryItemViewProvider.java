package com.yndongyong.widget.demo;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.yndongyong.widget.multiitem.ItemViewProvider;
import com.yndongyong.widget.multiitem.Items;
import com.yndongyong.widget.multiitem.SimpleAdapter;
import com.yndongyong.widget.multiitem.SimpleViewHolder;

/**
 * Created by dongzhiyong on 2017/6/14.
 */

public class Category3EntryItemViewProvider extends ItemViewProvider<Category3Entry> {

    private SimpleAdapter simpleAdapter;
    private Items items = new Items();

    @Override
    public int getLayoutId() {
        return R.layout.item_category_3;
    }

    @Override
    protected SimpleViewHolder onCreateViewHolder(Context context, LayoutInflater inflater, ViewGroup parent) {

        return super.onCreateViewHolder(context, inflater, parent);
    }

    @Override
    public void onBindViewHolder(final SimpleViewHolder holder, Category3Entry entity) {
        RecyclerView rv_list =  holder.getView(R.id.rv_list);
        rv_list.setLayoutManager(new LinearLayoutManager(holder.getContext(),LinearLayoutManager.HORIZONTAL,false));
        items = new Items();
        items.addAll(entity.getCategoryEntries());

        simpleAdapter = SimpleAdapter.create(holder.getContext())
                .addNewData(items)
                .register(new Category1EntryItemViewProvider())
                .register(new Category2EntryItemViewProvider())
                .attachToRecyclerView(rv_list);
    }

}
