package com.yndongyong.widget.multiitem;

import android.util.SparseArray;

import java.util.List;

/**
 * Created by dongzhiyong on 2017/5/28.
 */

public interface ITypePool {

    void register(ItemViewProvider itemView);

    void register(ITypePool pool);

    SparseArray<ItemViewProvider> getAllItemViewProvider();

    List<Integer> findItemViewProviderAllIndex(String typeName);


}
