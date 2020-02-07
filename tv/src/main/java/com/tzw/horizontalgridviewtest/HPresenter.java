package com.tzw.horizontalgridviewtest;

import android.support.v17.leanback.widget.Presenter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class HPresenter extends Presenter {
    /**
     * 创建ViewHolder，作用同RecyclerView$Adapter的onCreateViewHolder
     * @param viewGroup
     * @return
     */
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup) {
        View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_h, viewGroup, false);
        return new ViewHolder(inflate);
    }

    /**
     * 同RecyclerView$Adapter的onBindViewHolder，但是解耦了position
     * @param viewHolder
     * @param o
     */
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, Object o) {
        if (o instanceof Integer){
            ((TextView)viewHolder.view.findViewById(R.id.tv_index)).setText(o.toString());
        }
    }
    @Override
    public void onUnbindViewHolder(ViewHolder viewHolder) {
        //解绑时释放资源
    }

}
