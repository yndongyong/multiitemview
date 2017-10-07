package com.yndongyong.widget.multiitem;

import java.util.List;

/**
 * Created by dongzhiyong on 2017/5/28.
 */

public interface ITypePool {

    void register(Class<?> clazz,List<ItemViewProvider> itemView ,Converter convertor);

    void register(ITypePool pool);

    int indexOfCategorys(Class<?> clazz);

    List<ItemViewProvider> findViewProvidersByIndex(int index);


//    ItemViewProvider getViewProviderByViewType(Class<?> clazz ,int viewType);

    Converter findConverterByClass(Class clazz);

    List<Class<?>> getCategory();

}
