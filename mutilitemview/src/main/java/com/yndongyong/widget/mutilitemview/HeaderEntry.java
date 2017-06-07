package com.yndongyong.widget.mutilitemview;

/**
 * Created by dongzhiyong on 2017/6/5.
 */

public class HeaderEntry {
    private String refreshTips;
    private String normalTips;

    public HeaderEntry(String refreshTips, String normalTips) {
        this.refreshTips = refreshTips;
        this.normalTips = normalTips;
    }

    public String getRefreshTips() {
        return refreshTips;
    }

    public void setRefreshTips(String refreshTips) {
        this.refreshTips = refreshTips;
    }

    public String getNormalTips() {
        return normalTips;
    }

    public void setNormalTips(String normalTips) {
        this.normalTips = normalTips;
    }
}
