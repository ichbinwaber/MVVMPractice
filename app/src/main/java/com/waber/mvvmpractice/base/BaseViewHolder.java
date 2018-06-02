package com.waber.mvvmpractice.base;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;

/**
 * Created by waber on 2018/5/29.
 */

public class BaseViewHolder<B extends ViewDataBinding> extends RecyclerView.ViewHolder{

    /**
     * ViewDataBinding
     */
    private B mBinding;

    public BaseViewHolder(B binding) {
        super(binding.getRoot());
        mBinding = binding;
    }

    /**
     *
     * @return mBinding
     */
    public B getmBinding(){
        return mBinding;
    }
}
