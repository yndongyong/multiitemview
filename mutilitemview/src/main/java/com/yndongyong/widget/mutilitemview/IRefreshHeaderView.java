package com.yndongyong.widget.mutilitemview;

/**
 * Created by dongzhiyong on 2017/6/5.
 */

public interface IRefreshHeaderView {

    int STATE_NORMAL = 0;
    int STATE_RELEASE_TO_REFRESH = 1;
    int STATE_REFRESHING = 2;
    int STATE_DONE = 3;

    void onMove(float delta);

    boolean releaseAction();

    void refreshComplete();

    /**
     * 得到view的可视高度 ，view初始的时候height被设置为0
     */
    int getVisibleHeight();

    /**
     * 得到view的测量高度
     * @return
     */
    int getMeasuredHeight();

    int getState();
}

