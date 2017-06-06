package com.yndongyong.widget.mutilitemview;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.SparseArray;

/**
 * Created by dongzhiyong on 2017/6/6.
 */

public class DYRecyclerView extends RecyclerView{

    private boolean pullToRefreshEnable = true;
    private boolean loadingMoreEnable = false;
    private boolean isRefreshing = false;
    private boolean isLoading = false;


    private final int VIEW_TYPE_REFRESH_HEADER = 10000;//刷新head的viewtype
    private final int VIEW_TYPE_LOADING_FOOTER = 20000;//加载更多的ViewType
    private final int BASE_HEADER_VIEW_INDEX = 10001; //haderview 的 基数

    private ITypePool mHeaderViews = new MultiTypePool();//存放headerviews
    private ITypePool mFooterViews = new MultiTypePool();//存放footerviews


    private MultiTypeAdapter multiTypeAdapter;


    public DYRecyclerView(Context context) {
        this(context,null);
    }

    public DYRecyclerView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public DYRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initialize();
    }

    private void initialize() {

    }

}
