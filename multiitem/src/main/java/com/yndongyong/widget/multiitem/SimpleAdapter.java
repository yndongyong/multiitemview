package com.yndongyong.widget.multiitem;

import android.app.Activity;
import android.content.Context;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dongzhiyong on 2017/5/29.
 */
public class SimpleAdapter extends RecyclerView.Adapter<SimpleViewHolder> {

    private Items items;
    private ITypePool typePool;
    private LayoutInflater inflater;
    private Context mContext;

    private int position = -1;


    private SimpleAdapter(Context context) {
        this(context, new Items());
    }

    private SimpleAdapter(Context context, Items items) {
        this.mContext = context;
        this.items = items;
        this.typePool = new MultiTypePool();
    }

    @NonNull
    @Override
    public SimpleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (inflater == null) {
            inflater = LayoutInflater.from(mContext);
        }
        ItemViewProvider viewProvider = getItemViewProvider(this.position, viewType);
        viewProvider.simpleAdapter = this;
        viewProvider.context = mContext;
        return viewProvider.onCreateViewHolder(mContext, inflater, parent);
    }

    @Override
    public void onBindViewHolder(SimpleViewHolder holder, int position) {
        int itemViewType = holder.getItemViewType();
        ItemViewProvider viewProvider = getItemViewProvider(position, itemViewType);
        viewProvider.onBindViewHolder(holder, items.get(position));
    }


    @Override
    public void onBindViewHolder(SimpleViewHolder holder, int position, List<Object> payloads) {
        int itemViewType = holder.getItemViewType();
        ItemViewProvider viewProvider = getItemViewProvider(position, itemViewType);
        viewProvider.onBindViewHolder(holder, items.get(position), payloads);
    }


    @Override
    public int getItemCount() {
        return this.items == null ? 0 : this.items.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        this.position = position;
        Class<?> aClass = this.items.get(position).getClass();
        int indexOfCategory = this.typePool.indexOfCategorys(aClass);
        Converter converter = typePool.findConverterByClass(aClass);
        int indexOfProviders = converter.index(this.items.get(position));
        return indexOfCategory + indexOfProviders;
    }


    private ItemViewProvider getItemViewProvider(int position, int itemViewType) {
        int indexOfProviders = indexOfProviders(position);
        int indexOfClass = itemViewType - indexOfProviders;
        List<ItemViewProvider> providers = this.typePool.findViewProvidersByIndex(indexOfClass);
        return providers.get(indexOfProviders);
    }

    /**
     *
     * @param position
     * @return
     */
    private int indexOfProviders(int position) {
        Class<?> aClass = this.items.get(position).getClass();
        Converter convertor = typePool.findConverterByClass(aClass);
        int indexOfProviders = convertor.index(this.items.get(position));
        return indexOfProviders;
    }




   /* public void register(Class<?> clazz, ItemViewProvider<?> viewProvider) {
        this.typePool.register(clazz, viewProvider);
    }*/

    public void register(ITypePool pool) {
        this.typePool.register(pool);
    }


    /**
     * 添加新的数据 带刷新
     *
     * @param data
     */
    public void addNewDataWithNotify(Items data) {
        this.items.clear();
        this.items.addAll(data);
        notifyDataSetChanged();
    }

    /**
     * 追加更多的数据
     * @param data
     */
    public void addMoreData(Items data) {
        this.items.addAll(data);
    }
    /**
     * 追加更多的数据 带刷新
     *
     * @param data
     */
    public void addMoreDataWithNotify(Items data) {
        int size = this.items.size();
        this.items.addAll(data);
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
        Items oldList = new Items(items);
        final DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new SimpleDiff(oldList, newData, callBack));
        this.items.clear();
        this.items.addAll(newData);
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
        this.items.clear();
        this.items.addAll(data);
        return SimpleAdapter.this;
    }

    public SimpleAdapter attachToRecyclerView(RecyclerView recyclerView) {
        recyclerView.setAdapter(SimpleAdapter.this);
        return SimpleAdapter.this;
    }

    public  SimpleAdapter register(Class<?> clazz, ItemViewProvider viewProvider) {
        List<ItemViewProvider> providers = new ArrayList<>();
        providers.add(viewProvider);
        SimpleAdapter.this.typePool.register(clazz, providers, new DefaultConverter());
        return SimpleAdapter.this;
    }
    public SimpleAdapter register(Class<?> clazz, List<ItemViewProvider> viewProvider, Converter converter) {
        SimpleAdapter.this.typePool.register(clazz, viewProvider, converter);
        return SimpleAdapter.this;
    }
}