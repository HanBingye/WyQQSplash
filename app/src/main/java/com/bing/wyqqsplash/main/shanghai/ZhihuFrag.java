package com.bing.wyqqsplash.main.shanghai;

import android.os.AsyncTask;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bing.wyqqsplash.R;
import com.bing.wyqqsplash.base.BaseFragment;
import com.bing.wyqqsplash.base.ViewInject;
import com.bing.wyqqsplash.main.XiaohuaTask;
import com.bing.wyqqsplash.main.hangzhou.HangzhouDetailBean;
import com.google.android.material.appbar.AppBarLayout;

import java.util.List;

import butterknife.BindView;

@ViewInject(mainlayoutid = R.layout.fragment_zhihu)
public class ZhihuFrag extends BaseFragment {
    @BindView(R.id.appbar_zhihu)
    AppBarLayout appbarZhihu;
    @BindView(R.id.rv_zhihu)
    RecyclerView rvZhihu;
    @BindView(R.id.toolbar)
    Toolbar toolbar;


    private List<HangzhouDetailBean.ResultBean.DataBean> list;



    @Override
    public void afterBindView() {



        initGetNetData();
        if (list != null) {
        initRecyclerView();
        }else{
            Toast.makeText(getContext(), "请检查网络状态", Toast.LENGTH_SHORT).show();
        }


    }

    private void initRecyclerView() {
        rvZhihu.setLayoutManager(new LinearLayoutManager(getContext()));
        rvZhihu.setAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.zhihu_show));

        if (rvZhihu.getAdapter() == null) {

            rvZhihu.setAdapter(new ZhihuAdapter(list));


        }
    }

    private void initGetNetData() {
        XiaohuaTask xiaohuaTask = new XiaohuaTask();
        AsyncTask<Object, Object, Object> desc = xiaohuaTask.execute("desc", "1", "20");

        try {
            Object o = desc.get();

            list = (List<HangzhouDetailBean.ResultBean.DataBean>) o;

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
