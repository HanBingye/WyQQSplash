package com.bing.wyqqsplash.main.shanghai;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bing.wyqqsplash.R;
import com.bing.wyqqsplash.main.hangzhou.HangzhouDetailBean;

import java.util.List;

public class ZhihuAdapter extends RecyclerView.Adapter {


    private List<HangzhouDetailBean.ResultBean.DataBean> list;

    public ZhihuAdapter(List<HangzhouDetailBean.ResultBean.DataBean> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_hangzhou, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((ViewHolder) holder).ivZhihu.setVisibility(View.GONE);
        ((ViewHolder) holder).tvZhihu.setText(list.get(position).getContent());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView ivZhihu;
        private TextView tvZhihu;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivZhihu = itemView.findViewById(R.id.iv_hangzhou_item);
            tvZhihu = itemView.findViewById(R.id.tv_hangzhou_item);
        }
    }
}

