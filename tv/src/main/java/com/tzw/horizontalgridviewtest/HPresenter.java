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
            final TextView textView = ((TextView)viewHolder.view.findViewById(R.id.tv_index));
            textView.setText(o.toString());

            textView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus){
                    textView.animate().scaleX(1.2f).scaleY(1.2f).setDuration(300).start();
                }else {
                    textView.animate().scaleX(1.0f).scaleY(1.0f).setDuration(300).start();
                }
            }
           });
        }
    }
    @Override
    public void onUnbindViewHolder(ViewHolder viewHolder) {
        //解绑时释放资源
    }



}
