package com.yndongyong.widget.multiitem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by dongzhiyong on 2017/5/28.
 */

public abstract class ItemViewProvider<T>  {

    public SimpleAdapter mSimpleAdapter;
    public Context mContext;

    protected SimpleViewHolder onCreateViewHolder(Context context, LayoutInflater inflater, ViewGroup parent){
        return new SimpleViewHolder(context,inflater.inflate(getLayoutId(),parent,false));
    }

    protected void onBindViewHolder(SimpleViewHolder holder, T entity, List<Object> payloads) {
        onBindViewHolder(holder,entity);
    }

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
     * T类 对应的itemviewtype的布局文件
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
