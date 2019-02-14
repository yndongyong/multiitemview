package com.yndongyong.adapter;

import android.app.Activity;
import android.content.Context;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yndongyong on 2017/5/29.
 */
public class SimpleAdapter extends RecyclerView.Adapter<SimpleViewHolder> {

    private Items mItems;
    private LayoutInflater mLayoutInflater;
    //context 一直传递到ItemViewProvider内部
    private Context mContext;

    //key: viewtype
    private SparseArray<ItemViewProvider> mViewProviders = new SparseArray<>(3);
    //key: viewtype
    private SparseArray<String> mDataType= new SparseArray<>(3);


    public SimpleAdapter(Context context) {
        this(context, new Items());
    }

    public SimpleAdapter(Context context, Items items) {
        this.mContext = context;
        this.mItems = items;
    }

    @NonNull
    @Override
    public SimpleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mLayoutInflater == null) {
            mLayoutInflater = LayoutInflater.from(mContext);
        }
        ItemViewProvider viewProvider = getItemViewProvider(viewType);
        viewProvider.mSimpleAdapter = this;
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
        if (!payloads.isEmpty()) {
            viewProvider.onBindViewHolder(holder, mItems.get(position), payloads);
        } else {
            viewProvider.onBindViewHolder(holder, mItems.get(position));
        }
    }

    @Override
    public int getItemViewType(int position) {

        Object entity = mItems.get(position);

        String typeName = entity.getClass().getName();

        List<Integer> itemViewProviderIndexes = findItemViewProviderAllViewType(typeName);

        if (itemViewProviderIndexes.size() == 0) {
            throw new RuntimeException(String.format(" %s ItemViewProvider is not register !!!", entity.getClass().getSimpleName()));

        }
        for (Integer index : itemViewProviderIndexes) {
            ItemViewProvider itemViewProvider = mViewProviders.valueAt(index);
            if (itemViewProvider != null && itemViewProvider.accept(entity, position)) {
                return index;
            }
        }
        throw new RuntimeException(String.format(" %s's ItemViewProvider a branch is not register !!!", entity.getClass().getSimpleName()));
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return this.mItems == null ? 0 : this.mItems.size();
    }


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


    /**
     * 根据 类名名称 找到支持的多个ItemViewProvider的viewtype数组
     * @param typeName entity类型对应的类名
     * @return viewtype数组
     */
    private List<Integer> findItemViewProviderAllViewType(String typeName) {
        List<Integer> indexes = new ArrayList<>();
        for (int i=0;i<mDataType.size();i++) {
            if (mDataType.get(i).equals(typeName)) {
                indexes.add(i);
            }
        }
        return indexes;
    }

    /**
     * 通过itemViewType找到 ItemViewProvider
     * @param itemViewType viewType
     * @return ItemViewProvider
     */
    private ItemViewProvider getItemViewProvider(int itemViewType) {
        return mViewProviders.get(itemViewType);
    }

    public SparseArray<ItemViewProvider> getAllItemViewProvider() {
        return mViewProviders;
    }


    /**
     * 添加新的数据 不带刷新
     * @param data
     */
    public void addNewData(Items data) {
        this.mItems.clear();
        this.mItems.addAll(data);
    }

    /**
     * 添加新的数据 带刷新
     * @param data
     */
    public void addNewDataWithNotify(Items data) {
        this.mItems.clear();
        this.mItems.addAll(data);
        notifyDataSetChanged();
    }

    /**
     * 追加更多的数据
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





}
