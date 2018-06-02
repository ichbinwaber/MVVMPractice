package com.waber.mvvmpractice.base;

import java.util.List;

/**
 *
 * Created by waber on 2018/5/28.
 */

public interface BaseLoadListener<T> {

    /**
     * 加载数据成功
     * @param mList
     */
    void loadSuccess(List<T> mList);

    /**
     * 加载数据失败
     * @param message
     */
    void loadFailure(String message);

    /**
     * 开始加载
     */
    void loadStart();

    /**
     * 加载结束
     */
    void loadComplete();
}
