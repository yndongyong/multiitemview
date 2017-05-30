package com.yndongyong.widget.mutilitemview;

import java.util.List;

/**
 * Created by dongzhiyong on 2017/5/28.
 */

public interface ITypePool {

    void register(Class<?> clazz,ItemViewProvider itemView);

    void register(ITypePool pool);

    int indexOfTypePool(Class<?> clazz);


    ItemViewProvider findViewProviderByIndex(int index);

    List<Class<?>> getCategory();

    List<ItemViewProvider> getProviders();

}
