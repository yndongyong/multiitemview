package com.yndongyong.widget.multiitemview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

/**
 * Created by dongzhiyong on 2017/5/28.
 */

public abstract class ItemViewProvider<T,VH extends RecyclerView.ViewHolder>  {

    public abstract  VH onCreateViewHolder(LayoutInflater inflater, ViewGroup parent);

    abstract public void onBindViewHolder(VH holder, T entity);

}
