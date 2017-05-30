package com.yndongyong.widget.mutilitemview;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

/**
 * Created by dongzhiyong on 2017/5/29.
 */
public class MultiTypeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Items items;
    private ITypePool typePool;
    private LayoutInflater inflater;

    public MultiTypeAdapter() {
        this.items = new Items();
        this.typePool = new MultiTypePool();
    }

    public MultiTypeAdapter(Items items) {
        this();
        this.items = items;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (inflater == null) {
            inflater = LayoutInflater.from(parent.getContext());
        }
        ItemViewProvider viewProvider = this.typePool.findViewProviderByIndex(viewType);
        return viewProvider.onCreateViewHolder(inflater,parent);
    }

    @NonNull
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int itemViewType = holder.getItemViewType();
        ItemViewProvider viewProvider = this.typePool.findViewProviderByIndex(itemViewType);
        viewProvider.onBindViewHolder(holder,items.get(position));
    }

    @Override
    public int getItemCount() {
        return this.items==null?0:this.items.size();
    }

    @Override
    public int getItemViewType(int position) {
        Class<?> aClass = this.items.get(position).getClass();
        return  this.typePool.indexOfTypePool(aClass);
    }

    public void register(Class<?> clazz, ItemViewProvider<?, ?> viewProvider) {
        this.typePool.register(clazz, viewProvider);
    }

    public void register(ITypePool pool) {
        this.typePool.register(pool);
    }

}
