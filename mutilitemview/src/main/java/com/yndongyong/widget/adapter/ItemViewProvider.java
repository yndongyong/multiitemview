package com.yndongyong.widget.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by dongzhiyong on 2017/5/28.
 */

public abstract class ItemViewProvider<T>  {

    protected SimpleViewHolder onCreateViewHolder(Context context, LayoutInflater inflater, ViewGroup parent){
        return new SimpleViewHolder(context,inflater.inflate(getLayoutId(),parent,false));
    }

    protected void onBindViewHolder(SimpleViewHolder holder, T entity, List<Object> payloads) {

    }

    public abstract int getLayoutId();

    public abstract  void onBindViewHolder(SimpleViewHolder holder, T entity);

}
