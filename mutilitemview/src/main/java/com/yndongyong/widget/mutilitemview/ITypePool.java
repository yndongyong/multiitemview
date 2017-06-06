package com.yndongyong.widget.mutilitemview;

import android.support.v7.widget.RecyclerView;

import java.util.List;

/**
 * Created by dongzhiyong on 2017/5/28.
 */

public interface ITypePool {

    void register(Class<?> clazz,ItemViewProvider<?,? extends RecyclerView.ViewHolder> itemView);

    void register(ITypePool pool);

    /**
     * 得到值作为Viewtype
     * @param clazz
     * @return
     */
    int indexOfTypePool(Class<?> clazz);

    ItemViewProvider findViewProviderByIndex(int index);

    List<Class<?>> getCategory();

    List<ItemViewProvider<?,? extends RecyclerView.ViewHolder>> getProviders();

    int getCount();

}
