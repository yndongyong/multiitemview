package com.yndongyong.widget.demo;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;

import com.yndongyong.adapter.ItemViewProvider;
import com.yndongyong.adapter.Items;
import com.yndongyong.adapter.SimpleAdapter;
import com.yndongyong.widget.demo.viewproviders.NewsEntryStyle1ItemViewProvider;
import com.yndongyong.widget.demo.viewproviders.NewsEntryStyle2ItemViewProvider;


/**
 * recyclerview VERTICAL gridlayoutmanager 显示一行一列，一行多列的UI样式
 * 控制recyclerview 的cell spanCount 按 SPAN_SIZE 显示
 */
public class MultiAdapterHelper {

    private static final int SPAN_SIZE = 3;

    private SparseArray<ItemViewProvider> mItemViewProviders;
    private SimpleAdapter mSimpleAdapter;

    public MultiAdapterHelper(final Context context, RecyclerView recyclerView, Items datas) {
        TwoWayGridSpanOffsetsItemDecoration gridSpanOffsetsItemDecoration = new TwoWayGridSpanOffsetsItemDecoration();
//        GridSpanOffsetsItemDecoration gridSpanOffsetsItemDecoration = new GridSpanOffsetsItemDecoration(BaseItemDecoration.VERTICAL);
        gridSpanOffsetsItemDecoration.setHorizontalItemOffsets(context.getResources().getDimensionPixelOffset(R.dimen.dp_2));
        gridSpanOffsetsItemDecoration.setVerticalItemOffsets(context.getResources().getDimensionPixelOffset(R.dimen.dp_2));
        recyclerView.addItemDecoration(gridSpanOffsetsItemDecoration);
        GridLayoutManager gridLayoutManager =
                new GridLayoutManager(context, SPAN_SIZE, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(gridLayoutManager);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return caculateSpanSize(position);
            }
        });
        this.mSimpleAdapter = new SimpleAdapter(context, datas);
        int style1ViewType = mSimpleAdapter.register(new NewsEntryStyle1ItemViewProvider());
        int style2ViewType = mSimpleAdapter.register(new NewsEntryStyle2ItemViewProvider());


       /* gridSpanOffsetsItemDecoration.registerTypeOffsets(style2ViewType, new GridOffsetsItemDecoration.IOffsetsCreator() {
            @Override
            public int createVertical(RecyclerView parent, int adapterPosition) {
                return context.getResources().getDimensionPixelOffset(R.dimen.dp_16);
            }

            @Override
            public int createHorizontal(RecyclerView parent, int adapterPosition) {
                return context.getResources().getDimensionPixelOffset(R.dimen.dp_16);
            }
        });*/
//        mSimpleAdapter.register(new NewsEntryStyle3ItemViewProvider());

//        gridSpanOffsetsItemDecoration.registerTypeOffsets(0, new GridOffsetsItemDecoration.IOffsetsCreator() {
//            @Override
//            public int createVertical(RecyclerView parent, int adapterPosition) {
//                return 0;
//            }
//
//            @Override
//            public int createHorizontal(RecyclerView parent, int adapterPosition) {
//                return mContext.getResources().getDimensionPixelOffset(R.dimen.dp_16);
//            }
//        });
        recyclerView.setAdapter(mSimpleAdapter);

        this.mItemViewProviders = mSimpleAdapter.getAllItemViewProvider();

    }

    private int caculateSpanSize(int position) {
        int itemViewType = mSimpleAdapter.getItemViewType(position);
        ItemViewProvider viewProvider = mItemViewProviders.get(itemViewType);
        if (viewProvider instanceof NewsEntryStyle2ItemViewProvider) {
            return 1;
        }
        return SPAN_SIZE;
    }
}
