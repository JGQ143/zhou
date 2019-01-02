package com.example.jiaguoqiang20190102.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.jiaguoqiang20190102.R;
import com.example.jiaguoqiang20190102.di.adapter.GrideAdapter;
import com.example.jiaguoqiang20190102.di.beans.User;
import com.example.jiaguoqiang20190102.di.contract.IContract;
import com.example.jiaguoqiang20190102.di.presenter.IGridePresenterImpl;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements IContract.IGrideView {

    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    private IContract.IGridePresenter iGridePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        //创建presenter
        iGridePresenter = new IGridePresenterImpl();
        iGridePresenter.attach(this);
        iGridePresenter.requestData();
    }
    //刷新数据
    @Override
    public void showData(User user) {
        final List<User.DataBean> dataList = user.getData();
        //布局
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,3);
        recyclerview.setLayoutManager(gridLayoutManager);
        //创建adapter
        final GrideAdapter grideAdapter = new GrideAdapter();
        //传数据
        grideAdapter.setData(this,dataList);
        recyclerview.setAdapter(grideAdapter);
        grideAdapter.notifyDataSetChanged();

        //条目监听
        grideAdapter.setCickListener(new GrideAdapter.CickListener() {
            @Override
            public void setOnItemClckListener(View v, int position) {
                dataList.remove(position);
                grideAdapter.notifyDataSetChanged();
            }
        });
    }
    //内存泄漏
    @Override
    protected void onDestroy() {
        super.onDestroy();
        iGridePresenter.deauch(this);
    }
}
