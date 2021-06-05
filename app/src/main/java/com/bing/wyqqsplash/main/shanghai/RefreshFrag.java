package com.bing.wyqqsplash.main.shanghai;

import android.os.AsyncTask;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bing.refresh.GodRefreshLayout;
import com.bing.wyqqsplash.R;
import com.bing.wyqqsplash.base.BaseFragment;
import com.bing.wyqqsplash.base.ViewInject;
import com.bing.wyqqsplash.main.XiaohuaTask;
import com.bing.wyqqsplash.main.hangzhou.HangzhouDetailBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

@ViewInject(mainlayoutid = R.layout.fragment_refresh)
public class RefreshFrag extends BaseFragment {
    @BindView(R.id.god_refresh)
    GodRefreshLayout godRefresh;
    @BindView(R.id.rv_refresh)
    RecyclerView rvRefresh;

    private List<HangzhouDetailBean.ResultBean.DataBean> list;

    @Override
    public void afterBindView() {
        initGetNetData();
        if (list == null) {
            list = new ArrayList<>();
            for (int i = 0; i < 20; i++) {
                HangzhouDetailBean.ResultBean.DataBean bean = new HangzhouDetailBean.ResultBean.DataBean();
                bean.setContent("服了");
                list.add(bean);
            }
        }
//        else{
//            Toast.makeText(getContext(), "请检查网络状态", Toast.LENGTH_SHORT).show();
//        }
        initRecyclerView();
        initRefreshLayout();


    }

    private void initRefreshLayout() {
//        godRefresh.setRefreshManger();
        godRefresh.setRefreshManger(new MeituanRefreshManger(getContext()));
        godRefresh.setRefreshListener(new GodRefreshLayout.RefreshListener() {
            @Override
            public void onRefreshing() {

                list = null;
                initGetNetData();
                if (list == null) {
                    list = new ArrayList<>();
                    int time = (int) System.currentTimeMillis();
                    for (int i = 0; i < 20; i++) {
                        HangzhouDetailBean.ResultBean.DataBean bean = new HangzhouDetailBean.ResultBean.DataBean();
                        bean.setContent("啊这" + time);
                        list.add(bean);
                    }

                }
                initRecyclerView();
                godRefresh.refreshOver();
//                        else {
//                            Toast.makeText(getContext(), "请检查网络状态", Toast.LENGTH_SHORT).show();
//                        }


            }
        });
    }

    private void initRecyclerView() {
        rvRefresh.setLayoutManager(new LinearLayoutManager(getContext()));
//        rvRefresh.setAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.zhihu_show));


        rvRefresh.setAdapter(new ZhihuAdapter(list));


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
