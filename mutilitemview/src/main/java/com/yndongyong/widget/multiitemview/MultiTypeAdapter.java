package com.yndongyong.widget.multiitemview;

import android.content.Context;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
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
    private Context mContext;

    public MultiTypeAdapter(Context context,Items items) {
        this.mContext = context;
        this.items = items;
        this.typePool = new MultiTypePool();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (inflater == null) {
            inflater = LayoutInflater.from(mContext);
        }
        ItemViewProvider viewProvider = this.typePool.findViewProviderByIndex(viewType);
        return viewProvider.onCreateViewHolder(mContext,inflater,parent);
    }

    @NonNull
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int itemViewType = holder.getItemViewType();
        ItemViewProvider viewProvider = this.typePool.findViewProviderByIndex(itemViewType);
        viewProvider.onBindViewHolder((SimpleViewHolder) holder,items.get(position));
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

    public void register(Class<?> clazz, ItemViewProvider<?> viewProvider) {
        this.typePool.register(clazz, viewProvider);
    }

    public void register(ITypePool pool) {
        this.typePool.register(pool);
    }

}
