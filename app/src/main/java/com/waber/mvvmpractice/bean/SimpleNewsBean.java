package com.waber.mvvmpractice.bean;

import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;


/**
 * Created by waber on 2018/5/31.
 */

public class SimpleNewsBean {

    public ObservableInt color = new ObservableInt();
    public ObservableField<String> thumbnail = new ObservableField<>();
    public ObservableField<String> description = new ObservableField<>();
    public ObservableInt id = new ObservableInt();
    public ObservableField<String> name = new ObservableField<>();
    public ObservableBoolean isGoods = new ObservableBoolean();
}
