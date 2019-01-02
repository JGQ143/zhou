package com.example.jiaguoqiang20190102.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.webkit.WebView;
import android.webkit.WebViewClient;


import com.example.jiaguoqiang20190102.R;
import com.example.jiaguoqiang20190102.di.adapter.ListAdapter;
import com.example.jiaguoqiang20190102.di.beans.JsonBean;
import com.example.jiaguoqiang20190102.di.beans.User;
import com.example.jiaguoqiang20190102.di.contract.IXaingContract;
import com.example.jiaguoqiang20190102.di.presenter.IXiangPresenterImpl;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class XiangQingActivity extends AppCompatActivity implements IXaingContract.IXiangView {

    @BindView(R.id.webview)
    WebView webview;
//    @BindView(R.id.recyclerww)
//    RecyclerView recyclerww;
    private IXaingContract.IXiangPresenter iXiangPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xiang_qing);
        ButterKnife.bind(this);
        //创建presenter
        iXiangPresenter = new IXiangPresenterImpl();
        iXiangPresenter.attach(this);
        iXiangPresenter.requestData();
        webview.loadUrl(getIntent().getStringExtra("url"));
        webview.setWebViewClient(new WebViewClient());
    }

    @Override
    public void ListData(JsonBean user) {
        List<User.DataBean> data = user.getData();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(OrientationHelper.VERTICAL);
//        recyclerww.setLayoutManager(linearLayoutManager);
//        ListAdapter listAdapter = new ListAdapter();
//        listAdapter.setData(this,data);
//        recyclerww.setAdapter(listAdapter);
//        listAdapter.notifyDataSetChanged();
    }

    //内存泄漏
    @Override
    protected void onDestroy() {
        super.onDestroy();
        iXiangPresenter.deauch(this);
    }
}
