package com.yndongyong.widget.multiitem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by dongzhiyong on 2017/5/28.
 */

public abstract class ItemViewProvider<T>  {

    public SimpleAdapter simpleAdapter;
    public Context context;

    protected SimpleViewHolder onCreateViewHolder(Context context, LayoutInflater inflater, ViewGroup parent){
        return new SimpleViewHolder(context,inflater.inflate(getLayoutId(),parent,false));
    }

    protected void onBindViewHolder(SimpleViewHolder holder, T entity, List<Object> payloads) {
        onBindViewHolder(holder,entity);
    }

    /**
     * 判断 是否可以处理T 这种类型,已过一个T 类型的数据对应两种Itemviewprovider 那么都需要重写该方法
     * @param t
     * @param position adapter 中的下标
     * @return
     */
    public boolean accept(T t,int position){
        return true;
    }

    /**
     *  为当前provider可以的处理的T类型增加一个tag标识，通过accept和tag共同判断是或否可以处理T类型
     * @param t
     * @return
     */
    public String getTag(T t) {
        return "";
    }

    /**
     * T类 对应的itemview的布局文件
     * @return
     */
    public abstract int getLayoutId();

    /**
     *
     * @param holder 通用的ViewHolder
     * @param entity
     */
    public abstract  void onBindViewHolder(SimpleViewHolder holder, T entity);


}
