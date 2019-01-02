package com.example.jiaguoqiang20190102.di.presenter;

import com.example.jiaguoqiang20190102.di.beans.JsonBean;
import com.example.jiaguoqiang20190102.di.contract.IXaingContract;
import com.example.jiaguoqiang20190102.di.model.IXiangModelImpl;

import java.lang.ref.SoftReference;

public class IXiangPresenterImpl implements IXaingContract.IXiangPresenter<IXaingContract.IXiangView> {
    IXaingContract.IXiangView iXiangView;
    private SoftReference<IXaingContract.IXiangView> iXiangViewSoftReference;
    private IXaingContract.IXiangModel iXiangModel;
    @Override
    public void attach(IXaingContract.IXiangView iXiangView) {
        this.iXiangView=iXiangView;
        //软引用
        iXiangViewSoftReference = new SoftReference<>(iXiangView);

        //创建model
        iXiangModel = new IXiangModelImpl();
    }

    @Override
    public void deauch(IXaingContract.IXiangView iXiangView) {
        iXiangViewSoftReference.clear();
    }

    @Override
    public void requestData() {
        iXiangModel.containXingData(new IXaingContract.IXiangModel.CallBack() {
            @Override
            public void responseData(JsonBean jsonBean) {
                iXiangView.ListData(jsonBean);
            }
        });
    }
}
