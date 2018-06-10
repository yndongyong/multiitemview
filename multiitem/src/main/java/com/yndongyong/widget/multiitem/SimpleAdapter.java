package com.yndongyong.widget.multiitem;

import android.app.Activity;
import android.content.Context;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by dongzhiyong on 2017/5/29.
 */
public class SimpleAdapter extends RecyclerView.Adapter<SimpleViewHolder> {

    private Items mItems;
    private ITypePool mTypePool;
    private LayoutInflater mLayoutInflater;
    private Context mContext;

    public SimpleAdapter(Context context) {
        this(context, new Items());
    }

    public SimpleAdapter(Context context, Items items) {
        this.mContext = context;
        this.mItems = items;
        this.mTypePool = new MultiTypePool();
    }


    @NonNull
    @Override
    public SimpleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mLayoutInflater == null) {
            mLayoutInflater = LayoutInflater.from(mContext);
        }
        ItemViewProvider viewProvider = getItemViewProvider(viewType);
        viewProvider.mSimpleAdapter = this;
        viewProvider.mContext = mContext;
        return viewProvider.onCreateViewHolder(mContext, mLayoutInflater, parent);
    }

    @Override
    public void onBindViewHolder(SimpleViewHolder holder, int position) {
        int itemViewType = holder.getItemViewType();
        ItemViewProvider viewProvider = getItemViewProvider(itemViewType);
        viewProvider.onBindViewHolder(holder, mItems.get(position));
    }

    @Override
    public void onBindViewHolder(SimpleViewHolder holder, int position, List<Object> payloads) {
        int itemViewType = holder.getItemViewType();
        ItemViewProvider viewProvider = getItemViewProvider(itemViewType);
        viewProvider.onBindViewHolder(holder, mItems.get(position), payloads);
    }

    @Override
    public int getItemViewType(int position) {

        Object entity = mItems.get(position);

        String typeName = entity.getClass().getName();

        List<Integer> itemViewProviderIndexs = mTypePool.findItemViewProviderAllIndex(typeName);

        if (itemViewProviderIndexs.size() == 0) {
            throw new RuntimeException(String.format(" %s ItemViewProvider is not register !!!", entity.getClass().getSimpleName().toString()));

        }
        for (Integer index : itemViewProviderIndexs) {
            ItemViewProvider itemViewProvider = mTypePool.getAllItemViewProvider().valueAt(index);
            if (itemViewProvider != null && itemViewProvider.accept(entity, position)) {
                return index;
            }
        }
        throw new RuntimeException(String.format(" %s's ItemViewProvider a branch is not register !!!", entity.getClass().getSimpleName().toString()));
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return this.mItems == null ? 0 : this.mItems.size();
    }

    private ItemViewProvider getItemViewProvider(int itemViewType) {
        return mTypePool.getAllItemViewProvider().valueAt(itemViewType);
    }

    /**
     * 添加新的数据 带刷新
     *
     * @param data
     */
    public void addNewDataWithNotify(Items data) {
        this.mItems.clear();
        this.mItems.addAll(data);
        notifyDataSetChanged();
    }

    /**
     * 追加更多的数据
     *
     * @param data
     */
    public void addMoreData(Items data) {
        this.mItems.addAll(data);
    }

    /**
     * 追加更多的数据 带刷新
     *
     * @param data
     */
    public void addMoreDataWithNotify(Items data) {
        int size = this.mItems.size();
        this.mItems.addAll(data);
        this.notifyItemRangeInserted(size, data.size());

    }

    /**
     * 局部刷新
     *
     * @param newData
     * @param callBack
     */
    public void updateItemsWithNotify(List<?> newData, SimpleDiff.CallBack callBack) {
        if (newData == null || newData.isEmpty()) {
            return;
        }
        Items oldList = new Items(mItems);
        final DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new SimpleDiff(oldList, newData, callBack));
        this.mItems.clear();
        this.mItems.addAll(newData);
        if (Looper.myLooper() == Looper.getMainLooper()) {
            diffResult.dispatchUpdatesTo(this);
        } else {
            ((Activity) mContext).runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    diffResult.dispatchUpdatesTo(SimpleAdapter.this);
                }
            });
        }
    }

    public SparseArray<ItemViewProvider> getAllItemViewProvider() {
        return mTypePool.getAllItemViewProvider();
    }

    //为方便使用添加的方法

    public static SimpleAdapter create(Context context) {
        SimpleAdapter adapter = new SimpleAdapter(context);
        return adapter;
    }

    /**
     * 添加新的数据 不带刷新
     *
     * @param data
     */
    public SimpleAdapter addNewData(Items data) {
        this.mItems.clear();
        this.mItems.addAll(data);
        return SimpleAdapter.this;
    }

    public SimpleAdapter register(ItemViewProvider<?> viewProvider) {
        SimpleAdapter.this.mTypePool.register(viewProvider);
        return SimpleAdapter.this;
    }

    public SimpleAdapter attachToRecyclerView(RecyclerView recyclerView) {
        recyclerView.setAdapter(SimpleAdapter.this);
        return SimpleAdapter.this;
    }


}
