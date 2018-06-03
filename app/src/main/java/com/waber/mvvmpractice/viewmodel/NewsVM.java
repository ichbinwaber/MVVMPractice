package com.waber.mvvmpractice.viewmodel;

import com.waber.mvvmpractice.adapter.NewsAdapter;
import com.waber.mvvmpractice.base.BaseLoadListener;
import com.waber.mvvmpractice.bean.SimpleNewsBean;
import com.waber.mvvmpractice.constant.MainConstant;
import com.waber.mvvmpractice.model.INewsModel;
import com.waber.mvvmpractice.model.NewsModelImpl;
import com.waber.mvvmpractice.view.INewsView;

import java.util.List;


public class NewsVM implements BaseLoadListener<SimpleNewsBean> {

    private static final String TAG = "NewsVM";

    private NewsAdapter mNewsAdapter;
    private INewsModel mINewsModel;
    private INewsView mNewsView;
    private int currPage = 1;  //当前页数
    private int loadType ; //加载的数据类型

    public NewsVM(INewsView mNewsView,NewsAdapter mNewsAdapter){
        this.mNewsAdapter = mNewsAdapter ;
        this.mNewsView = mNewsView;
        mINewsModel = new NewsModelImpl();
        getNewData();
    }

    /**
     * 第一次获得新闻数据
     */
    public void getNewData(){
        loadType = MainConstant.LoadData.FIRST_LOAD;
        mINewsModel.loadNewsData(currPage,this);
    }

    /**
     * 第一次获得下拉数据
     */
    public void loadRefreshData(){
        loadType = MainConstant.LoadData.REFRESH;
        currPage = 1;
        mINewsModel.loadNewsData(currPage,this);
    }

    /**
     * 获取上拉加载更多的数据
     */
    public void loadMoreData(){
        loadType = MainConstant.LoadData.LOAD_MORE;
        currPage++;
        mINewsModel.loadNewsData(currPage,this);
    }

    @Override
    public void loadSuccess(List<SimpleNewsBean> mList) {
        if (currPage>1){
            //上拉加载数据
           mNewsAdapter.onLoadMoreData(mList);
        }else {
            //第一次加载或者下拉刷新的数据
            mNewsAdapter.onRefreshData(mList);
        }
    }

    @Override
    public void loadFailure(String message) {
        //加载失败后的提示
        if (currPage>1){
            //加载失败后要回到之前的页数
            currPage--;
        }
        mNewsView.loadFailure(message);
    }

    @Override
    public void loadStart() {
        mNewsView.loadStart(loadType);
    }

    @Override
    public void loadComplete() {
        mNewsView.loadComplete();
    }
}
