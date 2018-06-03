package com.waber.mvvmpractice.view;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.waber.mvvmpractice.R;
import com.waber.mvvmpractice.adapter.NewsAdapter;
import com.waber.mvvmpractice.databinding.ActivityMainBinding;
import com.waber.mvvmpractice.helper.DialogHelper;
import com.waber.mvvmpractice.util.ToastUtils;
import com.waber.mvvmpractice.viewmodel.NewsVM;

import static com.waber.mvvmpractice.constant.MainConstant.LoadData.FIRST_LOAD;

public class MainActivity extends AppCompatActivity implements INewsView,XRecyclerView.LoadingListener {

    private ActivityMainBinding mBinding;
    private Context mContext;
    private NewsAdapter mNewAdapter;
    private NewsVM mNewsVM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        mContext = this;
        initRecycleView();
        mNewsVM = new NewsVM(this,mNewAdapter);
    }

    /**
     * 初始化RecycleV
     */
    private void initRecycleView(){
        mBinding.newsRv.setRefreshProgressStyle(ProgressStyle.BallClipRotate); //设置下拉刷新的样式
        mBinding.newsRv.setLoadingMoreProgressStyle(ProgressStyle.BallClipRotate); //设置上拉样式
        mBinding.newsRv.setArrowImageView(R.mipmap.pull_down_arrow);
        mBinding.newsRv.setLoadingListener(this);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mBinding.newsRv.setLayoutManager(layoutManager);
        mNewAdapter = new NewsAdapter(this);
        mBinding.newsRv.setAdapter(mNewAdapter);
    }

    @Override
    public void onRefresh() {
        //下拉刷新
        mNewsVM.loadRefreshData();

    }

    @Override
    public void onLoadMore() {
        //上拉加载
        mNewsVM.loadMoreData();

    }

    @Override
    public void loadStart(int loadType) {
        if (loadType == FIRST_LOAD){
            DialogHelper.getInstance().show(mContext,"加载中...");
        }

    }

    @Override
    public void loadComplete() {
        DialogHelper.getInstance().close();
        mBinding.newsRv.loadMoreComplete(); //结束加载
        mBinding.newsRv.refreshComplete();  //结束刷新

    }

    @Override
    public void loadFailure(String message) {
        DialogHelper.getInstance().close();
        mBinding.newsRv.loadMoreComplete(); //结束加载
        mBinding.newsRv.refreshComplete();  //结束刷新
        ToastUtils.show(mContext,message);

    }
}
