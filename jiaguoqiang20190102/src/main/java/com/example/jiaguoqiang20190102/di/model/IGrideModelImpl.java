package com.example.jiaguoqiang20190102.di.model;

import android.util.Log;


import com.example.jiaguoqiang20190102.di.beans.User;
import com.example.jiaguoqiang20190102.di.contract.IContract;
import com.example.jiaguoqiang20190102.di.data.Constant;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

public class IGrideModelImpl implements IContract.IGrideModel{

    @Override
    public void containGrideData(final CallBack callBack) {
        OkGo.<String>get(Constant.LIST_URL).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                String responseData = response.body().toString();
                Log.i("jgq","responseData");
                //解析gson
                Gson gson = new Gson();
                User user = gson.fromJson(responseData, User.class);
                Log.i("jgq","数据"+user);
                callBack.responseData(user);
            }
        });
    }
}
