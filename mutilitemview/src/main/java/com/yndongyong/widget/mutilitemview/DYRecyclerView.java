package com.yndongyong.widget.mutilitemview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.ViewGroup;

/**
 * Created by dongzhiyong on 2017/6/6.
 */

public class DYRecyclerView extends RecyclerView {

    private boolean pullToRefreshEnable = true;
    private boolean loadingMoreEnable = false;
    private boolean isRefreshing = false;
    private boolean isLoading = false;


    private final int VIEW_TYPE_REFRESH_HEADER = 10000;//刷新head的viewtype
    private final int VIEW_TYPE_LOADING_FOOTER = 20000;//加载更多的ViewType
    private final int BASE_HEADER_VIEW_INDEX = 10001; //haderview 的 基数

    private ITypePool mHeaderViews = new MultiTypePool();//存放headerviews
    private ITypePool mFooterViews = new MultiTypePool();//存放footerviews


    private Items items = new Items();

    private MultiTypeAdapter multiTypeAdapter = new MultiTypeAdapter(items);
    private MultiTypeAdapter originalAdapter;

    private final RecyclerView.AdapterDataObserver mDataObserver = new DataObserver();


    public DYRecyclerView(Context context) {
        this(context, null);
    }

    public DYRecyclerView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DYRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initialize();
    }

    private void initialize() {

    }

    /**
     * 为这个方法找一个执行时机
     */
    public void setupTypePool() {
        this.multiTypeAdapter.getTypePool().clear();
        //TODO register refresh header
        this.multiTypeAdapter.register(mHeaderViews);
        this.multiTypeAdapter.register(originalAdapter.getTypePool());
        this.multiTypeAdapter.register(mFooterViews);
    }

    @Override
    public void setAdapter(Adapter adapter) {
        this.originalAdapter = (MultiTypeAdapter) adapter;
        //TODO 添加头 添加footer
        this.items.addAll(originalAdapter.getItems());
        setupTypePool();
        super.setAdapter(multiTypeAdapter);
        adapter.registerAdapterDataObserver(mDataObserver);

    }

    @Override
    public Adapter getAdapter() {
        return this.originalAdapter;
    }

    public int getHeaderViewsCount() {
        return mHeaderViews.getCount();
    }

    public int getFooterViewsCount() {
        return mFooterViews.getCount();
    }

    public int getRealItemCount() {
        return originalAdapter != null ? originalAdapter.getItemCount() : 0;
    }

    public boolean isHearderViewPosition(int position) {
        return position < getHeaderViewsCount();
    }

    public boolean isFooterViewPosition(int position) {
        return position > getHeaderViewsCount() + getFooterViewsCount() + getRealItemCount();
    }

    public void addHeaderView(HeaderEntry object, ItemViewProvider<?, ? extends ViewHolder> itemViewProvider) {
        this.mHeaderViews.register(object.getClass(), itemViewProvider);
        this.items.add(object);
    }

    public void addFooterView(HeaderEntry object, ItemViewProvider<?, ? extends ViewHolder> itemViewProvider) {
        this.mFooterViews.register(object.getClass(), itemViewProvider);
        this.items.add(originalAdapter.getItemCount()-1,object);
    }

    private class DataObserver extends RecyclerView.AdapterDataObserver {
        @Override
        public void onChanged() {
            if (multiTypeAdapter != null) {
                multiTypeAdapter.notifyDataSetChanged();
            }
            /*if (multiTypeAdapter != null && mEmptyView != null) {
                int emptyCount = 1 + multiTypeAdapter.getHeadersCount();
                if (loadingMoreEnabled) {
                    emptyCount++;
                }
                if (mWrapAdapter.getItemCount() == emptyCount) {
                    mEmptyView.setVisibility(View.VISIBLE);
                    XRecyclerView.this.setVisibility(View.GONE);
                } else {

                    mEmptyView.setVisibility(View.GONE);
                    XRecyclerView.this.setVisibility(View.VISIBLE);
                }
            }*/
        }

        @Override
        public void onItemRangeInserted(int positionStart, int itemCount) {
            multiTypeAdapter.notifyItemRangeInserted(positionStart, itemCount);
        }

        @Override
        public void onItemRangeChanged(int positionStart, int itemCount) {
            multiTypeAdapter.notifyItemRangeChanged(positionStart, itemCount);
        }

        @Override
        public void onItemRangeChanged(int positionStart, int itemCount, Object payload) {
            multiTypeAdapter.notifyItemRangeChanged(positionStart, itemCount, payload);
        }

        @Override
        public void onItemRangeRemoved(int positionStart, int itemCount) {
            multiTypeAdapter.notifyItemRangeRemoved(positionStart, itemCount);
        }

        @Override
        public void onItemRangeMoved(int fromPosition, int toPosition, int itemCount) {
            multiTypeAdapter.notifyItemMoved(fromPosition, toPosition);
        }
    }

    class WrapAdapter extends MultiTypeAdapter {

        private MultiTypeAdapter origAdapter;

        public WrapAdapter(MultiTypeAdapter origAdapter) {
            this.origAdapter = origAdapter;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return super.onCreateViewHolder(parent, viewType);
        }

        @Override
        public int getItemViewType(int position) {
            if (isHearderViewPosition(position)) {
                return mHeaderViews.indexOfTypePool(items.get(position).getClass());
            }
            if (isFooterViewPosition(position)) {
                return mFooterViews.indexOfTypePool(items.get(position).getClass());
            }

            return origAdapter.getItemViewType(position);
        }
    }
}
