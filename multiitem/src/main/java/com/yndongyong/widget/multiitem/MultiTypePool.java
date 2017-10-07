package com.yndongyong.widget.multiitem;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dongzhiyong on 2017/5/28.
 */
public class MultiTypePool implements ITypePool {

    private List<Class<?>> mCategorys;
    private List<List<ItemViewProvider>> mProviders;
    private Map<Class, Converter> mConverters;


    public MultiTypePool() {
        this.mCategorys = new ArrayList<>(5);
        this.mProviders = new ArrayList<>(5);
        this.mConverters = new HashMap<>(5);
    }

    @Override
    public void register(Class<?> clazz, List<ItemViewProvider> itemView, Converter convertor) {
        if (!this.mCategorys.contains(clazz)) {
            this.mCategorys.add(clazz);
            this.mProviders.add(itemView);
            this.mConverters.put(clazz, convertor);
        } else {
            throw new RuntimeException(String.format("%s has register!!!", clazz.getCanonicalName().toString()));
        }
    }

    @Override
    public void register(ITypePool pool) {
        this.mCategorys.addAll(pool.getCategory());
    }

    @Override
    public int indexOfCategorys(Class<?> clazz) {
        if (!this.mCategorys.contains(clazz)) {
            throw new RuntimeException("Unregistered " + clazz.getSimpleName() + " type !!!");
        }
        return this.mCategorys.indexOf(clazz);
    }

    @Override
    public List<ItemViewProvider> findViewProvidersByIndex(int index) {
        return this.mProviders.get(index);
    }

    @Override
    public Converter findConverterByClass(Class clazz) {
        return this.mConverters.get(clazz);
    }

    @Override
    public List<Class<?>> getCategory() {
        return mCategorys;
    }

}
