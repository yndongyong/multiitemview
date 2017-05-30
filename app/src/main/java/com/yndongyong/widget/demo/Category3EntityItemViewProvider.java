package com.yndongyong.widget.demo;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yndongyong.widget.mutilitemview.ItemViewProvider;
import com.yndongyong.widget.mutilitemview.Items;
import com.yndongyong.widget.mutilitemview.MultiTypeAdapter;

/**
 * Created by dongzhiyong on 2017/5/29.
 */

public class Category3EntityItemViewProvider extends ItemViewProvider<Category3Entry,Category3EntityItemViewProvider.InnerViewHolder> {

    @Override
    public InnerViewHolder onCreateViewHolder(LayoutInflater inflater, ViewGroup parent) {
        return new InnerViewHolder(inflater.inflate(R.layout.item_category_3,parent,false));
    }

    @Override
    public void onBindViewHolder(InnerViewHolder holder, Category3Entry entity) {
        Items items = new Items();
        items.addAll(entity.getDatas());
        MultiTypeAdapter typeAdapter = new MultiTypeAdapter(items);
        typeAdapter.register(Category2Entry.class,new Category2EntryItemViewProvider());
        holder.rv_list.setAdapter(typeAdapter);
        holder.rv_list.setLayoutManager(new LinearLayoutManager(holder.rv_list.getContext(), LinearLayoutManager.HORIZONTAL, false));

    }

    class InnerViewHolder extends RecyclerView.ViewHolder {
        private RecyclerView rv_list;

        public InnerViewHolder(View itemView) {
            super(itemView);
            this.rv_list = (RecyclerView) itemView.findViewById(R.id.rv_list);
        }
    }
}
