package com.waber.mvvmpractice.retrofitInterface;

import com.waber.mvvmpractice.bean.NewsBean;
import com.waber.mvvmpractice.constant.URLConstant;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by waber on 2018/5/30.
 */

public interface RetrofitInterface {

    //获取“分类中搜索商品”的数据
    @GET(URLConstant.URL_PATH)
    Observable<NewsBean> getNewsData();
}
