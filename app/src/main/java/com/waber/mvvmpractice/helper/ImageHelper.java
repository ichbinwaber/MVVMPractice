package com.waber.mvvmpractice.helper;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * Created by waber on 2018/5/30.
 */

public class ImageHelper {

    /**
     * mv_vm xml 传入url 加载图片
     * imageUrl 为xml中 的命名
     *
     * @param iv   imageView
     * @param url 图片路径
     */
    @BindingAdapter({"imageUrl"})
    private static void loadImage(ImageView iv,String url){
        Glide.with(iv.getContext()).load(url).into(iv);
    }

    /**
     * mv_vm xml 设置 mipmap Resource
     *
     * @param iv    imageView
     * @param resId resource id
     */
    @BindingAdapter({"resId"})
    private static void loadMipmapResource(ImageView iv,int resId){
        iv.setImageResource(resId);
    }
}