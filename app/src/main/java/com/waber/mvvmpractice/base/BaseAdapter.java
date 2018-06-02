package com.waber.mvvmpractice.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Created by waber on 2018/5/28.
 */

public abstract class BaseAdapter<T,VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH>{

    public List<T> mDataList ; //数据源
    public Context mContext ;
    public LayoutInflater mLayoutInflater;

    public BaseAdapter(Context context){
        this.mContext = context ;
        this.mDataList = new ArrayList<>();
        mLayoutInflater = (LayoutInflater) mContext.getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        return onCreateVH(parent ,viewType);
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        onBindVH(holder,position);
    }

    /**
     * 创建 View Holder
     *
     * @param parent   parent
     * @param viewType item type
     * @return view holder
     */
    public abstract VH onCreateVH(ViewGroup parent, int viewType);

    /**
     * 绑定 View Holder
     *
     * @param vh       view holder
     * @param position position
     */
    public abstract void onBindVH(VH vh,int position);

    /**
     * 刷新数据
     *
     * @param data 数据源
     */
    public void onRefreshData(List<T> data){
        mDataList.clear();
        mDataList.addAll(data);
        notifyDataSetChanged();
    }

    /**
     * 加载更多
     *
     * @param data 加载的新数据
     */
    public void onLoadMoreData(List<T> data){
        mDataList.addAll(data);
        notifyDataSetChanged();
    }

}
