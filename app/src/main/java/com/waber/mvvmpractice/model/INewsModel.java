package com.waber.mvvmpractice.model;

import com.waber.mvvmpractice.base.BaseLoadListener;
import com.waber.mvvmpractice.bean.SimpleNewsBean;

/**
 * Created by waber on 2018/5/31.
 */

public interface INewsModel {

    /**
     * 获取新闻数据
     * @param page
     * @param loadListener
     */
    void loadNewsData(int page, BaseLoadListener<SimpleNewsBean> loadListener);
}

