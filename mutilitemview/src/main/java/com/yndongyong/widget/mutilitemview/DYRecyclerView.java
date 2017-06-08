package com.yndongyong.widget.mutilitemview;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by dongzhiyong on 2017/6/6.
 */
public class DYRecyclerView extends RecyclerView {

    private boolean pullToRefreshEnable = true;
    private boolean loadingMoreEnable = false;

    private boolean isRefreshing = false;
    private boolean isLoading = false;


    //refresh header
    private RefreshHeaderEntry refreshHeaderEntry;//刷新头部的Entry
    private ItemViewProvider<?, ? extends RecyclerView.ViewHolder> refreshHeaderViewProvider;
    private IRefreshHeaderView refreshHeader;

    private ITypePool mHeaderViews = new MultiTypePool();//存放headerviews
    private ITypePool mFooterViews = new MultiTypePool();//存放footerviews

    private Items mHeaderEntrys = new Items();//存放Header的数据
    private Items mFooterEntrys = new Items();//存放footer 的数据

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

    public void setPullToRefreshEnable(boolean pullToRefreshEnable) {
        this.pullToRefreshEnable = pullToRefreshEnable;
        if (!pullToRefreshEnable ) {
            return;
        }
        if (refreshHeaderEntry == null) {
            refreshHeaderEntry = fakeRefreshHeaderEntry();
        }
        if (refreshHeaderViewProvider == null) {
            refreshHeaderViewProvider = new DefaultHeaderViewProvider();
            refreshHeader = (IRefreshHeaderView) refreshHeaderViewProvider;

        }
    }

    public void refreshComplete() {
        refreshHeader.refreshComplete();
    }

    public void setRefreshHeaderViewProvider(ItemViewProvider<?, ? extends ViewHolder> refreshHeaderViewProvider) {
        this.refreshHeaderViewProvider = refreshHeaderViewProvider;
        refreshHeader = (IRefreshHeaderView) refreshHeaderViewProvider;
        this.setPullToRefreshEnable(true);
        if (refreshHeaderEntry == null) {
            refreshHeaderEntry = fakeRefreshHeaderEntry();
        }
        if (originalAdapter != null) {
            mDataObserver.onChanged();
        }
    }

    private RefreshHeaderEntry fakeRefreshHeaderEntry() {
        return new RefreshHeaderEntry("正在刷新。。。", "下拉刷新","刷新完毕");
    }


    public void setLoadingMoreEnable(boolean loadingMoreEnable) {
        this.loadingMoreEnable = loadingMoreEnable;
        // TODO: 2017/6/8
    }



    /**
     * 为这个方法找一个执行时机
     */
    public void combinationTypePool() {
        this.multiTypeAdapter.getTypePool().clear();
        if (pullToRefreshEnable && refreshHeaderEntry != null || refreshHeaderViewProvider != null) {
            multiTypeAdapter.register(refreshHeaderEntry.getClass(), refreshHeaderViewProvider);
        }
        this.multiTypeAdapter.register(mHeaderViews);
        this.multiTypeAdapter.register(originalAdapter.getTypePool());
        this.multiTypeAdapter.register(mFooterViews);
        // TODO: 2017/6/8 register load more footer
        if (loadingMoreEnable) {

        }
    }

    /**
     * 根据Refresh header，headerviews ，footerview，数据集，组合真正的数据集。
     */
    public void combinationItems() {
        Items tempData = new Items();
        if (pullToRefreshEnable && refreshHeaderEntry != null || refreshHeaderViewProvider != null) {
            // TODO: 2017/6/8 添加refresh header的数据
            tempData.add(refreshHeaderEntry);
        }
        if (mHeaderEntrys.size() > 0) {
            tempData.addAll(mHeaderEntrys);
        }
        if (originalAdapter.getItemCount() > 0) {
            tempData.addAll(originalAdapter.getItems());
        }
        if (mFooterEntrys.size() > 0) {
            tempData.addAll(mFooterEntrys);
        }

        if (loadingMoreEnable) {
            // TODO: 2017/6/8 添加load more footer的数据
        }
        items.clear();
        items.addAll(tempData);
    }

    @Override
    public void setAdapter(Adapter adapter) {
        this.originalAdapter = (MultiTypeAdapter) adapter;
        combinationTypePool();
        combinationItems();
        super.setAdapter(multiTypeAdapter);
        adapter.registerAdapterDataObserver(mDataObserver);
        if (this.mHeaderEntrys.size() > 0 || this.originalAdapter.getItemCount() > 0) {
            mDataObserver.onChanged();
        }
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

    public void addHeaderView(Object object, ItemViewProvider<?, ? extends ViewHolder> itemViewProvider) {
        this.mHeaderViews.register(object.getClass(), itemViewProvider);
        this.mHeaderEntrys.add(object);
        combinationItems();
    }

    public void addFooterView(Object object, ItemViewProvider<?, ? extends ViewHolder> itemViewProvider) {
        this.mFooterViews.register(object.getClass(), itemViewProvider);
        this.mFooterEntrys.add(object);
        combinationItems();
    }

    private class DataObserver extends RecyclerView.AdapterDataObserver {
        @Override
        public void onChanged() {
            if (multiTypeAdapter != null) {
                combinationItems();
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

    private float mLastY = -1;
    private static final float DRAG_RATE = 3;

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mLastY = ev.getRawY();
                break;
            case MotionEvent.ACTION_MOVE:
                final float deltaY = ev.getRawY() - mLastY;
                mLastY = ev.getRawY();
                if (pullToRefreshEnable ) {
                    refreshHeader.onMove(deltaY / DRAG_RATE);
                    if (refreshHeader.getVisibleHeight() > 0 && refreshHeader.getState() < IRefreshHeaderView.STATE_REFRESHING) {
                        return false;
                    }
                }
                break;
            default:
                mLastY = -1; // reset
                if ( pullToRefreshEnable /*&& appbarState == AppBarStateChangeListener.State.EXPANDED*/) {
                    if (refreshHeader.releaseAction()) {
                       /* if (mLoadingListener != null) {
                            if (mEmptyView != null) {
                                mEmptyView.getLayoutParams().width = 0;
                                mEmptyView.getLayoutParams().height = 0;
                            }
                            mLoadingListener.onRefresh();
                        }*/
                        // TODO: 2017/6/8
                    }
                }
                break;
        }
        return super.onTouchEvent(ev);
    }
}
