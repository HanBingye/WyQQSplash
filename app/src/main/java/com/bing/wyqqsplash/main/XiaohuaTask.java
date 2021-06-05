package com.bing.wyqqsplash.main;

import android.os.AsyncTask;
import android.util.Log;

import com.bing.wyqqsplash.main.hangzhou.HangzhouDetailBean;
import com.bing.wyqqsplash.main.hangzhou.module.HangzhouHttp;
import com.google.gson.Gson;

import java.util.List;

import okhttp3.Response;

public class XiaohuaTask extends AsyncTask<Object, Object, Object> {


    private List<HangzhouDetailBean.ResultBean.DataBean> list ;





    //运行在子线程中
    @Override
    protected List<HangzhouDetailBean.ResultBean.DataBean> doInBackground(Object... objects) {
        Object desc = new HangzhouHttp().getXiaoHua((String) objects[0], (String) objects[1], (String) objects[2]);


        Response response = (Response) desc;

        try {
            if (response != null) {
                String json = response.body().string();
                Log.d("XiaohuaTask", "onResponse" + json);
                HangzhouDetailBean hangzhouDetailBean = new Gson().fromJson(json, HangzhouDetailBean.class);
                list = hangzhouDetailBean.getResult().getData();
            } else {
                Log.d("XiaohuaTask", "response为空");
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
        return list;
    }

    //运行在主线程当中
    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);


    }

}
