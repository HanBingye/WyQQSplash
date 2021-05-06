package com.bing.wyqqsplash.main.hangzhou;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.QuickContactBadge;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bing.wyqqsplash.R;

import java.util.ArrayList;
import java.util.List;

public class HangzhouAdapter extends RecyclerView.Adapter {

    private Activity context;
    private ArrayList<HangzhouBean> data;
    private final RecyclerView.RecycledViewPool recycledViewPool;

    public HangzhouAdapter(Activity context, ArrayList<HangzhouBean> data) {
        recycledViewPool = new RecyclerView.RecycledViewPool();
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == HangzhouBean.HangzhouItemType.VERTICAL) {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_hangzhou, parent, false);
            ViewHolder holder = new ViewHolder(itemView);
            return holder;
        } else if (viewType == HangzhouBean.HangzhouItemType.HORIZONTAL) {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_hangzhou_rv, null);
            ViewHolderRv holder = new ViewHolderRv(itemView);
            return holder;
        }
        return null;

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        HangzhouBean hangzhouBean = data.get(position);
        if (holder instanceof ViewHolder) {
            ((ViewHolder) holder).tvHangzhouItem.setText(hangzhouBean.getDec());
            ((ViewHolder) holder).ivHangzhouItem.setVisibility(hangzhouBean.isShowImg() ? View.VISIBLE : View.GONE);
            holder.itemView.setTag(position);
        } else if (holder instanceof ViewHolderRv) {
            ((ViewHolderRv) holder).rvHangzhouItem.setAdapter(new HangzhouAdapter(context, hangzhouBean.getData()));
        }

    }


    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public int getItemViewType(int position) {
        return data.get(position).getItemType();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView ivHangzhouItem;
        private TextView tvHangzhouItem;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivHangzhouItem = itemView.findViewById(R.id.iv_hangzhou_item);
            tvHangzhouItem = itemView.findViewById(R.id.tv_hangzhou_item);

            ivHangzhouItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                HangzhouDetailActivity.start(context,ivHangzhouItem);
                }
            });

        }
    }

    public class ViewHolderRv extends RecyclerView.ViewHolder {

        private RecyclerView rvHangzhouItem;

        public ViewHolderRv(@NonNull View itemView) {
            super(itemView);
            rvHangzhouItem = itemView.findViewById(R.id.rv_hangzhou_item);
            LinearLayoutManager layoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
           //使用RecycledViewPool时必须设置为true
            layoutManager.setRecycleChildrenOnDetach(true);
            rvHangzhouItem.setLayoutManager(layoutManager);
            //小优化:使多个RecyclerView共用一个RecycledViewPool
            rvHangzhouItem.setRecycledViewPool(recycledViewPool);


        }
    }
}
