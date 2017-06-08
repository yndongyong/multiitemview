package com.yndongyong.widget.mutilitemview;

/**
 * Created by dongzhiyong on 2017/6/5.
 */

public class RefreshHeaderEntry {

    private String refreshTips;
    private String normalTips;
    private String refershDoneTips;

    public RefreshHeaderEntry(String refreshTips, String normalTips, String refershDoneTips) {
        this.refreshTips = refreshTips;
        this.normalTips = normalTips;
        this.refershDoneTips = refershDoneTips;
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

    public String getRefershDoneTips() {
        return refershDoneTips;
    }

    public void setRefershDoneTips(String refershDoneTips) {
        this.refershDoneTips = refershDoneTips;
    }

    public void setNormalTips(String normalTips) {
        this.normalTips = normalTips;
    }
}
