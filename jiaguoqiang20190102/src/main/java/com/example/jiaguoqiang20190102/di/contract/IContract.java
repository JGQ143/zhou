package com.example.jiaguoqiang20190102.di.contract;

import com.example.jiaguoqiang20190102.di.beans.User;

public interface IContract {
    //v层
    public interface IGrideView{
        public void showData(User user);
    }

    //p层
    public interface IGridePresenter<IGrideView>{
        //绑定
        public void attach(IGrideView iGrideView);

        //解绑
        public void deauch(IGrideView iGrideView);

        //传值
        public void requestData();
    }

    //m层
    public interface IGrideModel{
        public void containGrideData(CallBack callBack);

        //接口回传
        public interface CallBack{
            public void responseData(User user);
        }
    }
}
