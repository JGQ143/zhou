package com.example.jiaguoqiang20190102.di.contract;

import com.example.jiaguoqiang20190102.di.beans.JsonBean;

public interface IXaingContract {
    //v层
    public interface IXiangView{
        public void ListData(JsonBean user);
    }

    //p层
    public interface IXiangPresenter<IXiangView>{
        //绑定
        public void attach(IXiangView iXiangView);

        //解绑
        public void deauch(IXiangView iXiangView);

        //传值
        public void requestData();
    }

    //m层
    public interface IXiangModel{
        public void containXingData(CallBack callBack);

        //接口回传
        public interface CallBack{
            public void responseData(JsonBean jsonBean);
        }
    }
}
