package com.waber.mvvmpractice.helper;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;

/**
 * Created by waber on 2018/5/30.
 */

public class DialogHelper {

    public static DialogHelper getInstance(){
        return LoadDialogHelper.instance;
    }

    private static class LoadDialogHelper{
        static DialogHelper instance = new DialogHelper();
    }

    private ProgressDialog progressDialog;

    private void createDialog(Context context,String msg){
        progressDialog = new ProgressDialog(context);
        progressDialog.setCancelable(false);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setMessage(msg);
        progressDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialogInterface) {
                progressDialog = null;
            }
        });
    }

    /**
     * 展示加载框
     * @param context
     * @param msg
     */
    public void show(Context context,String msg){
        close();
        createDialog(context,msg);
        if (progressDialog!=null && !progressDialog.isShowing()){
            progressDialog.show();
        }
    }

    /**
     * 关闭展示框
     */
    public void close(){
        if (progressDialog!=null && !progressDialog.isShowing()){
            progressDialog.dismiss();
        }
    }

}
