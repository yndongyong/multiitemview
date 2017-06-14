package com.yndongyong.widget.multiitemview;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.DrawableRes;
import android.support.annotation.IdRes;
import android.support.annotation.StringRes;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by dongzhiyong on 2017/6/14.
 */

public class SimpleViewHolder extends RecyclerView.ViewHolder{

    private SparseArray<View> viewMap;
    private Context mContext;
    public View itemView;

    public SimpleViewHolder(View itemView) {
        super(itemView);
        this.itemView = itemView;
        viewMap = new SparseArray<>();
    }

    public SimpleViewHolder(Context context, View itemView) {
        this(itemView);
        this.mContext = context;
    }

    public final <T extends View> T getView(int id) {
        View view = viewMap.get(id);
        if (view == null) {
            view = itemView.findViewById(id);
            viewMap.put(id, view);
        }
        return (T) view;
    }

    public Context getContext() {
        return mContext;
    }

    public SimpleViewHolder setText(@IdRes int viewId, CharSequence charSequence) {
        TextView textView = getView(viewId);
        textView.setText(charSequence);
        return this;
    }

    public SimpleViewHolder setText(int viewId, @StringRes int resId) {
        TextView textView = getView(viewId);
        textView.setText(mContext.getString(resId));
        return this;
    }

    public SimpleViewHolder setImageResource(@IdRes int viewId, @DrawableRes int resId) {
        ImageView image = getView(viewId);
        image.setImageResource(resId);
        return this;
    }

    public SimpleViewHolder setImage(@IdRes int viewId, Bitmap bitmap) {
        ImageView image = getView(viewId);
        image.setImageBitmap(bitmap);
        return this;
    }
}
