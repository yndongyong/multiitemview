package com.yndongyong.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by yndongyong on 2017/5/28.
 */

public abstract class ItemViewProvider<T>  {

    public SimpleAdapter mSimpleAdapter;

    protected SimpleViewHolder onCreateViewHolder(Context context, LayoutInflater inflater, ViewGroup parent){
        return new SimpleViewHolder(context,inflater.inflate(getLayoutId(),parent,false));
    }

    /**
     * T类 对应的itemviewtype的布局文件
     * @return
     */
    public abstract int getLayoutId();

    /**
     * 判断 是否可以处理T 这种类型,如果一个T 类型的数据对应多种Itemviewprovider 那么都需要重写该方法
     * 如果只有一种viewtype则不用重写
     * @param t
     * @param position adapter 中的下标
     * @return
     */
    public boolean accept(T t,int position){
        return true;
    }

    /**
     * 绑定View 和 Entity
     * @param holder 通用的ViewHolder
     * @param entity
     */
    public abstract  void onBindViewHolder(SimpleViewHolder holder, T entity);

    /**
     * 对应Adapter的onBindViewHolder的局部刷新方法
     * @param holder
     * @param entity
     * @param payloads
     */
    protected void onBindViewHolder(SimpleViewHolder holder, T entity, List<Object> payloads) {
    }

}
