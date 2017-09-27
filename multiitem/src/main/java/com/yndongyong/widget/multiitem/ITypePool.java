package com.yndongyong.widget.multiitem;

import java.util.List;

/**
 * Created by dongzhiyong on 2017/5/28.
 */

public interface ITypePool {

    void register(Class<?> clazz,List<ItemViewProvider> itemView ,Convertor convertor);

    void register(ITypePool pool);

    int indexOfTypePool(Class<?> clazz);


    List<ItemViewProvider> findViewProvidersByIndex(int index);

    ItemViewProvider getViewProviderByViewType(int viewType);

    Convertor findConvertorByClass(Class clazz);

    List<Class<?>> getCategory();

}
