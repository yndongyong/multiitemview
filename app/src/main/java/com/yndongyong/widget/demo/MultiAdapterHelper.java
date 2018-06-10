package com.yndongyong.widget.demo;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;

import com.yndongyong.widget.multiitem.ItemViewProvider;
import com.yndongyong.widget.multiitem.Items;
import com.yndongyong.widget.multiitem.SimpleAdapter;

import static com.yndongyong.widget.demo.BaseItemDecoration.HORIZONTAL;
import static com.yndongyong.widget.demo.BaseItemDecoration.VERTICAL;

public class MultiAdapterHelper {

    private static final int SPAN_SIZE = 2;
    private Items mDatas;
//
//    public MultiAdapterHelper(Context mContext, Items datas) {
//        super(mContext, datas);
//        this.mDatas = datas;
//    }
    private SparseArray<ItemViewProvider> mItemViewProviders;
    private SimpleAdapter mSimpleAdapter;

    public MultiAdapterHelper(final Context context, RecyclerView recyclerView, Items datas) {
        GridSpanOffsetsItemDecoration gridSpanOffsetsItemDecoration = new GridSpanOffsetsItemDecoration(VERTICAL);
        gridSpanOffsetsItemDecoration.setHorizontalItemOffsets(context.getResources().getDimensionPixelOffset(R.dimen.dp_16));
//        gridSpanOffsetsItemDecoration.setVerticalItemOffsets(mContext.getResources().getDimensionPixelOffset(R.dimen.dp_16));
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
        this.mDatas = datas;
        this.mSimpleAdapter = new SimpleAdapter(context, datas);
        mSimpleAdapter.register(new NewsEntryStyle1ItemViewProvider());
        mSimpleAdapter.register(new NewsEntryStyle2ItemViewProvider());
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
        } else if (viewProvider instanceof NewsEntryStyle3ItemViewProvider) {
            return 1;
        }
        return SPAN_SIZE;
    }
}
