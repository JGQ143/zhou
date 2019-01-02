package com.example.jiaguoqiang20190102.di.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.jiaguoqiang20190102.R;
import com.example.jiaguoqiang20190102.di.beans.User;
import com.example.jiaguoqiang20190102.ui.activity.XiangQingActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GrideAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    List<User.DataBean> dataList;
    private CickListener cickListener;

    public void setData(Context context, List<User.DataBean> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    //获取视图
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        View view = View.inflate(context, R.layout.item_gride, null);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int position) {
        MyHolder myHolder = (MyHolder) viewHolder;
        myHolder.textview1.setText(dataList.get(position).getTitle());
        myHolder.textview2.setText("￥"+dataList.get(position).getPrice());
        String images = dataList.get(position).getImages();
        String[] split = images.split("\\|");
        Glide.with(context).load(split[0]).into(myHolder.imageview);
        //长按删除
        viewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(context,"点击了",Toast.LENGTH_LONG).show();
                cickListener.setOnItemClckListener(v,position);
                return true;
            }
        });
        //点击条目传值
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,XiangQingActivity.class);
                intent.putExtra("url",dataList.get(position).getDetailUrl());
                context.startActivity(intent);
                Toast.makeText(context,"点击了",Toast.LENGTH_LONG).show();
            }
        });
    }
    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class MyHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.imageview)
        ImageView imageview;
        @BindView(R.id.textview1)
        TextView textview1;
        @BindView(R.id.textview2)
        TextView textview2;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    //创建接口
    public interface CickListener{
        public void setOnItemClckListener(View v, int position);
    }

    public void setCickListener(CickListener cickListener) {
        this.cickListener = cickListener;
    }
}
