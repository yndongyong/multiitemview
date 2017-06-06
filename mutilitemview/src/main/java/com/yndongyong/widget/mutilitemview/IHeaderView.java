package com.yndongyong.widget.mutilitemview;

/**
 * Created by dongzhiyong on 2017/6/5.
 */

public interface IHeaderView{

    void onMove(float delta);

    boolean releaseAction();

    void refreshComplete();
}

