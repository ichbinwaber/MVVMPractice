package com.waber.mvvmpractice.model;

import android.util.Log;

import com.waber.mvvmpractice.base.BaseLoadListener;
import com.waber.mvvmpractice.bean.NewsBean;
import com.waber.mvvmpractice.bean.SimpleNewsBean;
import com.waber.mvvmpractice.http.HttpUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Handler;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by waber on 2018/5/31.
 */

public class NewsModelImpl implements INewsModel{

    private static final String TAG = "NewsModelImpl";
    List<SimpleNewsBean> simpleNewsBeanList = new ArrayList<SimpleNewsBean>();

    @Override
    public void loadNewsData(final int page, final BaseLoadListener<SimpleNewsBean> loadListener) {

        HttpUtils.getNewsData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<NewsBean>() {
                    @Override
                    public void onNext(NewsBean newsBean) {
                        Log.i(TAG, "onNext: ");
                        List<NewsBean.OtherBean> otherBeanList = newsBean.getOthers();
                        simpleNewsBeanList.clear();
                        if (otherBeanList!=null&&otherBeanList.size()>0){
                            for (NewsBean.OtherBean otherBean:otherBeanList) {
                                String thumbnail = otherBean.getThumbnail();
                                String name = otherBean.getName();
                                String description = otherBean.getDescription();
                                Log.i(TAG, "thumbnail:---->" + thumbnail);
                                Log.i(TAG, "name:---->" + name);
                                Log.i(TAG, "description:---->" + description);

                                //构造Adapter 所需的数据源
                                SimpleNewsBean simpleNewsBean = new SimpleNewsBean();
                                simpleNewsBean.thumbnail.set(thumbnail);
                                simpleNewsBean.name.set(name);
                                simpleNewsBean.description.set(description);
                                simpleNewsBeanList.add(simpleNewsBean);

                                if (page > 1) {
                                    //这个接口暂时没有分页的数据，这里为了模拟分页，通过取第1条数据作为分页的数据
                                    break;
                                }
                            }
                        }
                    }

                    @Override
                    protected void onStart() {
                        super.onStart();
                        Log.i(TAG, "onStart: ");
                        loadListener.loadStart();
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        Log.i(TAG, "onError: " + throwable.getMessage());
                        loadListener.loadFailure(throwable.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Log.i(TAG, "onComplete: ");
                        new android.os.Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                loadListener.loadSuccess(simpleNewsBeanList);
                                loadListener.loadComplete();
                            }
                        }, 2000);
                    }
                });
    }
}
