package com.example.jiaguoqiang20190102.di.presenter;

import com.example.jiaguoqiang20190102.di.beans.User;
import com.example.jiaguoqiang20190102.di.contract.IContract;
import com.example.jiaguoqiang20190102.di.model.IGrideModelImpl;

import java.lang.ref.SoftReference;

public class IGridePresenterImpl implements IContract.IGridePresenter<IContract.IGrideView> {
    IContract.IGrideView iGrideView;
    private IContract.IGrideModel iGrideModel;
    private SoftReference<IContract.IGrideView> iGrideViewSoftReference;

    @Override
    public void attach(IContract.IGrideView iGrideView) {
        this.iGrideView=iGrideView;
        //软引用
        iGrideViewSoftReference = new SoftReference<>(iGrideView);
        //创建model
        iGrideModel = new IGrideModelImpl();
    }

    @Override
    public void deauch(IContract.IGrideView iGrideView) {
        iGrideViewSoftReference.clear();
    }

    @Override
    public void requestData() {
        iGrideModel.containGrideData(new IContract.IGrideModel.CallBack() {
            @Override
            public void responseData(User user) {
                iGrideView.showData(user);
            }
        });
    }
}
