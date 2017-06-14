package com.yndongyong.widget.multiitemview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

/**
 * Created by dongzhiyong on 2017/5/28.
 */

public abstract class ItemViewProvider<T>  {

    public RecyclerView.ViewHolder onCreateViewHolder(Context context,LayoutInflater inflater, ViewGroup parent){
        return new SimpleViewHolder(context,inflater.inflate(getLayoutId(),parent,false));
    }

    public abstract int getLayoutId();

    public abstract  void onBindViewHolder(SimpleViewHolder holder, T entity);

}
