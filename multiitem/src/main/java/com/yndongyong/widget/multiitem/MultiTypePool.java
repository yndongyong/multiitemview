package com.yndongyong.widget.multiitem;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dongzhiyong on 2017/5/28.
 */
public class MultiTypePool implements ITypePool {

    private List<Class<?>> categorys;
    private List<List<ItemViewProvider>> providers;
    private Map<Class, Convertor> convertors;


    public MultiTypePool() {
        this.categorys = new ArrayList<>(5);
        this.providers = new ArrayList<>(5);
        this.convertors = new HashMap<>(5);
    }

    @Override
    public void register(Class<?> clazz, List<ItemViewProvider> itemView, Convertor convertor) {
        if (!this.categorys.contains(clazz)) {
            this.categorys.add(clazz);
            this.providers.add(itemView);
            this.convertors.put(clazz, convertor);
        } else {
            throw new RuntimeException(String.format("%s has register!!!", clazz.getCanonicalName().toString()));
        }
    }

    @Override
    public void register(ITypePool pool) {
        this.categorys.addAll(pool.getCategory());
    }

    @Override
    public int indexOfTypePool(Class<?> clazz) {
        if (!this.categorys.contains(clazz)) {
            throw new RuntimeException("Unregistered " + clazz.getSimpleName() + " type !!!");
        }
        return this.categorys.indexOf(clazz);
    }

    @Override
    public List<ItemViewProvider> findViewProvidersByIndex(int index) {
        return this.providers.get(index);
    }

    @Override
    public ItemViewProvider getViewProviderByViewType(int viewType) {
        return null;
    }

    @Override
    public Convertor findConvertorByClass(Class clazz) {
        return this.convertors.get(clazz);
    }

    @Override
    public List<Class<?>> getCategory() {
        return categorys;
    }

}
