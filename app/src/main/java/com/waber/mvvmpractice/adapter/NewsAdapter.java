package com.waber.mvvmpractice.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.waber.mvvmpractice.BR;
import com.waber.mvvmpractice.R;
import com.waber.mvvmpractice.base.BaseAdapter;
import com.waber.mvvmpractice.base.BaseViewHolder;
import com.waber.mvvmpractice.bean.SimpleNewsBean;
import com.waber.mvvmpractice.util.ToastUtils;

/**
 * Created by waber on 2018/6/1.
 */

public class NewsAdapter extends BaseAdapter<SimpleNewsBean,BaseViewHolder> {

    public NewsAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder onCreateVH(ViewGroup parent, int viewType) {
        ViewDataBinding dataBinding = DataBindingUtil.inflate(mLayoutInflater, R.layout.item_news,parent,false);
        return new BaseViewHolder(dataBinding);
    }

    @Override
    public void onBindVH(BaseViewHolder baseViewHolder, int position) {
        ViewDataBinding dataBinding = baseViewHolder.getmBinding();
        dataBinding.setVariable(BR.simpleNewsBean, mDataList.get(position));
        dataBinding.setVariable(BR.position, position);
        dataBinding.setVariable(BR.adapter, this);
        dataBinding.executePendingBindings(); //防止闪烁
    }

    public void onClickDianZan(SimpleNewsBean simpleNewsBean,int position){
        if (simpleNewsBean.isGoods.get()){
            simpleNewsBean.isGoods.set(false);
            ToastUtils.show(mContext, "取消点赞 position=" + position);
        }else {
            simpleNewsBean.isGoods.set(true);
            ToastUtils.show(mContext, "点赞成功 position=" + position);
        }
    }


}
