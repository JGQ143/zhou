package com.example.jiaguoqiang20190102.di.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.jiaguoqiang20190102.R;
import com.example.jiaguoqiang20190102.di.beans.User;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    List<User.DataBean> data;


    public void setData(Context context, List<User.DataBean> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        View view = View.inflate(context, R.layout.item_list, null);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        MyHolder myHolder = (MyHolder) viewHolder;
        myHolder.textview3.setText(data.get(position).getTitle());
        myHolder.textview4.setText("￥"+data.get(position).getPrice());
        String images = data.get(position).getImages();
        String[] split = images.split("\\|");
        Glide.with(context).load(split[0]).into(myHolder.imageview2);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    class MyHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.imageview2)
        ImageView imageview2;
        @BindView(R.id.textview3)
        TextView textview3;
        @BindView(R.id.textview4)
        TextView textview4;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

}
