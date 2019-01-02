package com.example.jiaguoqiang20190102.di.model;

import android.util.Log;

import com.example.jiaguoqiang20190102.di.beans.JsonBean;
import com.example.jiaguoqiang20190102.di.contract.IXaingContract;
import com.example.jiaguoqiang20190102.di.data.Constant;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

public class IXiangModelImpl implements IXaingContract.IXiangModel {

    @Override
    public void containXingData(final CallBack callBack) {
        OkGo.<String>get(Constant.LIST_URL).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                String responseData = response.body().toString();
                Log.i("jgq","responseData");
                //解析
                Gson gson = new Gson();
                JsonBean jsonBean = gson.fromJson(responseData, JsonBean.class);
                Log.i("jgq","数据"+jsonBean);
                callBack.responseData(jsonBean);
            }
        });
    }
}
