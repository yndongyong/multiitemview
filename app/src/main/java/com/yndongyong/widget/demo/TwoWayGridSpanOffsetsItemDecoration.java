package com.yndongyong.widget.demo;

import android.graphics.Rect;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;

/**
 * GridLayoutManager显示一行一列，一行多列的情况下控制每个ViewType对应的offset
 */
public class TwoWayGridSpanOffsetsItemDecoration extends RecyclerView.ItemDecoration {

    private final SparseArray<IOffsetsCreator> mTypeOffsetsFactories = new SparseArray<>();
    private SparseIntArray mSpanSizeRecorder = new SparseIntArray();


    private int mVerticalItemOffsets;
    private int mHorizontalItemOffsets;

    public void setVerticalItemOffsets(int verticalItemOffsets) {
        this.mVerticalItemOffsets = verticalItemOffsets;
    }

    public void setHorizontalItemOffsets(int horizontalItemOffsets) {
        this.mHorizontalItemOffsets = horizontalItemOffsets;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int spanCount = getSpanCount(parent);
        int childCount = parent.getAdapter().getItemCount();

        int adapterPosition = parent.getChildAdapterPosition(view);
        int spanSize = getSpanSize(parent, adapterPosition);
        mSpanSizeRecorder.put(adapterPosition, spanSize);

        outRect.set(getHorizontalOffsets(parent, view) / 2, 0, getHorizontalOffsets(parent, view) / 2, getVerticalOffsets(parent, view));

        if (isFirstColumn(parent, adapterPosition, spanCount, spanSize, childCount)) {
            outRect.left = getHorizontalOffsets(parent, view);
        }

        if (isLastColumn(parent, adapterPosition, spanCount, spanSize, childCount)) {
            outRect.right = getHorizontalOffsets(parent, view);
        }

    }

    /**
     * 是不是第一列
     *
     * @param parent
     * @param position   item position
     * @param spanCount  GridLayoutManager spanCount
     * @param spanSize   item spanSize
     * @param childCount total child count
     * @return true ：第一列
     */
    protected boolean isFirstColumn(RecyclerView parent, int position, int spanCount, int spanSize, int childCount) {

        //一行中独占一列
        if (spanSize == spanCount) {
            return true;
        }
        int nearFullSpanPosition = findFullSpanCountByPosition(position,spanCount);
        return ((position - nearFullSpanPosition) % spanCount == 1);
    }

    private int findFullSpanCountByPosition(int position,int spanCount) {
        if (mSpanSizeRecorder == null) {
            return 0;
        }
        int size = mSpanSizeRecorder.size();
        for (int i = size - 1 -1 ; i >= 0; i--) {//-1 exclude it
            int target = mSpanSizeRecorder.keyAt(i);
            if ((target < position) && mSpanSizeRecorder.valueAt(i) == spanCount) { //look up last full span position and get the full span count
                return target;
            }
        }
        return 0; // if non return zero
    }

    /**
     * 是不是第最后一列
     *
     * @param parent
     * @param position   item position
     * @param spanCount  GridLayoutManager spanCount
     * @param spanSize   item spanSize
     * @param childCount total child count
     * @return true ：最后一列
     */
    protected boolean isLastColumn(RecyclerView parent, int position, int spanCount, int spanSize, int childCount) {
        //一行中独占一列
        if (spanSize == spanCount) {
            return true;
        }
        int nearFullSpanPosition = findFullSpanCountByPosition(position,spanCount);
        return ((position - nearFullSpanPosition) % spanCount == 0);
    }

    protected int getSpanCount(RecyclerView parent) {
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();

        if (layoutManager instanceof GridLayoutManager) {
            return ((GridLayoutManager) layoutManager).getSpanCount();
        } else {
            throw new UnsupportedOperationException("the +" + getClass().getSimpleName() + " can only be used in " +
                    "the RecyclerView which use a VERTICAL GridLayoutManager ");
        }
    }

    protected int getSpanSize(RecyclerView parent, int adapterPosition) {
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            GridLayoutManager gridLayoutManager = (GridLayoutManager) layoutManager;
            if (gridLayoutManager.getOrientation() != GridLayoutManager.VERTICAL) {
                throw new UnsupportedOperationException("the +" + getClass().getSimpleName() + " can only be used in " +
                        "the RecyclerView which use a VERTICAL GridLayoutManager");
            }
            GridLayoutManager.SpanSizeLookup spanSizeLookup = gridLayoutManager.getSpanSizeLookup();
            return spanSizeLookup.getSpanSize(adapterPosition);
        } else {
            throw new UnsupportedOperationException("the +" + getClass().getSimpleName() + " can only be used in " +
                    "the RecyclerView which use a VERTICAL GridLayoutManager");
        }
    }

    protected int getHorizontalOffsets(RecyclerView parent, View view) {
        if (mTypeOffsetsFactories.size() == 0) {
            return mHorizontalItemOffsets;
        }

        final int adapterPosition = parent.getChildAdapterPosition(view);
        final int itemType = parent.getAdapter().getItemViewType(adapterPosition);
        final IOffsetsCreator offsetsCreator = mTypeOffsetsFactories.get(itemType);

        if (offsetsCreator != null) {
            return offsetsCreator.createHorizontal(parent, adapterPosition);
        }

        return mHorizontalItemOffsets;
    }

    protected int getVerticalOffsets(RecyclerView parent, View view) {
        if (mTypeOffsetsFactories.size() == 0) {
            return mVerticalItemOffsets;
        }

        final int adapterPosition = parent.getChildAdapterPosition(view);
        final int itemType = parent.getAdapter().getItemViewType(adapterPosition);
        final IOffsetsCreator offsetsCreator = mTypeOffsetsFactories.get(itemType);

        if (offsetsCreator != null) {
            return offsetsCreator.createVertical(parent, adapterPosition);
        }

        return mVerticalItemOffsets;
    }

    public void registerViewTypeOffsets(int itemType, IOffsetsCreator offsetsCreator) {
        mTypeOffsetsFactories.put(itemType, offsetsCreator);
    }

    public interface IOffsetsCreator {
        int createVertical(RecyclerView parent, int adapterPosition);

        int createHorizontal(RecyclerView parent, int adapterPosition);
    }
}
