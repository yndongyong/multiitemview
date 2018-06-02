package com.yndongyong.widget.multiitem;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.annotation.IdRes;
import android.support.annotation.StringRes;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by dongzhiyong on 2017/6/14.
 */

public class SimpleViewHolder extends RecyclerView.ViewHolder {

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

    public SimpleViewHolder setText(@IdRes int viewId, @StringRes int resId) {
        TextView textView = getView(viewId);
        textView.setText(mContext.getString(resId));
        return this;
    }

    public SimpleViewHolder setTextColor(@IdRes int viewId, int color) {
        TextView textView = getView(viewId);
        textView.setTextColor(color);
        return this;
    }

    public SimpleViewHolder textSize(@IdRes int viewId, int sp) {
        TextView textView = getView(viewId);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, sp);
        return this;
    }

    public SimpleViewHolder textSize(@IdRes int viewId, int unit, int size) {
        TextView textView = getView(viewId);
        textView.setTextSize(unit, size);
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


    public SimpleViewHolder setImage(@IdRes int viewId, Drawable drawable) {
        ImageView image = getView(viewId);
        image.setImageDrawable(drawable);
        return this;
    }

    public SimpleViewHolder setBackgroundResource(@IdRes int viewId, @DrawableRes int resId) {
        getView(viewId).setBackgroundResource(resId);
        return this;
    }

    public SimpleViewHolder setBackground(@IdRes int viewId, Drawable drawable) {
        getView(viewId).setBackground(drawable);
        return this;
    }


    public SimpleViewHolder alpha(@IdRes int viewId, float alpha) {
        getView(viewId).setAlpha(alpha);
        return this;
    }


    public SimpleViewHolder setVisible(@IdRes int viewId, boolean visible) {
        getView(viewId).setVisibility(visible ? View.VISIBLE : View.GONE);
        return this;
    }

    public SimpleViewHolder setInvisible(@IdRes int viewId) {
        getView(viewId).setVisibility(View.INVISIBLE);
        return this;
    }

    public SimpleViewHolder setEnabled(@IdRes int viewId, boolean enable) {
        getView(viewId).setEnabled(enable);
        return this;
    }

    public SimpleViewHolder setOnClickListener(@IdRes int viewId, View.OnClickListener listener) {
        getView(viewId).setOnClickListener(listener);
        return this;
    }

    public SimpleViewHolder setOnClickListener(View.OnClickListener listener) {
        itemView.setOnClickListener(listener);
        return this;
    }

    public SimpleViewHolder setOnLongClickListener(@IdRes int viewId, View.OnLongClickListener onLongClickListener) {
        getView(viewId).setOnLongClickListener(onLongClickListener);
        return this;
    }

    public SimpleViewHolder setOnLongClickListener(View.OnLongClickListener onLongClickListener) {
        itemView.setOnLongClickListener(onLongClickListener);
        return this;
    }

}
