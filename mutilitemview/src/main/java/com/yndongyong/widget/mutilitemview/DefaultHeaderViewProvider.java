package com.yndongyong.widget.mutilitemview;

import android.animation.ValueAnimator;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Date;

/**
 * Created by dongzhiyong on 2017/6/5.
 */

public class DefaultHeaderViewProvider extends ItemViewProvider<RefreshHeaderEntry, DefaultHeaderViewProvider.HeaderViewHolder>
        implements IRefreshHeaderView {


    private int measureHeight;
    private View rootView;

    private Animation mRotateUpAnim;
    private Animation mRotateDownAnim;

    private int mState = IRefreshHeaderView.STATE_NORMAL;

    private int currentPosition;

    @Override
    public HeaderViewHolder onCreateViewHolder(LayoutInflater inflater, final ViewGroup parent) {
        rootView = inflater.inflate(R.layout.item_list_default_header, parent, false);
        rootView.post(new Runnable() {
            @Override
            public void run() {
                rootView.measure(
                        View.MeasureSpec.makeMeasureSpec(parent.getWidth(), View.MeasureSpec.EXACTLY),
                        View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
                measureHeight = rootView.getMeasuredHeight();
            }
        });
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
//        lp.setMargins(0, 0, 0, 0);
        lp.height = 0;
        rootView.setLayoutParams(lp);
//        rootView.setPadding(0, 0, 0, 0);
        return new HeaderViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(HeaderViewHolder holder, RefreshHeaderEntry entity) {
        holder.tv_header_tips.setText(entity.getRefreshTips());
        currentPosition = holder.getAdapterPosition();



    }

    @Override
    public void onMove(float delta) {
        if (getVisibleHeight() > 0 || delta > 0) {
            setVisibleHeight((int) delta + getVisibleHeight());
            if (getVisibleHeight() > getMeasuredHeight()) {
                setState(STATE_RELEASE_TO_REFRESH);
            } else {
                setState(STATE_NORMAL);
            }
        }
    }

    @Override
    public boolean releaseAction() {
        boolean isOnRefresh = false;
        int height = getVisibleHeight();
        if (height == 0) // not visible.
            isOnRefresh = false;

        if(getVisibleHeight() > getMeasuredHeight() &&  mState < STATE_REFRESHING){
            setState(STATE_REFRESHING);
            isOnRefresh = true;
        }
        // refreshing and header isn't shown fully. do nothing.
        if (mState == STATE_REFRESHING && height <=  getMeasuredHeight()) {
            //return;
        }
        if (mState != STATE_REFRESHING) {
            smoothScrollTo(0);
        }

        if (mState == STATE_REFRESHING) {
            int destHeight = getMeasuredHeight();
            smoothScrollTo(destHeight);
        }

        return isOnRefresh;
    }

    @Override
    public void refreshComplete() {
        setState(STATE_DONE);
        new Handler().postDelayed(new Runnable(){
            public void run() {
                reset();
            }
        }, 200);
    }

    public void reset() {
        smoothScrollTo(0);
        new Handler().postDelayed(new Runnable() {
            public void run() {
                setState(STATE_NORMAL);
            }
        }, 500);
    }

    private void smoothScrollTo(int destHeight) {
        ValueAnimator animator = ValueAnimator.ofInt(getVisibleHeight(), destHeight);
        animator.setDuration(300).start();
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                setVisibleHeight((int) animation.getAnimatedValue());
            }
        });
        animator.start();
    }

    public void setVisibleHeight(int height) {
        if (height < 0) height = 0;
        RecyclerView.LayoutParams lp = (RecyclerView.LayoutParams) rootView.getLayoutParams();
        lp.height = height;
        rootView.setLayoutParams(lp);
    }

    public int getVisibleHeight() {
        RecyclerView.LayoutParams lp = (RecyclerView.LayoutParams) rootView.getLayoutParams();
        return lp.height;
    }

    @Override
    public int getMeasuredHeight() {
        return measureHeight;
    }

    public void setState(int state) {
        if (state == mState) return;
        mState = state;
        if (mState == STATE_REFRESHING) {    // 显示进度
            holder.iv_arrow.clearAnimation();
            holder.iv_arrow.setVisibility(View.INVISIBLE);
            holder.iv_loading.setVisibility(View.VISIBLE);
            smoothScrollTo(getMeasuredHeight());
        } else if (mState == STATE_DONE) {
            holder.iv_arrow.setVisibility(View.INVISIBLE);
            holder.iv_loading.setVisibility(View.INVISIBLE);
        } else {    // 显示箭头图片
            holder.iv_arrow.setVisibility(View.VISIBLE);
            holder.iv_loading.setVisibility(View.INVISIBLE);
        }

        switch (mState) {
            case STATE_NORMAL:
                if (mState == STATE_RELEASE_TO_REFRESH) {
                    holder.iv_arrow.startAnimation(mRotateDownAnim);
                }
                if (mState == STATE_REFRESHING) {
                    holder.iv_arrow.clearAnimation();
                }
                holder.tv_header_tips.setText(entity.getNormalTips());
                break;
            case STATE_RELEASE_TO_REFRESH:
                if (mState != STATE_RELEASE_TO_REFRESH) {
                    holder.iv_arrow.clearAnimation();
                    holder.iv_arrow.startAnimation(mRotateUpAnim);
                    holder.tv_header_tips.setText(entity.getRefreshTips());
                }
                break;
            case STATE_REFRESHING:
                holder.tv_header_tips.setText(entity.getRefreshTips());
                break;
            case STATE_DONE:
                holder.tv_header_tips.setText(entity.getNormalTips());
                break;
            default:
        }
    }

    @Override
    public int getState() {
        return mState;
    }

    class HeaderViewHolder extends RecyclerView.ViewHolder {
        private ImageView iv_loading;
        private ImageView iv_arrow;
        private TextView tv_header_tips;

        public HeaderViewHolder(View itemView) {
            super(itemView);
            this.iv_loading = (ImageView) itemView.findViewById(R.id.iv_loading);
            this.iv_arrow = (ImageView) itemView.findViewById(R.id.iv_arrow);
            this.tv_header_tips = (TextView) itemView.findViewById(R.id.tv_header_tips);
        }

    }
}

