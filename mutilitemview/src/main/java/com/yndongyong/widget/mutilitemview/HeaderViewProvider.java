package com.yndongyong.widget.mutilitemview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * Created by dongzhiyong on 2017/6/5.
 */

public class HeaderViewProvider  extends ItemViewProvider<HeaderEntry,HeaderViewProvider.HeaderViewHolder>
implements IHeaderView{


    private int currentPosition;

    @Override
    public HeaderViewHolder onCreateViewHolder(LayoutInflater inflater, ViewGroup parent) {
        return new HeaderViewHolder(inflater.inflate(R.layout.item_list_header, parent, false));
    }

    @Override
    public void onBindViewHolder(HeaderViewHolder holder, HeaderEntry entity) {
        currentPosition = holder.getAdapterPosition();
        holder.tv_header_tips.setText(entity.getRefreshTips());
    }

    @Override
    public void onMove(float delta) {

    }

    @Override
    public boolean releaseAction() {
        return false;
    }

    @Override
    public void refreshComplete() {

    }


    class HeaderViewHolder extends RecyclerView.ViewHolder {
        private ProgressBar pb_progress;
        private TextView tv_header_tips;
        public HeaderViewHolder(View itemView) {
            super(itemView);
            this.pb_progress = (ProgressBar) itemView.findViewById(R.id.pb_progress);
            this.tv_header_tips = (TextView) itemView.findViewById(R.id.tv_header_tips);
        }

    }
}

