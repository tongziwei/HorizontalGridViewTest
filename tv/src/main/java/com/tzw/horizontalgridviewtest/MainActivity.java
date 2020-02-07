package com.tzw.horizontalgridviewtest;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.support.v17.leanback.widget.ArrayObjectAdapter;
import android.support.v17.leanback.widget.FocusHighlightHelper;
import android.support.v17.leanback.widget.HorizontalGridView;
import android.support.v17.leanback.widget.ItemBridgeAdapter;
import android.support.v17.leanback.widget.OnChildViewHolderSelectedListener;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends Activity {
    public static final String TAG="MainActivity";
    /**
     * 默认实现方式
     * 始终将重点项目保持在对齐位置。开发人员可以使用WINDOW_ALIGN_XXX和ITEM_ALIGN_XXX来定义焦                点项的对齐方式。
        在此模式下，当焦点返回视图时，将记住并恢复最后一个聚焦位置。
     */
    public final static int FOCUS_SCROLL_ALIGNED = 0;

    /**
     * 滚动以将焦点项目放在客户区域内。
     *
     */
    public final static int FOCUS_SCROLL_ITEM = 1;

    /**
     *  当聚焦到客户区域外的项目时，滚动一页项目。页面大小与RecyclerView的客户区大小相匹配。
     *
     *
     */
    public final static int FOCUS_SCROLL_PAGE = 2;

    private HorizontalGridView mHgv;
    private List<Integer> mDataList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    @SuppressLint("RestrictedApi")
    private void initViews() {
        mHgv= (HorizontalGridView) findViewById(R.id.hgv);
        //3行
        mHgv.setNumRows(3);
        //item纵向和横向的距离
        mHgv.setItemSpacing(20);
        //item的对齐方式
        mHgv.setGravity(Gravity.CENTER_VERTICAL);
        //设置
        mHgv.setOnChildViewHolderSelectedListener(new OnChildViewHolderSelectedListener() {
            @Override
            public void onChildViewHolderSelected(RecyclerView parent, RecyclerView.ViewHolder child, int position, int subposition) {
                super.onChildViewHolderSelected(parent, child, position, subposition);
                Log.d(TAG, "onChildViewHolderSelected() returned: " + position);
                //大部分情况下可以通过该方法获取到position

            }

            @Override
            public void onChildViewHolderSelectedAndPositioned(RecyclerView parent, RecyclerView.ViewHolder child, int position, int subposition) {
                super.onChildViewHolderSelectedAndPositioned(parent, child, position, subposition);
                Log.d(TAG, "onChildViewHolderSelectedAndPositioned() returned: " + position);
                //当通过setSelectedPosition()方法大幅移动列表时，该方法会回调，返回的是最终的真实的position（当set的值超出范围时...)
            }
        });
        HPresenter presenter=new HPresenter();
        //创建ObjectAdapter，用于提供数据，当有多种类型时，传入PresenterSelector
        ArrayObjectAdapter objectAdapter=new ArrayObjectAdapter(presenter);
        //初始化模拟数据
        initData();
        //添加数据
        objectAdapter.addAll(0,mDataList);
        //通过前面创建的objectAdapter创建ItemBridgeAdapter，完成数据的传递
        ItemBridgeAdapter bridgeAdapter=new ItemBridgeAdapter(objectAdapter);
        //将ItemBridgeAdapter传入HorizontalGridView
        mHgv.setAdapter(bridgeAdapter);
        mHgv.requestFocus();
        //mHgv.setFocusScrollStrategy(FOCUS_SCROLL_ITEM);
       // mHgv.setSelectedPosition(0);
        //设置上焦动画
        FocusHighlightHelper.setupHeaderItemFocusHighlight(bridgeAdapter);
    }



    private void initData(){
        if (mDataList==null){
            mDataList=new ArrayList<>();
            for (int i = 0; i <100 ; i++) {
                mDataList.add(i);
            }
        }
    }
}
