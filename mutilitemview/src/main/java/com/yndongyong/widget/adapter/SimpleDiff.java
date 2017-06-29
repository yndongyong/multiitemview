package com.yndongyong.widget.adapter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;

import java.util.List;

/**
 * Created by dongzhiyong on 2017/6/29.
 */

public class SimpleDiff extends  DiffUtil.Callback {

    private List<?> oldList;
    private List<?> newList;
    private CallBack callBack;

    public SimpleDiff(List<?> oldList, List<?> newList, CallBack callBack) {
        this.oldList = oldList;
        this.newList = newList;
        this.callBack = callBack;
    }

    @Override
    public int getOldListSize() {
        return oldList==null?0:oldList.size();
    }

    @Override
    public int getNewListSize() {
        return newList==null?0:newList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        if (oldList == null || newList == null) {
            return false;
        }
        return callBack.areItemsTheSame(oldList.get(oldItemPosition),newList.get(newItemPosition));
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return callBack.areContentsTheSame(oldList.get(oldItemPosition),newList.get(newItemPosition));
    }

    @Nullable
    @Override
    public Object getChangePayload(int oldItemPosition, int newItemPosition) {
        return callBack.getChangePayload(oldList.get(oldItemPosition),newList.get(newItemPosition));
    }

    public interface CallBack<T> {

        boolean areItemsTheSame(T oldItem, T newItem);

        boolean areContentsTheSame(T oldItem, T newItem);

        Bundle getChangePayload(T oldItem, T newItem);
    }

}
