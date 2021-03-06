package com.yndongyong.widget.mutilitemview;


import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dongzhiyong on 2017/5/28.
 */
public class MultiTypePool implements ITypePool {

    private List<Class<?>> categorys;
    private List<ItemViewProvider<?, ? extends RecyclerView.ViewHolder>> providers;

    public MultiTypePool() {
        this.categorys = new ArrayList<>(5);
        this.providers = new ArrayList<>(5);
    }

    @Override
    public void register(Class<?> clazz, ItemViewProvider<?, ? extends RecyclerView.ViewHolder> itemView) {
        if (!this.categorys.contains(clazz)) {
            this.categorys.add(clazz);
            this.providers.add(itemView);
        }
    }

    @Override
    public void register(ITypePool pool) {
        if (pool.getCategory().size() == 0) {
            return;
        }
        this.categorys.addAll(pool.getCategory());
        this.providers.addAll(pool.getProviders());
    }

    @Override
    public int indexOfTypePool(Class<?> clazz) {
        if (!this.categorys.contains(clazz)) {
            throw new RuntimeException("Unregistered " + clazz.getSimpleName() + " type !!!");
        }
        return this.categorys.indexOf(clazz);
    }


    @Override
    public ItemViewProvider findViewProviderByIndex(int index) {
        return providers.get(index);
    }

    @Override
    public List<Class<?>> getCategory() {
        return categorys;
    }

    @Override
    public List<ItemViewProvider<?, ? extends RecyclerView.ViewHolder>> getProviders() {
        return providers;
    }

    @Override
    public int getCount() {
        return categorys != null ? categorys.size() : 0;
    }

    @Override
    public void clear() {
        this.categorys.clear();
        this.providers.clear();
    }
}
