package com.yndongyong.widget.multiitem;


import android.util.SparseArray;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dongzhiyong on 2017/5/28.
 */
public class MultiTypePool implements ITypePool {

    private SparseArray<ItemViewProvider> mViewProviders;
    private SparseArray<String> mDataType;

    public MultiTypePool() {
        this.mViewProviders = new SparseArray<>(3);
        this.mDataType = new SparseArray<>(3);
    }

    @Override
    public void register(ItemViewProvider itemView) {

        Type genericSuperclass = itemView.getClass().getGenericSuperclass();
        if (genericSuperclass instanceof ParameterizedType) {
            Class<?> clazz = (Class<?>)((ParameterizedType) genericSuperclass).getActualTypeArguments()[0];
            String typeName = clazz.getName();
            int viewType = mViewProviders.size();
            mViewProviders.put(viewType, itemView);
            mDataType.put(viewType,typeName);

        }else {
            throw new RuntimeException(String.format("generic parameters error on %s !!!", itemView.getClass().getCanonicalName().toString()));
        }

    }

    @Override
    public void register(ITypePool pool) {
        SparseArray<ItemViewProvider> allItemViewProvider = pool.getAllItemViewProvider();
        for (int i=0;i<allItemViewProvider.size();i++) {
            register(allItemViewProvider.get(i));
        }
    }

    @Override
    public SparseArray<ItemViewProvider> getAllItemViewProvider() {
        return mViewProviders;
    }

    @Override
    public List<Integer> findItemViewProviderAllIndex(String typeName) {
        List<Integer> indexs = new ArrayList<>();
        for (int i=0;i<mDataType.size();i++) {
            if (mDataType.get(i).equals(typeName)) {
                indexs.add(i);
            }
        }
        return indexs;
    }


}
